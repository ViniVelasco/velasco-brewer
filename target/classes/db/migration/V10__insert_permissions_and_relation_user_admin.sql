INSERT INTO permission VALUES (1, 'ROLE_CADASTRAR_CIDADE');
INSERT INTO permission VALUES (2, 'ROLE_CADASTRAR_USUARIO');

INSERT INTO permission_group (id_group, id_permission) VALUES (1, 1);
INSERT INTO permission_group (id_group, id_permission) VALUES (1, 2);

INSERT INTO user_group (id_user, id_group) VALUES(
	(SELECT id FROM user where email = 'admin@brewer.com'), 1);