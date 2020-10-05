USE heroSighting;

INSERT INTO superpower(name, description)
    VALUES("Flight", "Can Fly");

INSERT INTO hero(name, description, superpowerId)
    VALUES("Heroman", "A random hero", 1);

SELECT * FROM superpower;
SELECT * FROM hero;