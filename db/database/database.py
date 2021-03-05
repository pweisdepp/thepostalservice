import mysql.connector

class Database:

	def __init__(self, database_name):
		self.__database_name = database_name
	
	def setLocalhost(self, localhost):
		self.__localhost = localhost

	def setUsername(self, username):
		self.__username = username
	
	def setPassword(self, password):
		self.__password = password

	def setTableName(self, table_name):
		self.__table_name = table_name

	def createDatabase(self):
		db = mysql.connector.connect(
		  host   = self.__localhost,
		  user   = self.__username,
		  passwd = self.__password,
		)

		cursor = db.cursor()

		cursor.execute("CREATE DATABASE IF NOT EXISTS "+self.__database_name)

		return "Database "+self.__database_name+" has been created"

	def createTable(self):
		self.createConnection()
		
		cursor = self.__db.cursor()
		
		cursor.execute("CREATE TABLE IF NOT EXISTS "+self.__table_name + 
                      "(id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        country_ISO VARCHAR(2) NOT NULL,
                        longitude DOUBLE,
                        latitude DOUBLE,
                        number INT,
                        street_name VARCHAR(128),
                        unit    VARCHAR(10),
                        city    VARCHAR(20),
                        district VARCHAR(128),
                        region VARCHAR(128),
                        post_code VARCHAR(30)
                        address_Hash VARCHAR(64))")
    
		return "Table "+self.__table_name+" has been created"
	
	def createConnection(self):
		db = mysql.connector.connect(
		  host     = self.__localhost,
		  user     = self.__username,
		  passwd   = self.__password,
		  database = self.__database_name
		)

		self.__db = db