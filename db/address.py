import mysql.connector
# import the library module
import hashlib

class ADRESS:

	def __init__(self):
		self.__localhost     = "localhost"
		self.__username      = "root"
		self.__password      = ""
		self.__database_name = "world_address" 
		self.__table_name 	 = "addresses" 
        self.__hashString = "This for world address"
		self.createConnection()
        
	def create(self):
		print('Enter Country ISO:')
		country_ISO = input()
		print('Enter Longitude:')
		longitude = double(input())
        print('Enter Latitude:')
		latitude = double(input())
		print('Enter street number:')
		number = int(input())
        print('Enter street name:')
		street_name = input()
		print('Enter Unit:')
		unit = input()
        print('Enter City:')
		city = input()
		print('Enter District:')
		district = input()
        print('Enter Region:')
		region = input()
        print('Enter Post code:')
		post_code = input()
		address_Hash = hashlib.sha224(self.__hashString.encode())
		val = (country_ISO, longitude, latitude, number, street_name, unit, city, district, region, post_code,address_Hash)
		cursor.execute("INSERT INTO "+self.__table_name+" (country_ISO, longitude, latitude, number, street_name, unit, city, district, region, post_code,address_Hash) 
                        VALUES (%s, %.2f, %.2f, %d, %s, %s, %s, %s, %s, %s, %s)", val)

		self.__db.commit()

		print(cursor.rowcount, "record inserted.")

	def read(self):
		cursor = self.__db.cursor()

		cursor.execute("SELECT * FROM "+self.__table_name+"")

		myresult = cursor.fetchall()

		for x in myresult:
		  print(x)

	def update(self, id, country_ISO, longitude, latitude, number, street_name, unit, city, district, region, post_code,address_Hash):

		cursor = self.__db.cursor()

		cursor.execute ("UPDATE "+self.__table_name+" SET country_ISO=%s, longitude=%.2f, latitude=%.2f, number=%d, street_name=%s, unit=%s, city=%s, district=%s, region=%s, post_code=%s,address_Hash=%s ", 
                        (country_ISO, longitude, latitude, number, street_name, unit, city, district, region, post_code,address_Hash, id))

		self.__db.commit()

		print(cursor.rowcount, "record update.")

	def delete(self, id):
		
		cursor = self.__db.cursor()

		cursor.execute ("DELETE FROM "+self.__table_name+" WHERE id = %s", (id,))

		self.__db.commit()

		print(cursor.rowcount, "record deleted.")

	def createConnection(self):
		db = mysql.connector.connect(
		  host     = self.__localhost,
		  user     = self.__username,
		  passwd   = self.__password,
		  database = self.__database_name
		)

		self.__db = db