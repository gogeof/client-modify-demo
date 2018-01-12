#!/bin/bash

SCRIPTHOME=$(cd `dirname $0`;pwd)

/software/tomcat/apache-tomcat-8.5.24/bin/shutdown.sh
cd $SCRIPTHOME
mvn clean 
mvn package
rm -rf /software/tomcat/apache-tomcat-8.5.24/webapp/login*
cp $SCRIPTHOME/target/client-modify-demo.war /software/tomcat/apache-tomcat-8.5.24/webapps/
/software/tomcat/apache-tomcat-8.5.24/bin/startup.sh

