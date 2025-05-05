-- ------------------------------------------------------------
-- V6__insert_sample_data.sql
-- Inserts: USERS, ACCOUNTS, TRANSFERS
-- ------------------------------------------------------------

-- ----------------------------------------
-- Helper function to generate UUID in Java format
-- ----------------------------------------
CREATE OR REPLACE FUNCTION generate_uuid RETURN VARCHAR2 IS
    l_uuid RAW(16);
    l_hex VARCHAR2(32);
BEGIN
    l_uuid := SYS_GUID();
    l_hex := RAWTOHEX(l_uuid);
    -- Format as 8-4-4-4-12 UUID format (Java style)
    RETURN SUBSTR(l_hex, 1, 8) || '-' ||
           SUBSTR(l_hex, 9, 4) || '-' ||
           SUBSTR(l_hex, 13, 4) || '-' ||
           SUBSTR(l_hex, 17, 4) || '-' ||
           SUBSTR(l_hex, 21);
END;
/

-- ----------------------------------------
-- 1. Insert into USERS table
-- ----------------------------------------
DECLARE
    v_user_id1 VARCHAR2(36);
    v_user_id2 VARCHAR2(36);
    v_user_id3 VARCHAR2(36);
    v_admin_user VARCHAR2(50) := 'system';
BEGIN
    v_user_id1 := generate_uuid();
    v_user_id2 := generate_uuid();
    v_user_id3 := generate_uuid();

    -- User 1: Admin user
    INSERT INTO users (
        user_id, national_id, full_name, email, phone_number, date_of_birth,
        street, city, postal_code, country, username, password_hash,
        roles, kyc_status, risk_rating, account_status, created_by, updated_by
    ) VALUES (
        v_user_id1,
        'A1234567B',
        'Admin User',
        'admin@example.com',
        '+34666111222',
        TO_DATE('1980-01-15', 'YYYY-MM-DD'),
        'Calle Principal 123',
        'Madrid',
        '28001',
        'España',
        'admin',
        'bcrypt:$2a$10$dL4JJm16zu/WRQmRpKJ4vuesX5kbGMkXOJ.Y9YL.yxJPfP5B7zyFi', -- 'password123'
        '["ADMIN", "USER"]',
        'VERIFIED',
        'LOW',
        'ACTIVE',
        v_admin_user,
        v_admin_user
    );

    -- User 2: Regular user
    INSERT INTO users (
        user_id, national_id, full_name, email, phone_number, date_of_birth,
        street, city, postal_code, country, username, password_hash,
        roles, kyc_status, risk_rating, account_status, created_by, updated_by
    ) VALUES (
        v_user_id2,
        'B9876543C',
        'María García',
        'maria@example.com',
        '+34666333444',
        TO_DATE('1990-05-20', 'YYYY-MM-DD'),
        'Avenida Libertad 45',
        'Barcelona',
        '08001',
        'España',
        'maria',
        'bcrypt:$2a$10$dL4JJm16zu/WRQmRpKJ4vuesX5kbGMkXOJ.Y9YL.yxJPfP5B7zyFi', -- 'password123'
        '["USER"]',
        'VERIFIED',
        'LOW',
        'ACTIVE',
        v_admin_user,
        v_admin_user
    );

    -- User 3: Premium user
    INSERT INTO users (
        user_id, national_id, full_name, email, phone_number, date_of_birth,
        street, city, postal_code, country, username, password_hash,
        roles, kyc_status, risk_rating, account_status, created_by, updated_by
    ) VALUES (
        v_user_id3,
        'C5432198D',
        'Carlos Rodríguez',
        'carlos@example.com',
        '+34666555666',
        TO_DATE('1985-11-30', 'YYYY-MM-DD'),
        'Plaza Mayor 7',
        'Valencia',
        '46001',
        'España',
        'carlos',
        'bcrypt:$2a$10$dL4JJm16zu/WRQmRpKJ4vuesX5kbGMkXOJ.Y9YL.yxJPfP5B7zyFi', -- 'password123'
        '["USER", "PREMIUM"]',
        'VERIFIED',
        'MEDIUM',
        'ACTIVE',
        v_admin_user,
        v_admin_user
    );

    -- ----------------------------------------
    -- 2. Insert into ACCOUNTS table
    -- ----------------------------------------
    DECLARE
        v_account_id1 VARCHAR2(36);
        v_account_id2 VARCHAR2(36);
        v_account_id3 VARCHAR2(36);
    BEGIN
        v_account_id1 := generate_uuid();
        v_account_id2 := generate_uuid();
        v_account_id3 := generate_uuid();

        -- Account 1: Admin user's account
        INSERT INTO accounts (
            account_id, user_id, account_number, balance, currency
        ) VALUES (
            v_account_id1,
            v_user_id1,
            'ES9121000418450200051332',
            10000.00,
            'EUR'
        );

        -- Account 2: María's account
        INSERT INTO accounts (
            account_id, user_id, account_number, balance, currency
        ) VALUES (
            v_account_id2,
            v_user_id2,
            'ES7921000813610123456789',
            5000.00,
            'EUR'
        );

        -- Account 3: Carlos's account
        INSERT INTO accounts (
            account_id, user_id, account_number, balance, currency
        ) VALUES (
            v_account_id3,
            v_user_id3,
            'ES1000492352082414205416',
            7500.00,
            'EUR'
        );

        -- ----------------------------------------
        -- 3. Insert into TRANSFERS table
        -- ----------------------------------------
        DECLARE
            v_transfer_id1 VARCHAR2(36);
            v_transfer_id2 VARCHAR2(36);
            v_transfer_id3 VARCHAR2(36);
        BEGIN
            v_transfer_id1 := generate_uuid();
            v_transfer_id2 := generate_uuid();
            v_transfer_id3 := generate_uuid();

            -- Transfer 1: Admin to María
            INSERT INTO transfers (
                transfer_id, from_account, to_account, amount, currency, status, created_at, executed_at
            ) VALUES (
                v_transfer_id1,
                v_account_id1,
                v_account_id2,
                500.00,
                'EUR',
                'COMPLETED',
                SYSTIMESTAMP - INTERVAL '2' DAY,
                SYSTIMESTAMP - INTERVAL '2' DAY
            );

            -- Transfer 2: María to Carlos
            INSERT INTO transfers (
                transfer_id, from_account, to_account, amount, currency, status, created_at, executed_at
            ) VALUES (
                v_transfer_id2,
                v_account_id2,
                v_account_id3,
                250.00,
                'EUR',
                'COMPLETED',
                SYSTIMESTAMP - INTERVAL '1' DAY,
                SYSTIMESTAMP - INTERVAL '1' DAY
            );

            -- Transfer 3: Carlos to Admin (pending)
            INSERT INTO transfers (
                transfer_id, from_account, to_account, amount, currency, status, created_at
            ) VALUES (
                v_transfer_id3,
                v_account_id3,
                v_account_id1,
                350.00,
                'EUR',
                'PENDING',
                SYSTIMESTAMP
            );
        END;
    END;
END;
/

-- Drop the helper function as it's no longer needed
DROP FUNCTION generate_uuid;