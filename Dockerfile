FROM pluribuslabs/centos7-oracle-jdk-8
VOLUME /tmp
ARG JAR_FILE=target/*-exec.jar
COPY ${JAR_FILE} learn-openshift.jar
ENTRYPOINT ["java","-jar", "/learn-openshift.jar"]