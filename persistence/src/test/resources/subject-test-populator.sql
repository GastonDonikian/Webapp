truncate schema public restart identity and commit no check;
TRUNCATE TABLE subjects RESTART IDENTITY AND COMMIT NO CHECK;
INSERT INTO subjects(subject_id,name,level,category) VALUES
(1,'Algebra Lineal','College','Science'),
(2,'Matematica Discreta','College','Science'),
(3,'Algebra para Informaticos','College','Science'),
(4,'Analisis Matematico I','College','Science'),
(5,'Analisis Matematico II','College','Science'),
(6,'Analisis Matematico III','College','Science'),
(7,'Ingles','School','Language'),
(8,'Ingles','HighSchool','Language'),
(9,'Ingles','College','Language'),
(10,'Geografía','HighSchool','Social'),
(11,'Historia General','HighSchool','Social'),
(12,'Filosofia I','College','Social'),
(13,'Arte Medieval','HighSchool','Arts'),
(14,'Arte Romantica','College','Arts');

