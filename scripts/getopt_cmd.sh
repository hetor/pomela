#!/bin/bash

# -o或--options选项后面接可接受的短选项，如ab:c::，表示可接受的短选项为-a -b -c，其中-a选项不接参数，-b选项后必须接参数，-c选项的参数为可选的
# -l或--long选项后面接可接受的长选项，如along,blong:,clong::用逗号分开，冒号的意义同短选项。
# -n选项后接选项解析出错时的提示信息
# --举一个例子比较好理解：
    #我们要创建一个名字为 "-f"的目录你会怎么办？
    # mkdir -f #不成功，因为-f会被mkdir当作选项来解析，这时就可以使用
    # mkdir -- -f 这样-f就不会被作为选项。

ERR_MSG=`basename $0`
ARGS=`getopt -o ab:c:: --long along,blong:,clong:: -n "${ERR_MSG}" -- "$@"`
if [ $? != 0 ]; then
    echo "Usage: `basename $0` [-qv] [-l LOGFILE] -d DEVICE input_file [input_file2...]"
    exit 1
fi

#echo $ARGS
#将规范化后的命令行参数分配至位置参数($1,$2,...)
eval set -- "${ARGS}"

while true
do
    case "$1" in
        -a|--along) 
            echo "Option a";
            shift
            ;;
        -b|--blong)
            echo "Option b, argument $2";
            shift 2
            ;;
        -c|--clong)
            case "$2" in
                "")
                    echo "Option c, no argument";
                    shift 2  
                    ;;
                *)
                    echo "Option c, argument $2";
                    shift 2;
                    ;;
            esac
            ;;
        --)
            shift
            break
            ;;
        *)
            echo "Internal error!"
            exit 1
            ;;
    esac
done

#处理剩余的参数
for arg in $@
do
    echo "processing $arg"
done