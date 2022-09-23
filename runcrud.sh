#!/usr/bin/env bash

export CATALINA_HOME=~/tomcat

stop_tomcat()
{
   docker stop homebudget_tomcat_1
}

start_tomcat()
{
   docker start homebudget_tomcat_1
   end;
}

rename() {
   rm build/libs/crud.war
   if mv build/libs/tasks-0.0.1-SNAPSHOT.war build/libs/crud.war; then
      echo "Successfully renamed file"
   else
      echo "Cannot rename file"
      fail
   fi
}

copy_file() {
   if cp build/libs/crud.war $CATALINA_HOME/webapps; then
      start_tomcat
   else
      fail
   fi
}

fail() {
   echo "There were errors"
   exit 1;
}

end() {
   echo "Work is finished"
   exit 0
}

if ./gradlew build; then
   rename
   copy_file
else
   stop_tomcat
   fail
fi
