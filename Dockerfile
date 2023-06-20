
FROM tomcat
COPY target/ProjetFilRougeLocMnsV3-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps-javaee/ROOT.war
ENTRYPOINT ["/bin/bash", "/usr/local/tomcat/bin/catalina.sh", "run"]