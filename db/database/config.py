import database as db

db = db.Database("world_address")
db.setLocalhost("localhost")
db.setUsername("root")
db.setPassword("")
db.createDatabase()

db.setTableName("addresses")
db.createTable()