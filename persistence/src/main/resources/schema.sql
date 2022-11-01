CREATE TABLE IF NOT EXISTS subjects
(
    subject_id SERIAL PRIMARY KEY,
    name       VARCHAR(40),
    level      VARCHAR(20),
    category   VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS users
(
    user_id      SERIAL PRIMARY KEY,
    name         VARCHAR(20),
    surname      VARCHAR(20),
    email        VARCHAR(30) UNIQUE,
    password     VARCHAR(30),
    phone_number VARCHAR(10),
    age          INTEGER,
    is_professor BOOLEAN,
    profile_image BYTEA,
    enabled      BOOLEAN default FALSE
);

CREATE TABLE IF NOT EXISTS verification
(
    user_id            INTEGER PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    verification_token VARCHAR(15),
    expiration_date    DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS professors
(
    professor_id INTEGER PRIMARY KEY,
    FOREIGN KEY (professor_id) REFERENCES users (user_id) ON DELETE CASCADE,
    studies      VARCHAR(30),
    description  VARCHAR(200),
    location     VARCHAR(25),
    schedule VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS contract
(
    contract_id          SERIAL PRIMARY KEY,
    professor_id         INTEGER,
    subject_id           INTEGER,
    FOREIGN KEY (professor_id) REFERENCES professors (professor_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects (subject_id) ON DELETE CASCADE,
    contract_description VARCHAR(200),
    local                BOOLEAN,
    remote               BOOLEAN,
    price                FLOAT,
    status               VARCHAR(10)
);



CREATE TABLE IF NOT EXISTS lesson
(
    lesson_id                     SERIAL PRIMARY KEY,
    subject_id                    INTEGER,
    professor_id                  INTEGER,
    student_id                    INTEGER,
    FOREIGN KEY (professor_id) REFERENCES professors (professor_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subjects (subject_id) ON DELETE CASCADE,
    price                         double precision,
    lesson_status                 VARCHAR(20),
    confirmed_status_by_student   boolean default false,
    confirmed_status_by_professor boolean default false,
    schedule VARCHAR(30),
    meeting_link VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS review
(
    review_id SERIAL PRIMARY KEY,
    lesson_id INTEGER,
    user_id INTEGER,
    FOREIGN KEY (lesson_id) REFERENCES lesson (lesson_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    date DATE NOT NULL,
    message VARCHAR(255),
    rating    INTEGER
);


CREATE TABLE IF NOT EXISTS chat
(
    chat_id SERIAL PRIMARY KEY,
    lesson_id INTEGER,
    FOREIGN KEY (lesson_id) REFERENCES lesson (lesson_id) ON DELETE CASCADE,
    chat_name VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS message
(
    message_id SERIAL PRIMARY KEY,
    chat_id INTEGER,
    user_id INTEGER,
    FOREIGN KEY (chat_id) REFERENCES chat (chat_id) ON DELETE  CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    message VARCHAR(255) NOT NULL,
    date DATE NOT NULL
);


CREATE TABLE IF NOT EXISTS files
(
    file_id SERIAL PRIMARY KEY,
    lesson_id INTEGER,
    FOREIGN KEY (lesson_id) REFERENCES lesson (lesson_id) ON DELETE CASCADE,
    name VARCHAR(30) NOT NULL,
    file BYTEA NOT NULL
);

DROP VIEW IF EXISTS full_contract;
DROP VIEW IF EXISTS contract_rating;
DROP VIEW IF EXISTS  professor_rating;
DROP VIEW IF EXISTS full_review;
DROP VIEW IF EXISTS full_lesson;



CREATE VIEW full_lesson AS
(
SELECT lesson.lesson_id                     AS lesson_id,
       lesson.price                         AS lesson_price,
       lesson.lesson_status                 AS lesson_status,
       lesson.confirmed_status_by_professor AS confirmed_status_by_professor,
       lesson.confirmed_status_by_student   AS confirmed_status_by_student,
       lesson.subject_id                    AS subject_id,
       lesson.professor_id                  AS professor_id,
       lesson.student_id                    AS student_id,
       s.name                               AS subject_name,
       s.level                              AS subject_level,
       s.category                           AS subject_category,
       p.name                               AS professor_name,
       p.surname                            AS professor_surname,
       p.email                              AS professor_email,
       p.phone_number                       AS professor_phone_number,
       p.studies                            AS professor_studies,
       p.description                        AS professor_description,
       p.location                           AS professor_location,
       p.schedule                           AS professor_schedule,
       student.name                         AS student_name,
       student.surname                      AS student_surname,
       student.email                        AS student_email,
       student.phone_number                 AS student_phone_number,
       student.is_professor                 AS student_is_professor,
       r.review_id                          AS review_id,
       r.rating                             AS review_rating
FROM lesson
         LEFT JOIN (professors LEFT JOIN users u on professors.professor_id = u.user_id) p
                   ON p.professor_id = lesson.professor_id
         LEFT JOIN users student ON lesson.student_id = student.user_id
         LEFT JOIN subjects s on lesson.subject_id = s.subject_id
         LEFT JOIN review r on lesson.lesson_id = r.lesson_id);

CREATE VIEW full_review AS
(
SELECT review.review_id AS review_id,
       fl.lesson_id     AS lesson_id,
       fl.professor_id  AS professor_id,
       fl.student_id    AS student_id,
       fl.subject_id AS subject_id,
       review.rating    AS rating
FROM review
         LEFT JOIN full_lesson fl on review.review_id = fl.review_id);

CREATE VIEW professor_rating AS
(
SELECT
    p.professor_id AS professor_id,
    AVG(COALESCE(fl.rating,0))    AS professor_rating,
       COUNT(fl.review_id) AS professor_rating_count
FROM professors p
         RIGHT JOIN full_review fl ON p.professor_id = fl.professor_id
GROUP BY(p.professor_id)
    );

CREATE VIEW contract_rating AS
(
SELECT
    c.contract_id AS contract_id,
    AVG(r.rating) AS contract_rating,
    COUNT(r.review_id) AS contract_rating_count
FROM contract c RIGHT JOIN lesson l on (c.professor_id = l.professor_id AND c.subject_id = l.subject_id) LEFT JOIN review r on l.lesson_id = r.lesson_id WHERE r.rating IS NOT NULL GROUP BY(c.contract_id)
    );

CREATE VIEW full_contract AS
(
SELECT contract.contract_id AS contract_id,
       p.professor_id,
       s.subject_id,
       contract_description,
       COALESCE(contract_rating,0) AS contract_rating,
       local,
       remote,
       price,
       p.name     AS professor_name,
       p.surname  AS professor_surname,
       email,
       phone_number,
       studies,
       description,
       s.name     AS subject_name,
       level,
       category,
       p.location AS professor_location,
       status
FROM contract
         LEFT JOIN
     (professors LEFT JOIN users u on professors.professor_id = u.user_id) p on p.professor_id = contract.professor_id
         LEFT JOIN subjects s on contract.subject_id = s.subject_id LEFT JOIN contract_rating cr on contract.contract_id = cr.contract_id);


-- CREATE OR REPLACE FUNCTION unaccent(text)
--     RETURNS text
--     IMMUTABLE
--     STRICT
--     LANGUAGE SQL
-- AS
-- $$
-- SELECT translate(
--                $1,
--                'âãäåāăąÁÂÃÄÅĀĂĄèééêëēĕėęěĒĔĖĘĚìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮ',
--                'aaaaaaaaaaaaaaaeeeeeeeeeeeeeeeiiiiiiiiiiiiiiiiooooooooooooooouuuuuuuuuuuuuuuu'
--            )
-- $$;
