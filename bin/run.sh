#!/bin/bash

JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home
export JAVA_HOME

APPLICATION_PATH=~/Desktop/eclipse_37/work/dummy-gen

CLASSPATH=$(echo "$APPLICATION_PATH"/lib/*.jar | tr ' ' ':')
CLASSPATH=${CLASSPATH}:${APPLICATION_PATH}/json:.
echo ${CLASSPATH}

cd ${APPLICATION_PATH}/classes

pwd

${JAVA_HOME}/bin/java -Dfile.encoding=UTF-8 -cp ${CLASSPATH} dummygen.Main


