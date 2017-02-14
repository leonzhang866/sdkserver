

PID=$(ps auxf|grep "config_server" |grep -v grep|awk '{print $2}')
echo "kill $PID"
kill $PID
sleep 3
nohup java -Dlogback.configurationFile=/var/sdkserver/target/logback.xml -Djava.net.preferIPv4Stack=true -server -Xms8g -Xmx8g -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:InitiatingHeapOccupancyPercent=70 -XX:ParallelGCThreads=15 -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintHeapAtGC -Xloggc:/data/sdkserverlogs/g1gc-`date +'%Y-%m-%d_%H-%M-%S'`.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/sdkserverlogs/ -XX:ErrorFile=/data/sdkserverlogs/jvm-error-`date +'%Y-%m-%d_%H-%M-%S'`.log -jar config_server-1.0.jar > config_server-`date +'%Y-%m-%d_%H-%M-%S'`.log 2>&1&
echo $! > pid.txt    

                                                        