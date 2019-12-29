#!/bin/bash
  
echo " Building Alphamon Discv Service.."

OLD_VER=$ALPHAMON_DISCV_VER
if [ -z "$ALPHAMON_DISCV_VER" ]
then
    export ALPHAMON_DISCV_VER=0.1
else
   export ALPHAMON_DISCV_VER=$(echo $ALPHAMON_DISCV_VER+.1|bc)
fi
echo "Version-"$ALPHAMON_DISCV_VER

#Create FAT JAR for docker image
mvn install

docker stop alphamon-auth:$OLD_VER
echo "---- Build docker image START -----"
docker build -t alphamon-auth:"DEV"$ALPHAMON_DISCV_VER .
echo "---- Build docker image END -----"

echo "---- RUN docker image -----"
docker run --env MONGO_URL -d -p 8761:8761 --memory="512m" alphamon-auth:"DEV"$ALPHAMON_DISCV_VER

