-- ------------------------------------------------------------
-- V5__create_tables.sql
-- Tables: USERS, ACCOUNTS, TRANSFERS
-- ------------------------------------------------------------

-- ----------------------------------------
-- 1. Users table
-- ----------------------------------------
CREATE TABLE users (
    user_id         VARCHAR2(36)  NOT NULL,
    national_id     VARCHAR2(20)  NOT NULL,
    full_name       VARCHAR2(200) NOT NULL,
    email           VARCHAR2(200) NOT NULL UNIQUE,
    phone_number    VARCHAR2(20)  NOT NULL,
    date_of_birth   DATE          NOT NULL,
    street          VARCHAR2(200) NOT NULL,
    city            VARCHAR2(100) NOT NULL,
    postal_code     VARCHAR2(10)  NOT NULL,
    country         VARCHAR2(100) NOT NULL,
    username        VARCHAR2(50)  NOT NULL UNIQUE,
    password_hash   VARCHAR2(200) NOT NULL,
    roles           CLOB CHECK (roles IS JSON),
    kyc_status      VARCHAR2(10)  NOT NULL,
    risk_rating     VARCHAR2(10)  NOT NULL,
    account_status  VARCHAR2(10)  NOT NULL,
    created_at      TIMESTAMP     DEFAULT SYSTIMESTAMP NOT NULL,
    updated_at      TIMESTAMP     DEFAULT SYSTIMESTAMP NOT NULL,
    created_by      VARCHAR2(50)  NOT NULL,
    updated_by      VARCHAR2(50)  NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

-- ----------------------------------------
-- 2. Accounts table
-- ----------------------------------------
CREATE TABLE accounts (
    account_id      VARCHAR2(36)  NOT NULL,
    user_id         VARCHAR2(36)  NOT NULL,
    account_number  VARCHAR2(30)  NOT NULL UNIQUE,
    balance         NUMBER(18,2)  DEFAULT 0 NOT NULL,
    currency        VARCHAR2(3)   DEFAULT 'EUR' NOT NULL,
    created_at      TIMESTAMP     DEFAULT SYSTIMESTAMP NOT NULL,
    updated_at      TIMESTAMP     DEFAULT SYSTIMESTAMP NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (account_id),
    CONSTRAINT fk_acct_user FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);

-- ----------------------------------------
-- 3. Transfers table
-- ----------------------------------------
CREATE TABLE transfers (
    transfer_id     VARCHAR2(36)  NOT NULL,
    from_account    VARCHAR2(36)  NOT NULL,
    to_account      VARCHAR2(36)  NOT NULL,
    amount          NUMBER(18,2)  NOT NULL,
    currency        VARCHAR2(3)   NOT NULL,
    status          VARCHAR2(10)  NOT NULL,
    created_at      TIMESTAMP     DEFAULT SYSTIMESTAMP NOT NULL,
    executed_at     TIMESTAMP,
    CONSTRAINT pk_transfers PRIMARY KEY (transfer_id),
    CONSTRAINT fk_trf_from FOREIGN KEY (from_account)
        REFERENCES accounts(account_id),
    CONSTRAINT fk_trf_to   FOREIGN KEY (to_account)
        REFERENCES accounts(account_id)
);
