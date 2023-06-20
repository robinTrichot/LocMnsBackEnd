
FROM tomcat:8.5
COPY target/ProjetFilRougeLocMnsV3-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ProjetFilRougeLocMnsV3-0.0.1-SNAPSHOT.war
COPY ./tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY ./manager.xml /usr/local/tomcat/conf/Catalina/localhost/manager.xml
ENTRYPOINT ["/bin/bash", "/usr/local/tomcat/bin/catalina.sh", "run"]

