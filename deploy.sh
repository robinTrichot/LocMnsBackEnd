#!/bin/bash

#Mettre à jour le code source
git pull

#Construire le projet avec Maven
sudo bash mvnw --settings /home/debian/.m2/settings.xml package -Pprod -Psysadmin

#Construire l'image Docker
docker build --no-cache -t demospringboot .

#Arreter le conteneur existant
docker stop conteneur-spring-demo

#Supprimer le conteneur existant
docker rm conteneur-spring-demo

#Lancer un nouveau conteneur
docker run -d --net backend --ip 172.18.0.4 --name=conteneur-spring-demo -p 8080:8080 -v uploaded_files:/uploads demospringboot