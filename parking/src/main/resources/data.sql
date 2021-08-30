DROP TABLE IF EXISTS car_parking;

CREATE TABLE car_parking (
    car_park_no varchar(20) PRIMARY KEY,
    address varchar(200),
    x_coord decimal(11,7),
    y_coord decimal(11,7),
    car_park_type varchar(20),
    type_of_parking_system varchar(20),
    short_term_parking varchar(10),
    free_parking varchar(50),
    night_parking tinyint(1) ,
    car_park_decks varchar(50),
    gantry_height varchar(50),
    car_park_basement tinyint(1),
    is_parking_available tinyint(1),
    total_lots int,
    lots_available int,
    INDEX (x_coord, y_coord,is_parking_available)
);
