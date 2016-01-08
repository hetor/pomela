#!/bin/bash

#getopts option_string variable
#当optstring以":"开头时，getopts会区分invalid option错误和miss option argument错误。
#invalid option时，varname会被设成?，$OPTARG是出问题的option；
#miss option argument时，varname会被设成:，$OPTARG是出问题的option。
#如果optstring不以":"开头，invalid option错误和miss option argument错误都会使varname被设成?，$OPTARG是出问题的option。

function usage() {
    echo "Invalid option: -$OPTARG"
    echo "Usage: `basename $0` [-qv] [-l LOGFILE] -d DEVICE input_file [input_file2...]"
    exit 1
}

while getopts ":t:p:o:hid" arg
do
    case $arg in
        t)
	    ssh2Test "$OPTARG"
	    ;;
	p)
	    ssh2Pre "$OPTARG"
	    ;;
	o)
	    echo "o's arg:$OPTARG"
	    ;;
	h)
	    ssh2Hotfix "1"
	    ;;
	i)
	    ssh2Integration "1"
	    ;;
        d)
            ssh2DDB "1"
	    ;;
	\?)
	    usage
	    ;;
	:)
	    echo "Miss option argument -$OPTARG <arg>"
	    ;;
    esac
done
