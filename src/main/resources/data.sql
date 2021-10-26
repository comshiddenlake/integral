INSERT IGNORE INTO roles(id, name) SELECT * FROM (SELECT '0', 'ROLE_REF') AS tmp WHERE NOT EXISTS (SELECT name from roles WHERE name = 'ROLE_REF') LIMIT 1;
INSERT IGNORE INTO roles(id, name) SELECT * FROM (SELECT '1', 'ROLE_ADMIN') AS tmp WHERE NOT EXISTS (SELECT name from roles WHERE name = 'ROLE_ADMIN') LIMIT 1;

INSERT IGNORE INTO usuario (id,email, password, username)
SELECT * FROM (SELECT '0', 'bruseghini_92@live.com.ar', '$2a$10$qeBsDYuRxtGxm1bQW/k5MOZX.g6PU824rvGOWVbm8C1OOAiFVKBrG','bruseghini92') AS tmp
WHERE NOT EXISTS (
    SELECT username FROM usuario WHERE username = 'bruseghini92.ab'
) LIMIT 1;

INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT * FROM (SELECT '0','1') AS tmp1
WHERE NOT EXISTS (
    SELECT user_id from user_roles where user_id = '0'
) LIMIT 1;

INSERT IGNORE INTO tipo_asistencias(id, tipo_asistencia) SELECT * FROM (SELECT '0', 'PRESENTE') AS tmp WHERE NOT EXISTS (SELECT tipo_asistencia from tipo_asistencias WHERE tipo_asistencia = 'PRESENTE') LIMIT 1;

INSERT IGNORE INTO tipo_asistencias(id, tipo_asistencia) SELECT * FROM (SELECT '1', 'FRANCO') AS tmp WHERE NOT EXISTS (SELECT tipo_asistencia from tipo_asistencias WHERE tipo_asistencia = 'FRANCO') LIMIT 1;

INSERT IGNORE INTO tipo_asistencias(id, tipo_asistencia) SELECT * FROM (SELECT '2', 'VACACIONES') AS tmp WHERE NOT EXISTS (SELECT tipo_asistencia from tipo_asistencias WHERE tipo_asistencia = 'VACACIONES') LIMIT 1;
