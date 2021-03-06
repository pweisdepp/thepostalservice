#!/usr/bin/env python

from sqlalchemy import create_engine
import pandas
from pathlib import Path

# Use 1% of openaddress records
RATIO = 0.01
CURR_DIR = Path.cwd()

engine = create_engine('mysql+pymysql://root:password@localhost:3306/postal')


def insert_records(csv_path: Path):
    print(f'Inserting from {csv_path}')
    df = pandas.read_csv(csv_path)
    # CSV is statetest/{state}/{city}.csv
    city = csv_path.stem[0:20]
    state = csv_path.parts[-2]

    # get random samples
    df = df.sample(frac=RATIO)
    df.drop(columns=['LAT', 'LON', 'ID', 'HASH'], inplace=True)
    df.rename(columns={
        'NUMBER': 'number',
        'STREET': 'street',
        'UNIT': 'unit',
        'CITY': 'city',
        'DISTRICT': 'district',
        'REGION': 'country',
        'POSTCODE': 'post_code',
    }, inplace=True)
    df['country'].fillna('US', inplace=True)
    df['city'].fillna(city, inplace=True)
    df['district'].fillna(state, inplace=True)
    df.dropna(thresh=5)
    df.fillna('', inplace=True)
    # add random samples to db
    df.to_sql('addresses', con=engine, if_exists='append', index=False)
    print(f'Inserted from {csv_path}')


def load_records(path: Path):
    if path.is_file():
        if path.suffix == ".csv":
            insert_records(path)
    elif path.is_dir():
        for child in path.iterdir():
            load_records(child)


if __name__ == "__main__":
    load_records(Path(__file__).parent / 'statetest')
