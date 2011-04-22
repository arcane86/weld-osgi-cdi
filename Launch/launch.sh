#!/bin/sh
#

clear;
echo Cleaning current app
rm -f ./modules/*SNAPSHOT.jar
echo Install new version
cd ..
#mvn clean install
echo skiping
echo Copy files
cp ./samples/paint/api/target/paint-api-1.0-SNAPSHOT.jar ./Launch/modules/
cp ./samples/paint/core/target/paint-core-1.0-SNAPSHOT.jar ./Launch/modules/
cp ./samples/paint/square/target/paint-square-1.0-SNAPSHOT.jar ./Launch/modules/
cp ./samples/paint/triangle/target/paint-triangle-1.0-SNAPSHOT.jar ./Launch/modules/
cp ./osgi-cdi/osgi-cdi-api/target/osgi-cdi-api-0.1-SNAPSHOT.jar ./Launch/modules/
cp ./osgi-cdi/osgi-cdi-core/target/osgi-cdi-core-0.1-SNAPSHOT.jar ./Launch/modules/
cp ./weld-osgi/target/weld-osgi-0.1-SNAPSHOT.jar ./Launch/modules/
echo Launching app
cd ./Launch/;
java -jar bin/felix.jar;
