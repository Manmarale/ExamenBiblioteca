CREATE TABLE libros (
  id INT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(255),
  autor VARCHAR(255),
  anio_publicacion INT
);
 
CREATE TABLE lectores (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255),
  email VARCHAR(255)
);