#!/bin/sh

PROJECT_NAME=$1      #'pomela-project'
MODULE_NAME=$2       #'pomela-project-module'

if [ -n "$PROJECT_NAME" ]; then
    echo -e "project name: $PROJECT_NAME"
else
    echo -e 'error: no project name'
    exit 1
fi

if [ -n "$MODULE_NAME" ]; then
    echo -e "module name: $MODULE_NAME"
else
    echo -e 'error: no module name'
    exit 1
fi

ROOT_PATH=`pwd`/..

cd $ROOT_PATH 

if [ -d $PROJECT_NAME ]; then
    echo -e "already exist directory: `pwd`/$PROJECT_NAME"
else
    mkdir -p $PROJECT_NAME && echo -e "create directory: `pwd`/$PROJECT_NAME"
fi

if [ -e ${PROJECT_NAME}.settings.gradle ]; then
    echo -e "already exist file: `pwd`/${PROJECT_NAME}.settings.gradle"
else
    touch ${PROJECT_NAME}.settings.gradle && echo -e "create file: `pwd`/${PROJECT_NAME}.settings.gradle"
fi


cd $ROOT_PATH/$PROJECT_NAME 

if [ -d $MODULE_NAME ]; then
    echo -e "already exist directory: `pwd`/$MODULE_NAME"
else
    mkdir -p $MODULE_NAME && echo -e "create directory: `pwd`/$MODULE_NAME"
fi


cd $ROOT_PATH/$PROJECT_NAME/$MODULE_NAME

if [ -e build.gradle ]; then
    echo -e "already exist file: `pwd`/build.gradle"
else
    touch build.gradle && echo -e "create file: `pwd`/build.gradle"
fi

if [ -d src/main/java ]; then
    echo -e "already exist directory: `pwd`/src/main/java"
else
     mkdir -p src/main/java && echo -e "create directory: `pwd`/src/main/java"
fi

if [ -d src/test/java ]; then
    echo -e "already exist directory: `pwd`/src/test/java"
else
    mkdir -p src/test/java && echo -e "create directory: `pwd`/src/test/java"
fi


cd $ROOT_PATH 
grep "include $PROJECT_NAME:$MODULE_NAME" ${PROJECT_NAME}.settings.gradle || sed -i "$a\
    include $PROJECT_NAME:$MODULE_NAME\
    " ${PROJECT_NAME}.settings.gradle
