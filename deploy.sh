#!/bin/bash

#Mettre à jour le code source
git pull

#Construire le projet avec Maven
sudo bash mvnw --settings /home/debian/.m2/settings.xml package -Pprod

#Construire l'image Docker
docker build --no-cache -t loc-mns-back-end .

#Arreter le conteneur existant
docker stop conteneur-loc-mns

#Supprimer le conteneur existant
docker rm conteneur-loc-mns

#Lancer un nouveau conteneur
docker run -d --net backend --ip 172.18.0.4 --name=conteneur-loc-mns -p 8080:8080 -v uploaded_files:/uploads loc-mns-back-end