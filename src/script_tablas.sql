CREATE DATABASE bd_subastas;
USE bd_subastas;

CREATE TABLE t_usuarios_moderador (
    id                INTEGER(10),
    nombreCompleto    VARCHAR(15),
    fechaNacimiento   DATE,
    edad              INTEGER(3),
    contrasenna       VARCHAR(15),
    correoElectronico VARCHAR(30) PRIMARY KEY NOT NULL UNIQUE
);

CREATE TABLE t_usuarios_vendedor (
    id                INTEGER(10),
    nombreCompleto    VARCHAR(15),
    fechaNacimiento   DATE,
    edad              INTEGER(3),
    contrasenna       VARCHAR(15),
    correoElectronico VARCHAR(30) PRIMARY KEY NOT NULL UNIQUE,
    puntuacion        INTEGER(3),
    direccion         VARCHAR(50)
);

CREATE TABLE t_usuarios_coleccionista (
    id                INTEGER(10),
    nombreCompleto    VARCHAR(15),
    fechaNacimiento   DATE,
    edad              INTEGER(3),
    contrasenna       VARCHAR(15),
    correoElectronico VARCHAR(30) PRIMARY KEY NOT NULL UNIQUE,
    puntuacion        INTEGER(3),
    direccion         VARCHAR(50)
);

CREATE TABLE t_objetos (
    id          INTEGER(10) PRIMARY KEY,
    nombre      VARCHAR(50),
    descripcion VARCHAR(100),
    fechaCompra DATE,
    estado      VARCHAR(20)
);

CREATE TABLE t_subasta (
    id                     INTEGER(10) PRIMARY KEY,
    fechaVencimiento       DATE,
    puntuacionCreador      INTEGER(10),
    precioMinimoAceptable  DOUBLE,
    estadoSubasta          VARCHAR(20),
    idUsuarioVendedor      VARCHAR(30),
    idUsuarioColeccionista VARCHAR(30),
    idObjeto               INTEGER(10)
);

CREATE TABLE t_oferta (
    id                     INTEGER(10) PRIMARY KEY,
    nombreOfertante        VARCHAR(15),
    puntuacionOfertante    INTEGER(3),
    precioOfertado         DOUBLE,
    idUsuarioColeccionista VARCHAR(30),
    idSubasta              INTEGER(10)
);

DROP TABLE t_oferta;
DROP TABLE t_subasta;
DROP TABLE t_objetos;
DROP TABLE t_usuarios_coleccionista;
DROP TABLE t_usuarios_vendedor;
DROP TABLE t_usuarios_moderador;

-- SELECT
SELECT * FROM t_usuarios_moderador;
SELECT * FROM t_usuarios_vendedor;
SELECT * FROM t_usuarios_coleccionista;
SELECT * FROM t_objetos;
SELECT * FROM t_subasta;
SELECT * FROM t_oferta;