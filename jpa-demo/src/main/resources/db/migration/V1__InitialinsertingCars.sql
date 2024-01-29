create sequence cars_seq increment BY 50;

create table cars
(
    id         INT auto_increment primary key,
    plate      CHARACTER VARYING,
    created_At  TIMESTAMP,
    updated_At  TIMESTAMP,
    created_By  TIMESTAMP,
    modified_By TIMESTAMP
);


insert into cars (id, plate)
values (1, 'abc');

-- any SQL command 
create table carServiceReporting
(
    id          INT PRIMARY KEY,
    description VARCHAR(255)
); 