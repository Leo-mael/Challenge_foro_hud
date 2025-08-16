INSERT INTO usuario (nombre, correo_electronico, contrasena) VALUES
('Leo Mael', 'leo.alura@example.com', '$2a$12$25lApN4ZLmf/OVxX9JwlAubFpnlXwKS0N.5KO7I/i1EW8O1SnC0rO'), -- contrasena: 123456
('Ana Perez', 'ana.perez@example.com', '$2a$12$BQtEMXOIzaexy1ZXi0jkOOURuvkb1dV/ZDOkN8uJwnjb2xN74pJUa'), -- contrasena: contrasena123
('Luis Martinez', 'luis.martinez@example.com', '$2a$12$9bAbq5OjVKbmX65GvNMJ5ey19gwMB5Wkrkx.Cck/HwT307jmj3yQq'), -- contrasena: luis456
('Carla Gomez', 'carla.gomez@example.com', '$2a$12$ucgv9mfDm8AXtkdxp0E7KexwO907vgJVu3jjVAyOEl4flqgnYxTGq'); -- -- contrasena: carla789

INSERT INTO curso (nombre, categoria) VALUES
('Programación en Java', 'PROGRAMACION'),
('Diseño Web', 'DISENO'),
('Bases de Datos', 'BASE_DE_DATOS');

INSERT INTO topico (titulo, mensaje, autor, curso) VALUES
('¿Como uso Spring Boot?', 'Estoy comenzando con Spring Boot y tengo algunas duadas.', 1, 1),
('Problemas con CSS Grid', 'Mi diseño no se alinea correctamente. ¿Alguien puede ayudar?', 2, 2),
('Diferencias entre SQL y NoSQL', '¿Cuáles son las ventajas de cada uno?', 3, 3);

INSERT INTO respuesta (mensaje, topico, autor, solucion) VALUES
('Puedes comenzar por aprender los tipos de datos básicos.', 1, 2, TRUE),
('Revisa si estás usando correctamente las áreas de grid.', 2, 1, FALSE),
('SQL es relacional, NoSQL es más flexible.', 3, 2, TRUE);
