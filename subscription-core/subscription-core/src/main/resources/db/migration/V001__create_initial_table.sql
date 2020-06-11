CREATE TABLE subscriber(
	id 	BIGINT NOT NULL auto_increment,
    name VARCHAR(255),
    last_name VARCHAR(255),
    cpf VARCHAR(11),
    email VARCHAR(255),
	cellphone VARCHAR(255),
	status VARCHAR(255),
    PRIMARY KEY(id)
)
COLLATE = 'utf8_general_ci'
ENGINE = InnoDB;
