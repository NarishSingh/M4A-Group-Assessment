DROP DATABASE IF EXISTS heroSighting;
CREATE DATABASE heroSighting;

USE heroSighting;

CREATE TABLE location (
    locationId INT PRIMARY KEY AUTO_INCREMENT,
    latitude DECIMAL(8,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
    name VARCHAR(50),
    description VARCHAR(255),
    street VARCHAR(100) NOT NULL,
    city VARCHAR(30) NOT NULL,
    state CHAR(2) NOT NULL,
    zipcode CHAR(5) NOT NULL
);

CREATE TABLE organization (
    organizationId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    phone CHAR(12),
    email VARCHAR(50),
    locationId INT NOT NULL,
    CONSTRAINT `fk_organization_location` FOREIGN KEY (locationId)
        REFERENCES location (locationId)
);

CREATE TABLE superpower (
    superpowerId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE hero (
    heroId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    superpowerId INT NOT NULL,
    CONSTRAINT `fk_hero_superpower` FOREIGN KEY (superpowerId)
        REFERENCES superpower (superpowerId)
);

-- bridge table
CREATE TABLE heroOrganization (
    heroId INT NOT NULL,
    organizationId INT NOT NULL,
    PRIMARY KEY (heroId, organizationId),
    CONSTRAINT `fk_heroOrganization_hero` FOREIGN KEY (heroId)
        REFERENCES hero (heroId),
    CONSTRAINT `fk_heroOrganization_organization` FOREIGN KEY (organizationId)
        REFERENCES organization (organizationId)
);

CREATE TABLE sighting (
    sightingId INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    heroId INT NOT NULL,
    locationId INT NOT NULL,
    CONSTRAINT `fk_sighting_hero` FOREIGN KEY (heroId)
        REFERENCES hero (heroId),
    CONSTRAINT `fk_sighting_location` FOREIGN KEY (locationId)
        REFERENCES location (locationId)
);