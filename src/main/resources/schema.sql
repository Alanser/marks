CREATE TABLE pupils(
    id IDENTITY NOT NULL PRIMARY KEY,
    fio VARCHAR(200)
);

CREATE TABLE subjects(
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(200)
);

CREATE TABLE marks(
    id IDENTITY NOT NULL PRIMARY KEY,
    grade INT,
    pupil_id BIGINT,
    subject_id BIGINT,
    mark INT,
    date DATE,
    foreign key (pupil_id) references pupils(id),
    foreign key (subject_id) references subjects(id)
);
