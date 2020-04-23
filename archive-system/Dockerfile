FROM java:8

label maintainer="raowei <539277@qq.com>"

copy target/general-archive-0.0.1-SNAPSHOT.jar /usr/general-archive.jar

#定义时区参数
env TZ Asia/Shanghai

#设置时区
run ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo "$TZ" > /etc/timezone

expose 8191

CMD ["java","-jar","/usr/general-archive.jar"]