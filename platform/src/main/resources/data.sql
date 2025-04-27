INSERT INTO reservation (passenger_name, destination, date) VALUES
('Alice', 'Douala', '2025-04-30'),
('Bob', 'Yaoundé', '2025-05-01');

-- Création du rôle admin
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');

-- Ensure the password is hashed using BCrypt
INSERT INTO users (id, username, password, agency_name) VALUES (1, 'admin', '$2a$12$9opD.AOnMCjrZLuhSDCsOuIBuwFU8FCKTVJSWTXIB5CxR5C5it6o2', 'AgenceAlpha');

-- Association de l’utilisateur avec le rôle admin
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
