INSERT INTO users (username, password, first_name, last_name, phone)
VALUES   ('admin', '$2y$12$w3LCJwUyEg10JD1rJarm.O1NZLr6jx15fqp1p0fmBGXRnwsnC9xo2', 'n/a', 'n/a', 'n/a'),
        ('guest', '$2y$12$4gv/3QyfdAuwxjlwmC36bOc2kGJHoHVY9coRlnOah1QNezXUmq9WG', 'n/a', 'n/a', 'n/a');

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'), ('ROLE_CLIENT');

INSERT INTO users_roles(user_id, role_id)
SELECT (SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM users WHERE username = 'guest'), (SELECT id FROM roles WHERE name = 'ROLE_CLIENT');
