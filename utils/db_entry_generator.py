#!/usr/bin/env python

import mysql.connector
import os
import pandas

# Use 1% of openaddress records
RATIO = 0.01
CURR_DIR = os.getcwd()

conn = mysql.connector.connect(
    user='root', password='password', host='127.0.0.1')


def insert_records(csv_path):
    df = pandas.read_csv(csv_path)
    # get random samples
    df = df.sample(frac=RATIO)
    # add random samples to db
    df.to_sql('US', con=conn)


for subdir, dirs, files in os.walk(CURR_DIR):
    for filename in files:
        filepath = subdir + os.sep + filename
        if filepath.endswith(".csv"):
            insert_records(filepath)
