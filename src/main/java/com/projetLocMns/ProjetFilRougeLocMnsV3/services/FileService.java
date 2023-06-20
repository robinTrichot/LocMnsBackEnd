package com.projetLocMns.ProjetFilRougeLocMnsV3.services;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    @Value("${dossier.upload}")
    private String dossierUpload;

    @Value("${notice.upload}")
    private String noticeUpload;

    // donc une methode avec le fichier en param + son nom;
    public void transfertVersDossierUpload(MultipartFile fichier, String nomDuFichier) throws IOException {
        // path permet de declarer un chemin
        // /!\ attention au paths
        Path cheminDossierUpload = Paths.get(dossierUpload);

        //est ce que le chemin existe ? si oui on crée le dossier
        if (!Files.exists(cheminDossierUpload)) {
            Files.createDirectories(cheminDossierUpload);
        }

        Path destination = Paths.get(dossierUpload + "/" + nomDuFichier);


        // donc copy du fichier, à destionation de la destination créée ci-dessus;
        Files.copy(fichier.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
    }

    // ici c'est pour recuperer l'image par son nom
    // rappel : tableau de bytes car c'est de binaire une image
    // pour cela il faut la transformer cette image en tableau de byte, on utilise uen classep our ça

    public byte[] getImageByName(String nomImage) throws FileNotFoundException {

        Path destination = Paths.get(dossierUpload + "/" + nomImage);// retrieve the image by its name
        try {
            // donc ici on transforme bien l'image en binaire;
            return IOUtils.toByteArray(destination.toUri());
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }


    // Méthode pour transférer le fichier de notice vers le dossier de téléchargement
    public void transfertNoticeVersDossierUpload(MultipartFile notice, String nomDuFichier) throws IOException {
        Path cheminDossierUpload = Paths.get(noticeUpload);

        if (!Files.exists(cheminDossierUpload)) {
            Files.createDirectories(cheminDossierUpload);
        }

        Path destination = Paths.get(noticeUpload + "/" + nomDuFichier);

        Files.copy(notice.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
    }

    // Méthode pour récupérer le fichier de notice par son nom
    public byte[] getNoticeByName(String nomNotice) throws FileNotFoundException {
        Path destination = Paths.get(noticeUpload + "/" + nomNotice);

        try {
            return IOUtils.toByteArray(destination.toUri());
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
}
