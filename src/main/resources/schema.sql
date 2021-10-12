DROP TABLE IF EXISTS FILM;

CREATE TABLE FILM (id INT AUTO_INCREMENT  PRIMARY KEY,
                   titre VARCHAR(250) NOT NULL,
                   description VARCHAR(250)
);

DROP TABLE IF EXISTS ACTEUR;

CREATE TABLE ACTEUR (id INT AUTO_INCREMENT  PRIMARY KEY,
                     nom VARCHAR(250) NOT NULL,
                     prenom VARCHAR(250) NOT NULL,
                     film_id INT, FOREIGN KEY (film_id) REFERENCES FILM
);