truncate schema public restart identity and commit no check;
TRUNCATE TABLE subjects RESTART IDENTITY AND COMMIT NO CHECK;
INSERT INTO subjects(subject_id,name, level, category)
VALUES (1,'Algebra Lineal', 'College', 'Science'),
       (2,'Matematica Discreta', 'College', 'Science'),
       (3,'Algebra para Informaticos', 'College', 'Science'),
       (4,'Analisis Matematico I', 'College', 'Science'),
       (5,'Analisis Matematico II', 'College', 'Science'),
       (6,'Analisis Matematico III', 'College', 'Science'),
       (7,'Ingles', 'School', 'Language'),
       (8,'Ingles', 'HighSchool', 'Language'),
       (9,'Ingles', 'College', 'Language'),
       (10,'Geografía', 'HighSchool', 'Social'),
       (11,'Historia General', 'HighSchool', 'Social'),
       (12,'Filosofia I', 'College', 'Social'),
       (13,'Arte Medieval', 'HighSchool', 'Arts'),
       (14,'Arte Romantica', 'College', 'Arts');

TRUNCATE TABLE users RESTART IDENTITY AND COMMIT NO CHECK;
INSERT INTO users(user_id,name, surname, email, password, phone_number, is_professor, enabled)
VALUES (1,'Gaston', 'Donikian', 'gastondonikian@gmail.com', '12345678', '54165353', true, true),
       (2,'Felipe', 'Oliver', 'foliver@itba.edu.ar', '12345678', '12345678',  true, true),
       (3,'Lucia', 'Digon', 'ldigon@itba.edu.ar', '12345678', '56781234',  true, true),
       (4,'Julian', 'Sicardi', 'jsicardi@itba.edu.ar', '12345678', '13572468', true, true),
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


TRUNCATE TABLE contract RESTART IDENTITY AND COMMIT NO CHECK;
INSERT INTO contract(contract_id,professor_id, subject_id, contract_description, local, remote, price, status)
VALUES (1,1, 3, 'Este contrato tiene mucho flow', false, true, 1250, 'PAUSED'),
       (2,2, 3, 'Este contrato tiene mucho flow', false, true, 1250, 'ACTIVE'),
       (3,1, 3, 'Este contrato tiene mucho flow', false, true, 1250, 'PAUSED'),
       (4,1, 3, 'Este contrato tiene mucho flow', false, true, 1250, 'ACTIVE'),
       (5,3, 2, 'Este contrato tiene mucho flow', true, false, 1250, 'PAUSED'),
       (6,4, 2, 'Este contrato tiene mucho flow', true, false, 1250, 'ACTIVE'),
       (7,1, 2, 'Este contrato tiene mucho flow', true, false, 1250, 'PAUSED'),
       (8,1, 2, 'Este contrato tiene mucho flow', true, false, 1250, 'ACTIVE');