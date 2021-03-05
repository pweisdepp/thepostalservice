#!/usr/bin/env python

import mysql.connector
import os
import pandas
import sqlalchemy as sa

# Only include a % of the csv records to the db
# RATIO = 1.0
# Path to CSV files
CSV_DIRECTORY = "/home/pete/CPSC5200/csvData_reduced/"

XML_DATATYPES = {'NUMBER': 'str',
                 'STREET': 'str',
                 'UNIT': 'str',
                 'CITY': 'str',
                 'DISTRICT': 'str',
                 'COUNTRY': 'str',
                 'REGION': 'str',
                 'POSTCODE': 'str',}

SQL_DATATYPES = {'NUMBER': sa.VARCHAR(128),
                 'STREET': sa.VARCHAR(128),
                 'UNIT': sa.VARCHAR(128),
                 'CITY': sa.VARCHAR(128),
                 'DISTRICT': sa.VARCHAR(128),
                 'COUNTRY': sa.VARCHAR(128),
                 'REGION': sa.VARCHAR(128),
                 'POSTCODE': sa.VARCHAR(128)}

engine = sa.create_engine(
    "mysql+mysqlconnector://root:password@127.0.0.1/postal")


def insert_records(csv_path):
    df = pandas.read_csv(csv_path, dtype=XML_DATATYPES)
    # get random samples
    #df = df.sample(frac=RATIO)
    # add samples to db
    df.to_sql('addresses', con=engine, if_exists='append', dtype=SQL_DATATYPES, chunksize=2048)


for subdir, dirs, files in os.walk(CSV_DIRECTORY):
    for filename in files:
        filepath = subdir + os.sep + filename
        if filepath.endswith(".csv"):
            insert_records(filepath)
