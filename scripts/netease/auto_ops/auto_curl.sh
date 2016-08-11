#!/bin/bash
set LANG="gb2312"
export LANG

function do_it() {
    for param in `cat /tmp/tmp9`
    do
        echo "http://223.252.196.116:8191/manualinvoke/genPlatformPrivateField?$param"
        #curl "http://223.252.196.116:8191/manualinvoke/genPlatformPrivateField?$param"
    done
    wget "http://223.252.196.116:8191/manualinvoke/genPlatformPrivateField?orderIds=2016022121443111006166434&identityNames=叶静&identityIds=440602197407011828"
}

do_it
