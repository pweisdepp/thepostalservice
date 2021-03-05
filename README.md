# CPSC5200 Group Project

## Docker

All servers can be started using docker-compose.

```
docker-compose up -d --build
# Serving on http://localhost:3000
docker-compose stop
```

The front end is now available at `http://localhost:3000`.
The API is available at `http://localhost:8080`.
It will continue serving until you stop the cluster with `docker-compose stop`.
You will need a docker daemon & CLi to use this.
On Linux, use the suggested packages (probably docker.io and docker-compose)
On OSX and Windows, I suggest docker desktop.
On Windows, additionally, I'd suggest WSL.

## Web

The web app is a React app with a node dev server.
To develop locally (without using Docker, suggested, see below), you will need a Node installation.
It's easiest to install node with nvm (or nvm-windows).
With node installed, change to the web/ directory, install dependencies, and start the server.
The page will be available at http://localhost:3000, and will live reload as you edit.
You can run tests after stopping the server (or in a second shell).

```
cd web
npm install
npm start
npm test
```

## Server

The server app is in [spring](https://spring.io/projects/spring-framework).

## DB

The database is MySQL.
It runs on a container named `db` on port 3306, but this port is only available within the docker-compose network.
To access the database from the host, use the `adminer` server at http://localhost:8000

* **System** MySQL
* **Server** `db`
* **User** `root`
* **Password** `password` (obviously only for local development, not for deployment)
* **Database** `postal`