CREATE TABLE IF NOT EXISTS `hotels` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `category` varchar(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS `availabilities` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `availability_date` date NOT NULL,
  `id_hotel` int NOT NULL,
  `rooms` int NOT NULL,
  foreign key (id_hotel) references hotels(id)
);

CREATE TABLE IF NOT EXISTS `bookings` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `id_hotel` int NOT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `email` varchar(100) NOT NULL,
  foreign key (id_hotel) references hotels(id)
);