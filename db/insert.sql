INSERT INTO role (id, name)
VALUES (DEFAULT, 'laenutaja');

INSERT INTO role (id, name)
VALUES (DEFAULT, 'lugeja');


INSERT INTO "user"(id, username, password, role_id)
VALUES (DEFAULT, 'laenuataja1', 'laenuataja1', 516);

INSERT INTO "user"(id, username, password, role_id)
VALUES (DEFAULT, 'kasutaja1', 'kasutaja1', 517);

INSERT INTO "user"(id, username, password, role_id)
VALUES (DEFAULT, 'kasutaja2', 'kasutaja2', 517);

INSERT INTO "user"(id, username, password, role_id)
VALUES (DEFAULT, 'kasutaja3', 'kasutaja3', 517);


INSERT INTO librarian(id, id_code, user_id)
VALUES (DEFAULT, '11111111111', 447);


INSERT INTO library_user(id, id_code, first_name, last_name, user_id)
VALUES (DEFAULT, '22222222222', 'Kati', 'Karu', 448);

INSERT INTO library_user(id, id_code, first_name, last_name, user_id)
VALUES (DEFAULT, '33333333333', 'Juta', 'Jänku', 449);

INSERT INTO library_user(id, id_code, first_name, last_name, user_id)
VALUES (DEFAULT, '44444444444', 'Rein', 'Rebase', 450);


INSERT INTO book(id, name, acquisition_date, copy_quantity, loan_period, location)
VALUES (DEFAULT, 'Klouni silmadega', '2022-01-03', 15, 4, 'IK11');

INSERT INTO book(id, name, acquisition_date, copy_quantity, loan_period, location)
VALUES (DEFAULT, 'Tatarlaste kõrb', '2022-01-03', 4, 1, 'IK12');

INSERT INTO book(id, name, acquisition_date, copy_quantity, loan_period, location)
VALUES (DEFAULT, 'Onu Vanja', '2022-12-27', 15, 1, 'IK13');

INSERT INTO book(id, name, acquisition_date, copy_quantity, loan_period, location)
VALUES (DEFAULT, 'Everyday Chaos: Technology, Complexity, and How We’re Thriving in a New World of Possibility', '2022-01-03', 4, 1, 'IT11');


INSERT INTO  lending(id, lending_date, due_date, return_date, status, library_user_id, book_id, librarian_id)
VALUES (DEFAULT, '2023-01-02', '2023-01-20', NULL, 'välja laenutatud', 216, 322, 217);

INSERT INTO  lending(id, lending_date, due_date, return_date, status, library_user_id, book_id, librarian_id)
VALUES (DEFAULT, '2023-01-02', '2023-01-09', NULL, 'välja laenutatud', 217, 323, 217);

INSERT INTO  lending(id, lending_date, due_date, return_date, status, library_user_id, book_id, librarian_id)
VALUES (DEFAULT, '2023-01-02', '2023-01-09', NULL, 'välja laenutatud', 218, 324, 217);

INSERT INTO  lending(id, lending_date, due_date, return_date, status, library_user_id, book_id, librarian_id)
VALUES (DEFAULT, '2023-01-02', '2023-01-09', NULL, 'välja laenutatud', 216, 325, 217);

INSERT INTO  lending(id, lending_date, due_date, return_date, status, library_user_id, book_id, librarian_id)
VALUES (DEFAULT, '2022-08-02', '2023-01-09', NULL, 'välja laenutatud', 217, 325, 217);

INSERT INTO  lending(id, lending_date, due_date, return_date, status, library_user_id, book_id, librarian_id)
VALUES (DEFAULT, '2022-06-03', '2023-07-01', NULL, 'välja laenutatud', 216, 325, 217);

