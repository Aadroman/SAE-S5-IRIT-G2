import read

#Réécriture de l'opération de selection
#op = [SELECTION,p,ds]
#[["SELECTION","total_price>100;OR;total_price<201","Orders"]]
def r_selection(d,op,db) :
    if db == "DB3" :
        sys = "D"
    else :
        sys = "R"
    
    pred = op[1]
    ds = op[2]
    op2 = []
    op2.append(op)
    p = pred.split(";")
    for pi in p :
        if len(pi)<=2 :
            continue
        else :
            c = None
            if pi.find(">") != -1 :
                c = pi.split(">")
            elif pi.find("<") != -1 :
                c = pi.split("<")
            elif pi.find("=") != -1 :
                c = pi.split("=")
            #print(c)
            attr = c[0]
            #print(attr)
            position = read.find_attribute(d,attr)
            #print(position)
            db2 = position[0]
            ds2 = position[-1].split(".")[0]
            #print(db)
            if db == db2 :
                #print(db,"=",db2)
                if ds == ds2 :
                    #print(ds,"==",ds2)
                    continue
                else :
                    if create_join(d,ds,db,ds2,db2) not in op2 :
                        op2.append(create_join(d,ds,db,ds2,db2))
            else :
                #print(db,"!=",db2)
                if create_join(d,ds,db,ds2,db2) not in op2 :
                    #print(db,"!=",db2)
                    op2.append(create_join(d,ds,db,ds2,db2))
    return op2
#Réécriture de l'opération de projection
def r_projection(d,op,db) :
    if db == "DB3" :
        sys = "D"
    else :
        sys = "R"
    
    proj = op[1]
    ds = op[2]
    op2 = []
    op2.append(op)
    entities = read.getEntities(d)
    for attr in proj :
        position = read.find_attribute2(d,attr,db)
        if position == [] :
            position = read.find_attribute(d,attr)
        #print(position)
        db2 = position[0]
        ds2 = position[-1].split(".")[0]
        #print(ds2)
        #print(db,db2)
        if db == db2 :
            #print(db,"=",db2)
            if ds == ds2 :
                #print(ds,"==",ds2)
                continue
            else :
                if create_join(d,ds,db,ds2,db2) not in op2 or ds in entities :
                    #print("ko")
                    op2.append(create_join(d,ds,db,ds2,db2))
                else :
                    pass
        else :
            #print(db,"!=",db2)
            #print(ds)
            if ds in entities :
                if create_join(d,ds,db,ds2,db2) not in op2 or ds in entities :
                    op2.append(create_join(d,ds,db,ds2,db2))
            else :
                #print("not in entities")
                equi1 = read.getLogicEquivalences(d,attr,ds,sys)
                for e in equi1 :
                    #print(op2[0])
                    op2[0][2]=e[3].split(".")[0]
                #print(op2)
    return op2

#Réécriture de l'opération de jointure
def r_jointure(d,op,db) :
    pred = op[1]
    ds1 = op[2]
    ds2 = op[3]
    op2 = []
    p = pred.split("=") #p[0] = attribut de jointure de ds1 / p[1] = attribut de jointure de ds2
    
    if db == "DB3" :
        sys = "D"
    else :
        sys = "R"
    
    equi1 = read.getLogicEquivalences(d,p[0],ds1,sys)
    #print(1,ds1,equi1)
    equi2 = read.getLogicEquivalences(d,p[1],ds2,sys) #["JOIN","product_id=order_line.product_id","Products","Orders"]
    #print(2,ds2,equi2)
    
    for e in equi1 : #d[0][c],getLine,c,d[getLine][c]
        if e[0] == db :
            if e[-1].split(".")[0] != ds1 :
                op[2] = e[-1].split(".")[0]
                break
        else :
            if e[-1].split(".")[0] != ds1 :
                op[2] = e[-1].split(".")[0]
                #print(e[-1].split(".")[0],db,e[-1].split(".")[0],e[0])
                op2.append(create_join(d,e[-1].split(".")[0],db,e[-1].split(".")[0],e[0]))
                break
    
    for e in equi2 : #d[0][c],getLine,c,d[getLine][c]
        if e[0] == db :
            #print(e[-1].split(".")[0], ds2)
            if e[-1].split(".")[0] != ds2 :
                op[3] = e[-1].split(".")[0]
                break
        else :
            if e[-1].split(".")[0] != ds2 :
                op[2] = e[-1].split(".")[0]
                #print(e[-1].split(".")[0],db,e[-1].split(".")[0],e[0])
                op2.append(create_join(d,e[-1].split(".")[0],db,e[-1].split(".")[0],e[0]))
                break
    
    op2.insert(0,op)
    return op2

#Creation de jointure intermédiaire
def create_join(d,ds,db,ds2,db2) :
    cond = ""
    print(ds,db,ds2,db2)
    if ds == ds2 : #CLasse d'entité fragmentée à reconstruire
        #print(ds,"=",ds2)
        #print(ds,db2)
        key = read.find_key(d,ds,db) #d[0][c],l,c,d[l][c] / db, numligne, numcolonne, value
        if key == [] :
            key = read.find_key(d,ds,db2)
        key2 = read.find_attribute2(d,key[-1].split(".")[-1],db) #d[0][c],l,c,d[l][c] / db, numligne, numcolonne, value
        #print(key,key2)
        if key2 == [] :
            key2 = read.find_attribute2(d,key[-1].split(".")[-1],db2)
            #print("new key2",key2)
        elif key == [] :
            key = read.find_attribute2(d,key[-1].split(".")[-1],db2)
        cond = key[-1].split(".")[-1] + "=" + key2[-1].split(".")[-1]
        #print(key[-1],"=",key2[-1])
        #print(cond)
    else :
        key = read.find_key(d,ds,db)
        if key == [] :
            key = read.find_key(d,ds,db2)
        #print(key)
        fk = read.find_attribute2(d,key[-1].split(".")[-1],db)
        if fk == [] :
            key2 = read.find_key(d,ds2,db)
            fk2 = read.find_attribute2(d,key2[-1].split(".")[-1],db)
            cond = key2[-1].split(".")[-1] + "=" + fk2[-1].split(".")[-1]
        else :
            cond = key[-1].split(".")[-1] + "=" + fk[-1].split(".")[-1]
        #print(cond)
    return ["JOIN",cond,ds,ds2]