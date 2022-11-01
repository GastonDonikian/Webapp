truncate schema public restart identity and commit no check;
TRUNCATE TABLE users RESTART IDENTITY AND COMMIT NO CHECK;
INSERT INTO users(user_id,name, surname, email, password, phone_number, is_professor,  enabled)
VALUES (1,'Gaston', 'Donikian', 'gastondonikian@gmail.com', '12345678', '54165353', true, true),
       (2,'Felipe', 'Oliver', 'foliver@itba.edu.ar', '12345678', '12345678', true, true),
       (3,'Lucia', 'Digon', 'ldigon@itba.edu.ar', '12345678', '56781234', true, true),
       (4,'Julian', 'Sicardi', 'jsicardi@itba.edu.ar', '12345678', '13572468',  true, true),
       (5,'Camila', 'Borinsky', 'cborinsky@itba.edu.ar', '12345678', '87542163', true, true),
       (6,'Agustin', 'Jerusa', 'ajerusa@itba.edu.ar', '12345678', '85274163', true, true),
       (7,'Agustin', 'Spitzner', 'aspitnzer@itba.edu.ar', '12345678', '24681537', true, true);

TRUNCATE TABLE professors RESTART IDENTITY AND COMMIT NO CHECK;
INSERT INTO professors(professor_id, studies, description, location)
VALUES (1, 'ITBA', 'Estudie Ingenieria Informatica en el ITBA. Me gusta enseñar las materias de forma teorica',
        'Recoleta'),
       (2, 'ITBA', 'Estudie Ingenieria Informatica en el ITBA. Me gusta enseñar las materias de forma practica',
        'Recoleta'),
       (3, 'ITBA', 'Estudie Ingenieria Informatica en el ITBA. Me gusta mezclar entre teoria y practica.', 'Recoleta'),
       (4, 'ITBA',
        'Estudie Ingenieria Industrial en el ITBA (como todos los profesores de aca), bizarro. Me gusta recomendar peliculas',
        'Recoleta'),
       (5, 'ITBA', 'Estudie Ingenieria Mecanica en el ITBA. Consultame lo que quieras', 'Recoleta'),
       (6, 'ITBA', 'Estudie Ciencias Sociales en la UBA. Me gusta dar un enfoque amplio para lxs compañerxs',
        'Recoleta'),
       (7, 'ITBA', 'Estudie en el ITBA. Me gusta el heavy metal y el canto liturgico', 'Recoleta');