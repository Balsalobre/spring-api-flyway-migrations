ALTER TABLE clients
    ADD updated_at TIMESTAMP;

CREATE UNIQUE INDEX uq_clients_email ON clients(email);

CREATE UNIQUE INDEX uq_clients_phone ON clients(phone);