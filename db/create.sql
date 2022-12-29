-- Created by Vertabelo (http://vertabelo.com)

CREATE TABLE book (
                      id serial  NOT NULL,
                      name varchar(250)  NOT NULL,
                      acquisition_date date  NOT NULL,
                      copy_quantity int  NOT NULL,
                      loan_period int  NOT NULL,
                      location varchar(50)  NOT NULL,
                      CONSTRAINT book_pk PRIMARY KEY (id)
);

CREATE TABLE lending (
                         id serial  NOT NULL,
                         lending_date date  NOT NULL,
                         due_date date  NULL,
                         return_date date  NULL,
                         status varchar(50)  NULL,
                         library_user_id int  NOT NULL,
                         book_id int  NOT NULL,
                         librarian_id int  NOT NULL,
                         CONSTRAINT lending_pk PRIMARY KEY (id)
);

CREATE TABLE librarian (
                           id serial  NOT NULL,
                           id_code varchar(11)  NOT NULL,
                           user_id int  NOT NULL,
                           CONSTRAINT librarian_pk PRIMARY KEY (id)
);

CREATE TABLE library_user (
                              id serial  NOT NULL,
                              id_code varchar(11)  NOT NULL,
                              first_name varchar(50)  NOT NULL,
                              last_name varchar(50)  NOT NULL,
                              user_id int  NOT NULL,
                              CONSTRAINT library_user_pk PRIMARY KEY (id)
);

CREATE TABLE role (
                      id serial  NOT NULL,
                      name varchar(25)  NOT NULL,
                      CONSTRAINT role_pk PRIMARY KEY (id)
);

CREATE TABLE "user" (
                        id serial  NOT NULL,
                        username varchar(50)  NOT NULL,
                        password varchar(200)  NOT NULL,
                        role_id int  NOT NULL,
                        CONSTRAINT user_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: lending_book (table: lending)
ALTER TABLE lending ADD CONSTRAINT lending_book
    FOREIGN KEY (book_id)
        REFERENCES book (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: lending_librarian (table: lending)
ALTER TABLE lending ADD CONSTRAINT lending_librarian
    FOREIGN KEY (librarian_id)
        REFERENCES librarian (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: lending_library_user (table: lending)
ALTER TABLE lending ADD CONSTRAINT lending_library_user
    FOREIGN KEY (library_user_id)
        REFERENCES library_user (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: librarian_user (table: librarian)
ALTER TABLE librarian ADD CONSTRAINT librarian_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: library_user_user (table: library_user)
ALTER TABLE library_user ADD CONSTRAINT library_user_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_role (table: user)
ALTER TABLE "user" ADD CONSTRAINT user_role
    FOREIGN KEY (role_id)
        REFERENCES role (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;