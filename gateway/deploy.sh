#!/bin/bash

echo " Building Alphamon Gateway.."
OLD_VER=$ALPHAMON_GATEWAY_VER
if [ -z "$ALPHAMON_GATEWAY_VER" ]
then
    export ALPHAMON_GATEWAY_VER=0.1
else
   export ALPHAMON_GATEWAY_VER=$(echo $ALPHAMON_GATEWAY_VER+.1|bc)
fi
echo "Version-"$ALPHAMON_GATEWAY_VER

#Create FAT JAR for docker image
mvn install

docker stop alphamon-gateway:"DEV"$OLD_VER
echo "---- Build docker image START -----"
docker build -t alphamon-gateway:"DEV"$ALPHAMON_GATEWAY_VER .
echo "---- Build docker image END -----"

echo "---- RUN docker image -----"
docker run -d -p 9090:9090 --memory="512m" alphamon-gateway:"DEV"$ALPHAMON_GATEWAY_VER
