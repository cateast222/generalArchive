from java:8

maintainer ljj <111@qq.com>

add target/platform-0.0.1-SNAPSHOT.jar   /usr/aaa.jar

run bash -c 'touch /usr/aaa.jar'

run echo "Asia/Shanghai" > /etc/timezone && dpkg-reconfigure -f nointeractive tzdata

expose 8090

cmd ["java","-jar","/usr/aaa.jar"]