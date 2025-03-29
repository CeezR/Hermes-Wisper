-- Insert a test user
INSERT INTO app_user (id, username, email) VALUES (1, 'testuser', 'testuser@example.com');

-- Insert a ConsciousEntity linked to the test user
INSERT INTO conscious_entity (id, user_id) VALUES (1, 1);

-- Insert positive visions linked to the ConsciousEntity
INSERT INTO vision (id, user_id, conscious_entity_id, dimension, description, type) 
VALUES 
(1, 1, 1, 'Career', 'Achieve a leadership position', 'POSITIVE'),
(2, 1, 1, 'Health', 'Maintain a healthy lifestyle', 'POSITIVE');

-- Insert negative visions linked to the ConsciousEntity
INSERT INTO vision (id, user_id, conscious_entity_id, dimension, description, type) 
VALUES 
(3, 1, 1, 'Relationships', 'Lose contact with family', 'NEGATIVE'),
(4, 1, 1, 'Career', 'Fail to meet career goals', 'NEGATIVE');