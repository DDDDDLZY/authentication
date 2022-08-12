#所需基础镜像
#FROM openjdk:8-jdk-alpine
FROM openjdk:11-jre-slim
MAINTAINER liuzhanyang<15550835508@163.com>
#具名挂载
VOLUME /docker/springcloud
#端口暴露80
EXPOSE 80
#将我们的jar 添加到容器中 并取名为application.jar
ADD formal-gateway.jar formal-gateway.jar
# 执行命令
ENTRYPOINT ["java","-Xmx350m","-jar" ,"formal-gateway.jar"]
