#!/bin/bash

echo " Building Alphamon.."

if [ -z "$ALPHAMON_VER" ]
then
    export ALPHAMON_VER=0.1
else
   export ALPHAMON_VER=$(echo $ALPHAMON_VER+.1|bc)
fi
echo "Version-"$ALPHAMON_VER

#Create FAT JAR for docker image
mvn install

docker stop alphamon-mono
echo "---- Build docker image START -----"
docker build -t alphamon-mono:"DEV"$ALPHAMON_VER .
echo "---- Build docker image END -----"

echo "---- RUN docker image -----"
docker run  -d -p 8081:8081 --memory="512m" alphamon-mono:"DEV"$ALPHAMON_VER
