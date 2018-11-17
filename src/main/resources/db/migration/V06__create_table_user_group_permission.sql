CREATE TABLE user (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(120) NOT NULL,
    active BOOLEAN DEFAULT true,
    birthday DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE grupo (
    id BIGINT(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
    id BIGINT(20) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_group (
    id_user BIGINT(20) NOT NULL,
    id_group BIGINT(20) NOT NULL,
    PRIMARY KEY (id_user, id_group),
    FOREIGN KEY (id_user) REFERENCES user(id),
    FOREIGN KEY (id_group) REFERENCES grupo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission_group (
    id_group BIGINT(20) NOT NULL,
    id_permission BIGINT(20) NOT NULL,
    PRIMARY KEY (id_group, id_permission),
    FOREIGN KEY (id_group) REFERENCES grupo(id),
    FOREIGN KEY (id_permission) REFERENCES permission(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
