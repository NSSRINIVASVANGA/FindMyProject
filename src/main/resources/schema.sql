CREATE TABLE if not exists project(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    budget DOUBLE
);

CREATE TABLE if not exists researcher(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    specialization TEXT
);

CREATE TABLE if not exists researcher_project(
    projectId INTEGER,
    researcherId INTEGER,
    PRIMARY KEY(projectId,researcherId),
    FOREIGN KEY (projectId) REFERENCES project(id),
    FOREIGN KEY (researcherId) REFERENCES researcher(id)
);