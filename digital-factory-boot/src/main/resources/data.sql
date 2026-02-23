DROP TABLE IF EXISTS STUDENT;

CREATE TABLE STUDENT (
                         ID VARCHAR(8) PRIMARY KEY,
                         NAME VARCHAR(10) NOT NULL,
                         LASTNAME VARCHAR(30) NOT NULL,
                         STATUS VARCHAR(8) NOT NULL CHECK (STATUS IN ('activo', 'inactivo')),
                         AGE INTEGER NOT NULL
);

INSERT INTO STUDENT (ID, NAME, LASTNAME, STATUS, AGE)
VALUES ('72332211', 'Paúl', 'Guevara', 'activo', 30);
