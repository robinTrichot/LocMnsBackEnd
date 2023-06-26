INSERT INTO `type_usager` (`wording`, `role`)
VALUES ("admin", 'ROLE_ADMIN'),
       ("extInterv", 'ROLE_USER'),
       ("intInterv", 'ROLE_USER'),
       ("trainee", 'ROLE_USER');

INSERT INTO subcontractor (designation, phone, number_street, name_street, postale_code, city)
VALUES ("aucuneidee", "0222144", 12, "ruevictorhugo", "12111", "Palavaslesflots");


INSERT INTO structure (wording, picture, phone, type, street_number, name_street, postal_code, city)
VALUES ("MNS", "ifa.png", 062454444, "ecole progra", 12, "rue france", "57000", "Metz"),
       ("IFABuisness", "ifa.png", 062454444, "marketing", 12, "rue france", "57000", "Metz"),
       ("IFAHottelerie", "ifa.png", 062454444, "ecole hoteliere", 12, "rue france", "57000", "Metz");

INSERT INTO trademark_material (wording)
VALUES ("asus"),
       ("phillips"),
       ("samsung");

INSERT INTO features (wording)
VALUES ("20Mhertz, 8 core, lol"),
       ("4gb"),
       ("8gb"),
       ("16gb"),
       ("32gb"),
       ("6gb");


INSERT INTO event_hire (name_event)
VALUES ("MNS - Metz Numérique School"),
       ("Arènes de Metz"),
       ("IFA - Institut Français des Affaires");


INSERT INTO material (wording, picture_name, trademark, structure, notice)
VALUES ("pc_asus", "asus1.jpg", 1, 1, "document.pdf"),
       ("cle_usb", "usb.jpg", 2, 1, "document1.pdf"),
       ("ecran_fixe", "ecran.jpg", 3, 1, "document2.pdf");

INSERT INTO user(password, lastname, firstname, phone, cell_Phone, mail, street_number, name_street,
                 postal_code, city, role, nom_image_profil)
VALUES ("$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Qui", "Franck", 0365814397,
        0665301317, "admin@mail.com", 12, "Rue de France", "54400", "Longwy", 1, "qui.jpg"),
       ("$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Ritchie", "Dennis", 0952356578, 0728356157,
        "rambo@mail.com", 28, "Impasse des Roses", "57330", "Hettange-Grande", 2, "ritchie.jpg"),
       ("$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Lovelace", "Ada", 0382834153, 0622483389,
        "ali@mail.com", 7, "Rue Edmond Goudchaux", "57000", "Metz", 2, "lovelace.png"),
       ("$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Turing", "Alan", 0499526234,
        062157466, "bernard@mail.com", 68, "Maida Vale", "EC2P 2E", "Londres", 2, "turing.jpg"),
       ("$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Hamilton", "Margaret", 0554827572,
        0682648876, "titi@mail.com", 1, "Rue des Roses", "57000", "Metz", 1, "hamilton.jpeg");


INSERT INTO connection (`status`, date_connection, date_logout, id_user)
VALUES (true, '1993-05-20', '1993-05-20', 1);

INSERT INTO copy (date_purchase, status, in_Stock, date_out_of_stock, serial_number, material, features)
VALUES ('1993-05-20', 'free', true, '1457-12-15', '14071789', 1, 1),
       ('1993-05-20', 'free', true, '1457-12-15', '14071789', 1, 1),
       ('1993-05-20', 'hired', true, '1457-12-15', '14071789', 1, 1),
       ('1993-05-20', 'free', true, '1457-12-15', '14071789', 1, 1),

       ('1993-05-20', "hired", true, '1457-12-15', "22558888", 2, 2),
       ('1993-05-20', "free", true, '1457-12-15', "56954", 2, 2),
       ('1993-05-20', "free", true, '1457-12-15', "054545", 2, 3),
       ('1993-05-20', "hired", true, '1457-12-15', "24545", 2, 4),
       ('1993-05-20', "hired", true, '1457-12-15', "22558888", 2, 2),
       ('1993-05-20', "free", true, '1457-12-15', "56954", 2, 3),
       ('1993-05-20', "free", true, '1457-12-15', "054545", 2, 4),
       ('1993-05-20', "hired", true, '1457-12-15', "24545", 2, 2),

       ('1993-05-20', "free", true, '1457-12-15', "56954", 3, 1),
       ('1993-05-20', "hired", true, '1457-12-15', "054545", 3, 1),
       ('1993-05-20', "free", true, '1457-12-15', "24545", 3, 1);

INSERT INTO hire (date_hire, date_real_return, date_planned_return, status, id_location, id_user, copy)
VALUES ('1993-05-20', '1993-05-20', '1993-05-20', "en cours", 1, 1, 1),
       ('1977-05-20', '1893-05-20', '1000-05-01', "en cours", 2, 2, 4);



INSERT INTO belongs_user_structure (user_id, structure_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (5, 3);

INSERT INTO failure (date_failure, date_send_repair, date_return_repair, number_quote, price_repair,
                     description_failure)
VALUES ('1993-05-20', '1993-05-20', '1993-05-20', 12, 12, "Disque dur HS");



