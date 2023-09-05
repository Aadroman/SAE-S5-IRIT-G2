def getDictionary() :
    #Initialisation de la structure du dictionnaire manipulé
    d = []
    #Lecture du dictionnaire
    input_path = "dictionary.csv"
    file_input=open(input_path,"r")
    lines = file_input.readlines()
    #Parcours des lignes du dictionnaire
    for line in lines :
        #Suppression du symbole de passage à la ligne
        line = line.replace("\n","")
        #Récupération de la ligne sous forme d'une liste
        cells = line.split(";")
        #Ajout de la liste à la structure renvoyée
        d.append(cells)
    file_input.close()
    return d

def find_attribute2(d,attr,db) :
    l = 0
    res = []
    for line in d :
        c = 2
        while c<5:
            if d[0][c]==db :
                column = line[c]
                cell = column.split(".")
                #print(cell[-1],attr)
                if attr == cell[-1] :
                    #print([d[0][c],l,c,d[l][c]])
                    res.append(d[0][c])
                    res.append(l)
                    res.append(c)
                    res.append(d[l][c])
                    return res
                    c=c+1
                else :
                    c=c+1
            else :
                c=c+1
        l=l+1
    return res

def find_attribute(d,attr) :
    l = 0
    res = []
    for line in d :
        c = 2
        while c<5:
            column = line[c]
            cell = column.split(".")
            if attr == cell[-1] :
                #print([d[0][c],l,c,d[l][c]])
                res.append(d[0][c])
                res.append(l)
                res.append(c)
                res.append(d[l][c])
                return res
                c=c+1
            else :
                c=c+1

        l=l+1
    return res

def getEntities(d) :
    entities = []
    for line in d : 
        c = 2
        while c<5:
            e = line[c].split(".")[0]
            if e in entities or e == 'DB1' or e == 'DB2' or e == 'DB3' or e == '':
                c=c+1
            else :
                entities.append(e)
                c=c+1
    return entities

def find_key(d,ds,db) : # à modifier en ajoutant la db en question
    l = 0
    for line in d :
        c = 2
        while c<5:
            if d[0][c]==db :
                column = line[c]
                cell = column.split(".")
                if ds == cell[0] :
                    #print(ds,"=",cell[0])
                    if "_id" in cell[-1] and ds.lower()[0:3] in cell[-1] :
                        #print(cell[-1])
                        #print([d[0][c],l,c,d[l][c]])
                        return [d[0][c],l,c,d[l][c]]
                        c=c+1
                    else :
                        c=c+1
                else :
                    c=c+1
            else :
                c=c+1
        l=l+1
    return []


def getLogicEquivalences(d,p,ds,sys) :
    value = ds + "." + p
    #print("value",value)
    getLine = getLineLogicalValue(d,value,sys)
    #print(getLine)
    eq = []
    c = 2
    #print("********",d[getLine][c])
    while c<5 :
        if len(d[getLine][c].split(","))!=1 :
            print("ok")
        else :
            cell = d[getLine][c].split(".")
            #print(cell)
            if cell[-1] in p and cell[-1] != '':
                eq.append([d[0][c],getLine,c,d[getLine][c]])
        c = c + 1
    
    if eq == [] : #si pas de correspondance alors il s'agit de la clé primaire de l'objet en question
        attr_eq = find_attribute(d,p) 
        eq.append(attr_eq)
        #print(eq)
    return eq

def getLineLogicalValue(d,value,sys) :
   # print("getLineLogicalValue",value,sys)
    l = 0
    if sys == "D" :
        for line in d :
            #print("*******line******",line)
            #print("getLineLogicalValue",line[1])
            if line[1] == value :
                #print(l)
                return l
            else :
                l = l + 1
    elif sys == "R" :
        for line in d :
            if line[0] == value :
                #print(l)
                return l
            else :
                l = l +1
    return -1


#d = getDictionary()
#attr = 'total_price'
#position = find_attribute(d,attr)
#print(position[0])
#print(position[-1].split(".")[0]) #getDS
#print(position[-1].split(".")[-1])

#print(getEntities(d))

#print(find_key(d,"Orders","DB2"))

