#!/bin/bash
  
echo " Building Alphamon Auth Service.."

OLD_VER=$ALPHAMON_AUTH_VER
if [ -z "$ALPHAMON_AUTH_VER" ]
then
    export ALPHAMON_AUTH_VER=0.1
else
   export ALPHAMON_AUTH_VER=$(echo $ALPHAMON_AUTH_VER+.1|bc)
fi
echo "Version-"$ALPHAMON_AUTH_VER

#Create FAT JAR for docker image
mvn install

docker stop alphamon-auth:$OLD_VER
echo "---- Build docker image START -----"
docker build -t alphamon-auth:"DEV"$ALPHAMON_AUTH_VER .
echo "---- Build docker image END -----"

echo "---- RUN docker image -----"
docker run --network=host --env MONGO_URL -d -p 8080:8080 --memory="512m" alphamon-auth:"DEV"$ALPHAMON_AUTH_VER

