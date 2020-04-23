docker build -t registry.cn-shanghai.aliyuncs.com/wxjava/com.ebs.platform .
docker login --username=wongxin@wongxin.com --password=Wongxin.com registry.cn-shanghai.aliyuncs.com
docker push registry.cn-shanghai.aliyuncs.com/wxjava/com.ebs.platform
pause