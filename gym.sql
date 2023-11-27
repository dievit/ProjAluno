USE gym;

DROP TABLE IF EXISTS weight_evolution;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    cpf VARCHAR(11) PRIMARY KEY,
    name VARCHAR(30), 
    birthday DATE NOT NULL, 
    weight DECIMAL(10,2) NOT NULL, 
    height DECIMAL(10,2) NOT NULL
); 

CREATE TABLE weight_evolution (
    weight_evolution_id INT AUTO_INCREMENT PRIMARY KEY,
    client_id VARCHAR(11),
    register_date DATE,
    weight DOUBLE,
    FOREIGN KEY (client_id) REFERENCES users (cpf)
);
    
SELECT * FROM users;

SELECT * FROM weight_evolution;

UPDATE users SET weight = 100 WHERE cpf = "36271327850";

INSERT INTO weight_evolution (client_id, register_date, weight) VALUES (36271327850, "2023-11-22", 100);
 

 
SELECT weight_evolution.weight_evolution_id AS id, users.client_name, weight_evolution.register_date, weight_evolution.weight FROM weight_evolution JOIN users ON weight_evolution.client_id = users.cpf WHERE cpf = 36271327850;

SELECT * FROM weight_evolution WHERE client_id = 36271327850;




