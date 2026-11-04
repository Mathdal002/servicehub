
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_users_company
        FOREIGN KEY (company_id)
        REFERENCES companies(id),
    CONSTRAINT uk_users_email
        UNIQUE (email),
    CONSTRAINT ck_users_role
        CHECK (role IN (
            'PLATFORM_ADMIN',
            'COMPANY_ADMIN',
            'COMPANY_USER'
        )),
    CONSTRAINT ck_users_company
        CHECK (
            (role = 'PLATFORM_ADMIN' AND company_id IS NULL)
            OR
            (role IN ('COMPANY_ADMIN', 'COMPANY_USER')
                AND company_id IS NOT NULL)
        )
);
