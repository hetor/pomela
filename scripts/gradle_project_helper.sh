#eg: ./gradle_project_helper.sh pomela-project pomela-project-module

#!/bin/sh
PROJECT_NAME=$1      #'pomela-project'
MODULE_NAME=$2       #'pomela-project-module'
WEB_APP=Y

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
    touch build.gradle && echo -e "dependencies{\n}" > build.gradle && echo "create file: `pwd`/build.gradle"
fi

if [ -d src/main/java ]; then
    echo "already exist directory: `pwd`/src/main/java"
else
    mkdir -p src/main/java && echo "create directory: `pwd`/src/main/java"
fi

if [ -d src/main/resources ]; then
    echo "already exist directory: `pwd`/src/main/resources"
else
    mkdir -p src/main/resources&& echo "create directory: `pwd`/src/main/resources"
fi

if [ "$WEB_APP" == "Y" ] || [ "$WEB_APP" == "y" ]; then
    if [ -d src/main/webapp/WEB-INF/opPage ]; then
        echo "already exist directory: `pwd`/src/main/webapp/WEB-INF/opPage"
    else
        mkdir -p src/main/webapp/WEB-INF/opPage&& echo "create directory: `pwd`/src/main/webapp/WEB-INF/opPage"
    fi
    
    if [ -f src/main/webapp/WEB-INF/web.xml ]; then
        echo "already exist directory: `pwd`/src/main/webapp/WEB-INF/web.xml"
    else
        touch src/main/webapp/WEB-INF/web.xml && echo "create directory: `pwd`/src/main/webapp/WEB-INF/web.xml"
    fi
    
    if [ -d src/main/webapp/resources/js ]; then
        echo "already exist directory: `pwd`/src/main/webapp/resources/js"
    else
        mkdir -p src/main/webapp/resources/js&& echo "create directory: `pwd`/src/main/webapp/resources/js"
    fi
    
    if [ -d src/main/webapp/resources/css ]; then
        echo "already exist directory: `pwd`/src/main/webapp/resources/css"
    else
        mkdir -p src/main/webapp/resources/css&& echo "create directory: `pwd`/src/main/webapp/resources/css"
    fi
    
    sed -i.bak "1i\
        apply plugin: 'war'\n\
	" build.gradle
    
    rm build.gradle.bak
fi

if [ -d src/test/java ]; then
    echo "already exist directory: `pwd`/src/test/java"
else
    mkdir -p src/test/java && echo "create directory: `pwd`/src/test/java"
fi

if [ -d src/test/resources ]; then
    echo "already exist directory: `pwd`/src/test/resources"
else
    mkdir -p src/test/resources&& echo "create directory: `pwd`/src/test/resources"
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
