#!/bin/sh

docker-compose down -v
rm -rf ./db/mysql/*
mkdir -p ./db/mysql