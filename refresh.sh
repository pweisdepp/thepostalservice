#!/bin/bash

./clean.sh
cd utils
[ -d venv ] || python -m venv venv
source venv/bin/activate
pip install -r requirements.txt 2>&1 >/dev/null

# Wait for the db to come up
echo "In a separate shell, run \`docker-compose up db\`"
read -p "Press Enter to continue... " </dev/tty

./db_entry_generator.py
