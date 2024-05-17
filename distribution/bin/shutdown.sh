#!/bin/bash

cd `dirname $0`/../target
target_dir=`pwd`

pid=`ps ax | grep -i 'pan.server' | grep ${target_dir} | grep java | grep -v grep | awk '{print $1}'`
if [ -z "$pid" ] ; then
        echo "no pan server running."
        exit -1;
fi

echo "the pan server(${pid}) is running..."

kill ${pid}

echo "send shutdown request to pan server(${pid}) OK"
