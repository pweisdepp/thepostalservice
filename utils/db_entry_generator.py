#!/usr/bin/env python

import mysql.connector
import os
import pandas
import sqlalchemy as sa

# Use 1% of openaddress records
RATIO = 0.01
CSV_DIRECTORY = os.getcwd() + os.sep + "csvData"
DATATYPES = {'ID': sa.Integer,
             'LON': sa.FLOAT,
             'LAT': sa.FLOAT,
             'NUMBER': sa.VARCHAR(128),
             'STREET': sa.VARCHAR(128),
             'UNIT': sa.VARCHAR(128),
             'CITY': sa.VARCHAR(128),
             'DISTRICT': sa.VARCHAR(128),
             'REGION': sa.VARCHAR(128),
             'POSTCODE': sa.Integer,
             'HASH': sa.VARCHAR(128)}

engine = sa.create_engine(
    "mysql+mysqlconnector://root:password@127.0.0.1/postal")


def insert_records(csv_path):
    df = pandas.read_csv(csv_path)
    # get random samples
    df = df.sample(frac=RATIO)
    # add random samples to db
    df.to_sql('Addresses', con=engine, if_exists='append', dtype=DATATYPES)


for subdir, dirs, files in os.walk(CSV_DIRECTORY):
    for filename in files:
        filepath = subdir + os.sep + filename
        if filepath.endswith(".csv"):
            insert_records(filepath)
