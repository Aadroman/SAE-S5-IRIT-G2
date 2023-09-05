from pymongo import MongoClient
import pymysql
import time
#/!\ Besoin d'installer les librairies pymsql et pymongo sur python avec la commande : pip install
#A MAJ avec nouvelles BD

def DB1_execution(query):
    connection = pymysql.connect(host="localhost", user="root", passwd="", database="db1")
    cursor = connection.cursor()    
    cursor.execute(query)
    connection.commit()
    return cursor.fetchall()

def DB2_execution(query):
    connection = pymysql.connect(host="localhost", user="root", passwd="", database="db2")
    cursor = connection.cursor()    
    cursor.execute(query)
    connection.commit()
    return cursor.fetchall()

def DB3_execution(pipeline,collection_name):
    client = MongoClient('mongodb://localhost:27017/?readPreference=primary&appname=MongoDB+Compass&directConnection=true&ssl=false')
    db = client["db3"]
    collection = db[collection_name]
    return collection.aggregate(pipeline)

"""
#Q6 sur UVR
start = time.time()
create_R_orders = "CREATE TABLE D_ORDERS (order_id VARCHAR(50), total_price FLOAT);"
create_R_orderline = "CREATE TABLE D_ORDERLINE (order_id VARCHAR(50), product_id VARCHAR(50), price FLOAT);"

DB2_execution(create_R_orders)
DB2_execution(create_R_orderline)

pipeline = [{"$match":{"orderline.product_id":"B002Q6DB7A"}}]
docs = DB3_execution(pipeline,"orders")
insert_R_orders = ""
insert_R_orderline = ""
for doc in docs :
    order_id = str(doc["order_id"])
    total_price = str(doc["total_price"])
    product_id = str(doc["orderline"][0]["product_id"])
    price = str(doc["orderline"][0]["price"])
    insert_R_orders = "INSERT INTO D_ORDERS VALUES ('" + order_id + "'," + total_price + ");" + "\n"
    DB2_execution(insert_R_orders)
    insert_R_orderline = "INSERT INTO D_ORDERLINE VALUES ('"+ order_id + "','"+ product_id + "',"+ price + ");" + "\n"
    DB2_execution(insert_R_orderline)

query = "SELECT customer_id FROM Orders O, D_Orders OD, D_Orderline DOL WHERE O.order_id=OD.order_id and OD.order_id=DOL.order_id;"

rows = DB2_execution(query)
for row in rows :
    print(row)

drop_R_orders = "DROP TABLE D_ORDERS;"
drop_R_orderline = "DROP TABLE D_ORDERLINE;"

DB2_execution(drop_R_orders)
DB2_execution(drop_R_orderline)

end = time.time()
print("execution time : ",end-start)
"""
#Q6 sure 
start = time.time()

client = MongoClient('mongodb://localhost:27017/?readPreference=primary&appname=MongoDB+Compass&directConnection=true&ssl=false')
db = client["db3"]
DB2collection = db["rorders"]
collection = db["orders"]

rows = DB2_execution("SELECT * FROM Orders")
for row in rows :
    pipeline = {"order_id":row[0],"customer_id":row[1]}
    DB2collection.insert_one(pipeline)
    
pipeline = [{"$match":{"product_id":20}},{"$lookup":{"from":"rorders","localField":"order_key","foreignField":"order_key","as":"rorders"}}]

docs = DB3_execution(pipeline,"orders")
for doc in docs :
    print(doc)

DB2collection.delete_many({})

end = time.time()
end = time.time()
print("execution time : ",end-start)
