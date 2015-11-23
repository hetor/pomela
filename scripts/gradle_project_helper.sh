#!/bin/sh
PROJECT_NAME=$1      #'pomela-project'
MODULE_NAME=$2       #'pomela-project-module'

if [ -n "$PROJECT_NAME" ]; then
    echo "project name: $PROJECT_NAME"
else
    echo 'error: no project name'
    exit 1
fi

if [ -n "$MODULE_NAME" ]; then
    echo "module name: $MODULE_NAME"
else
    echo 'error: no module name'
    exit 1
fi

ROOT_PATH=`pwd`/..

cd $ROOT_PATH 

if [ -d $PROJECT_NAME ]; then
    echo "already exist directory: `pwd`/$PROJECT_NAME"
else
    mkdir -p $PROJECT_NAME && echo "create directory: `pwd`/$PROJECT_NAME"
fi

if [ -f ${PROJECT_NAME}.settings.gradle ]; then
    echo "already exist file: `pwd`/${PROJECT_NAME}.settings.gradle"
else
    touch ${PROJECT_NAME}.settings.gradle && echo "create file: `pwd`/${PROJECT_NAME}.settings.gradle"
fi


cd $ROOT_PATH/$PROJECT_NAME 

if [ -d $MODULE_NAME ]; then
    echo "already exist directory: `pwd`/$MODULE_NAME"
else
    mkdir -p $MODULE_NAME && echo "create directory: `pwd`/$MODULE_NAME"
fi


cd $ROOT_PATH/$PROJECT_NAME/$MODULE_NAME

if [ -f build.gradle ]; then
    echo "already exist file: `pwd`/build.gradle"
else
    touch build.gradle && echo "create file: `pwd`/build.gradle"
fi

if [ -d src/main/java ]; then
    echo "already exist directory: `pwd`/src/main/java"
else
     mkdir -p src/main/java && echo "create directory: `pwd`/src/main/java"
fi

if [ -d src/test/java ]; then
    echo "already exist directory: `pwd`/src/test/java"
else
    mkdir -p src/test/java && echo "create directory: `pwd`/src/test/java"
fi


cd $ROOT_PATH 
p_existing=$(grep "include '$PROJECT_NAME:$MODULE_NAME'" ${PROJECT_NAME}.settings.gradle)
if [ "$p_existing" == "" ]; then
    echo "include '$PROJECT_NAME:$MODULE_NAME'" >> ${PROJECT_NAME}.settings.gradle && echo "add setting: \"include '$PROJECT_NAME:$MODULE_NAME'\""
else
    echo "already exist setting: \"include '$PROJECT_NAME:$MODULE_NAME'\""
fi

s_existing=$(grep "apply from: '${PROJECT_NAME}.settings.gradle'" settings.gradle)
if [ "$s_existing" == "" ]; then
    echo "apply from: '${PROJECT_NAME}.settings.gradle'" >> settings.gradle && echo "add setting: \"apply from: '${PROJECT_NAME}.settings.gradle'\""
else
    echo "already exist setting: \"apply from: '${PROJECT_NAME}.settings.gradle'\""
fi
