#!/usr/bin/env python

import mysql.connector
import os
import pandas
import sqlalchemy

# Use 1% of openaddress records
RATIO = 0.01
CURR_DIR = os.getcwd()

# conn = mysql.connector.connect(
#     user='root', password='password', host='127.0.0.1')

engine = sqlalchemy.create_engine(
    "mysql+mysqlconnector://root:password@127.0.0.1/postal")

def insert_records(csv_path):
    df = pandas.read_csv(csv_path)
    # get random samples
    df = df.sample(frac=RATIO)
    # add random samples to db
    df.to_sql('US', con=engine, if_exists='append')


for subdir, dirs, files in os.walk(CURR_DIR):
    for filename in files:
        filepath = subdir + os.sep + filename
        if filepath.endswith(".csv"):
            insert_records(filepath)
