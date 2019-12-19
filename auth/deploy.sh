#!/bin/bash
  
echo " Building Alphamon.."

if [ -z "$ALPHAMON_AUTH_VER" ]
then
    export ALPHAMON_AUTH_VER=0.1
else
   export ALPHAMON_AUTH_VER=$(echo $ALPHAMON_AUTH_VER+.1|bc)
fi
echo "Version-"$ALPHAMON_AUTH_VER

#Create FAT JAR for docker image
mvn install

docker stop alphamon-mono
echo "---- Build docker image START -----"
docker build -t alphamon-auth:"DEV"$ALPHAMON_AUTH_VER .
echo "---- Build docker image END -----"

echo "---- RUN docker image -----"
docker run -d -p 8080:8080 alphamon-auth:"DEV"$ALPHAMON_AUTH_VER

