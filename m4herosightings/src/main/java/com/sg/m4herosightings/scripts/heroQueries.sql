USE heroSighting;

INSERT INTO superpower (name, description)
    VALUES ("Flight", "Can Fly");

INSERT INTO superpower (name, description)
    VALUES ("Superspeed", "Can run really fast");

INSERT INTO hero (name, description, superpowerId)
    VALUES ("Heroman", "A random hero", 1);

INSERT INTO hero (name, description, superpowerId)
    VALUES ("Big Bird", "A really big bird", 2);

SELECT * FROM superpower;
SELECT * FROM hero;
SELECT * FROM sighting;
SELECT * FROM location;