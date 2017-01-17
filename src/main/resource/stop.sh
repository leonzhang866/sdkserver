PID=$(ps auxf|grep "config_server" |grep -v grep|awk '{print $2}')
echo "kill $PID"
kill $PID