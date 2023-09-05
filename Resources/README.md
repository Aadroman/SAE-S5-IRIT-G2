# Resources


## Description
In order to implement Unibench benchmark, this README explains the process to create all databases and datasets in the defined systems.

## Systems
- Relational: PhpMyadmin (https://www.apachefriends.org/fr/download.html)
- Document-oriented: MongoDB Compass (https://www.mongodb.com/products/compass)

## Data model
- Relational Databases: DB1, DB2
- Document-oriented database: DB3

## Datasets
DB1: 

`create table customers(customer_id INT, person_id INT, name VARCHAR(50), zipcode INT, CONSTRAINT pk_customer_id PRIMARY KEY (customer_id));`

DB2:

`create table orders(order_id VARCHAR(50), customer_id INT, CONSTRAINT pk_order_id PRIMARY KEY (order_id));`

`create table products(product_id INT, brand VARCHAR(50), title VARCHAR(100), CONSTRAINT pk_product_id PRIMARY KEY (product_id));`

DB3:

The database and datasets "orders", "reviews" and "products" are created using the MongoDB interface.


## Files
- inser_customer.txt
- inser_orders.txt
- inser_products.txt
- orders_db3.json
- reviews.csv
- product.csv (/!\ some fields do not need to be selected for "products" collection)
