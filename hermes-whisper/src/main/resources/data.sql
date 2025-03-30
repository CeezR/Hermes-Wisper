-- Insert a test user
INSERT INTO app_user (id, username, email) VALUES (1, 'testuser', 'testuser@example.com');


INSERT INTO vision (id, user_id, dimension, description, type) 
VALUES 
(1, 1, 'Career', 'Achieve a leadership position', 'POSITIVE'),
(2, 1, 'Health', 'Maintain a healthy lifestyle', 'POSITIVE');


INSERT INTO vision (id, user_id, dimension, description, type) 
VALUES 
(3, 1, 'Relationships', 'Lose contact with family', 'NEGATIVE'),
(4, 1, 'Career', 'Fail to meet career goals', 'NEGATIVE');