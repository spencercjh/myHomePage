#!/usr/bin/env bash
source ~/.bash_profile
port=443
#根据端口号查询对应的pid
pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');

#杀掉对应的进程，如果pid不存在，则不执行
if [  -n  "$pid"  ];  then
    kill  -9  $pid;
fi
nohup java -jar home-0.0.1-SNAPSHOT.jar  > nohup.log 2>&1 &

