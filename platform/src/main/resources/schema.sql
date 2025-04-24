CREATE TABLE reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    passenger_name VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    date DATE NOT NULL
);
