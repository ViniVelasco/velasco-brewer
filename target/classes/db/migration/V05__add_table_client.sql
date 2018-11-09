
CREATE TABLE client (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    peopleType VARCHAR(15) NOT NULL,
    cpf_cnpj VARCHAR(30),
    phone VARCHAR(20),
    email VARCHAR(50) NOT NULL,
    public_place VARCHAR(50),
    number VARCHAR(15),
    complement VARCHAR(20),
    cep VARCHAR(15),
    id_city BIGINT(20),
    FOREIGN KEY (id_city) REFERENCES city(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;