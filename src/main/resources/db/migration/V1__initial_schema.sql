CREATE TABLE clients (
    client_id CHAR(36) PRIMARY KEY,
    name VARCHAR2(255),
    surname VARCHAR2(255),
    phone VARCHAR2(255),
    email VARCHAR2(255),
    registration_date TIMESTAMP
);
