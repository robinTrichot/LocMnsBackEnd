INSERT INTO `type_usager` (`wording`, `role`)
VALUES ("admin", 'ROLE_ADMIN'),
       ("extInterv", 'ROLE_USER'),
       ("intInterv", 'ROLE_USER'),
       ("trainee", 'ROLE_USER');

INSERT INTO subcontractor (designation, phone, number_street, name_street, postale_code, city)
VALUES ("aucuneidee", "0222144", 12, "ruevictorhugo", "12111", "Palavaslesflots");


INSERT INTO structure (wording, picture, phone, type, street_number, name_street, postal_code, city)
VALUES ("IFA", "ifa.png", 062454444, "ecole progra", 12, "rue france", "57000", "Metz");

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

INSERT INTO notice (doc)
VALUES ("un pc assez fragile, faites, attention ! ");


INSERT INTO event_hire (title, summary, picture, observations)
VALUES ("Simple commande", "location", "loc.png", "aucune");


INSERT INTO material (wording, picture, trademark, structure)
VALUES ("pc_asus", "asus1.png", 1, 1),
       ("cle_usb", "usb.png", 2, 1),
       ("ecran_fixe", "ecran.png", 3, 1);

INSERT INTO user(login, password, lastname, firstname, phone, cell_Phone, mail, street_number, name_street,
                 postal_code, city, role)
VALUES ("Franck57", "$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Qui?", "Franck", 552527572,
        51455545, "admin@com", 12, "rue de france",
        "1244g", "longwy", 1),
       ("Jonh54", "$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "rambo", "Jhon", 64255444, 51455545,
        "rambo@com", 12, "rue de france",
        "1244g", "longwy", 2),
       ("Ali27", "$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Boulala", "Ali", 75275275, 51455545,
        "aLI@com", 12, "rue de france",
        "1244g", "longwy", 2),
       ("Jojo59", "$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Benrnard", "Jojo", 75275444,
        51455545, "bERNARD@com", 12,
        "rue de france", "1244g", "longwy", 2),
       ("Titi57", "$2y$10$ODv3tF1YNzlMGHueQE4UMO4jRZyMycigfA5SRRuv0VUKzkEK9WyY.", "Titi", "Toto", 554827572,
        22455545, "titi@com", 1, "Rue des Roses",
        "57000", "Metz", 1);


INSERT INTO connection (`status`, date_connection, date_logout, id_user)
VALUES (true, '1993-05-20', '1993-05-20', 1);

INSERT INTO copy (date_purchase, status, out_of_stock, date_out_of_stock, serial_number, material, features)
VALUES ('1993-05-20', 'free', '1454-12-12', '1457-12-15', '14071789', 1, 1),
       ('1993-05-20', 'free', '1454-12-12', '1457-12-15', '14071789', 1, 1),
       ('1993-05-20', 'hired', '1454-12-12', '1457-12-15', '14071789', 1, 1),
       ('1993-05-20', 'free', '1454-12-12', '1457-12-15', '14071789', 1, 1),

       ('1993-05-20', "free", '1454-12-12', '1457-12-15', "22558888", 2, 2),
       ('1993-05-20', "free", '1454-12-12', '1457-12-15', "56954", 2, 2),
       ('1993-05-20', "hired", '1454-12-12', '1457-12-15', "054545", 2, 3),
       ('1993-05-20', "hired", '1454-12-12', '1457-12-15', "24545", 2, 4),
       ('1993-05-20', "hired", '1454-12-12', '1457-12-15', "22558888", 2, 2),
       ('1993-05-20', "hired", '1454-12-12', '1457-12-15', "56954", 2, 3),
       ('1993-05-20', "free", '1454-12-12', '1457-12-15', "054545", 2, 4),
       ('1993-05-20', "free", '1454-12-12', '1457-12-15', "24545", 2, 2),

       ('1993-05-20', "free", '1454-12-12', '1457-12-15', "56954", 3, 1),
       ('1993-05-20', "hired", '1454-12-12', '1457-12-15', "054545", 3, 1),
       ('1993-05-20', "free", '1454-12-12', '1457-12-15', "24545", 3, 1);

INSERT INTO hire (date_hire, date_real_return, date_planned_return, status, id_location, id_user, id_copy)
VALUES ('1993-05-20', '1993-05-20', '1993-05-20', "rouleMaPoule", 1, 1, 1),
       ('1977-05-20', '1893-05-20', '1000-00-01', "2emeEssai", 1, 2, 4);

INSERT INTO owns_material_notice (material_id, notice_id)
VALUES (1, 1);

INSERT INTO belongs_user_structure (user_id, structure_id)
VALUES (1, 1);

INSERT INTO failure (date_failure, date_send_repair, date_return_repair, number_quote, price_repair,
                     description_failure)
VALUES (12 / 21 / 1211, 12 / 20 / 1210, 02 / 12 / 1265, 12, 550.45, "grosse casse hein ! ça va couter cher");



