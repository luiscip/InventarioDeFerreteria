-- MySQL Script generated by MySQL Workbench
-- Fri Nov 15 23:07:40 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dbinventario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbinventario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbinventario` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `dbinventario` ;

-- -----------------------------------------------------
-- Table `dbinventario`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `email` VARCHAR(50) NULL,
  `user` VARCHAR(20) NOT NULL,
  `contraseña` VARCHAR(20) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `descripción` VARCHAR(250) NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `dbinventario`.`Marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Marca` (
  `id_marca` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `descripcion` VARCHAR(250) NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_marca`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`Ubicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Ubicacion` (
  `id_ubicacion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(250) NOT NULL,
  `seccion` VARCHAR(100) NOT NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_ubicacion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Producto` (
  `id_producto` INT NOT NULL AUTO_INCREMENT,
  `id_categoria` INT NOT NULL,
  `id_marca` INT NOT NULL,
  `id_ubicacion` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(100) NULL,
  `precio_compra` DECIMAL(12,2) NOT NULL,
  `precio_venta` DECIMAL(12,2) NOT NULL,
  `stock` INT NOT NULL,
  `stock_minimo` INT NOT NULL,
  `fecha_ultima_actualizacion` DATETIME NOT NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  `imagen` VARCHAR(100) NULL,
  PRIMARY KEY (`id_producto`),
  INDEX `fk_producto_categoria_idx` (`id_categoria` ASC),
  INDEX `fk_producto_marca_idx` (`id_marca` ASC),
  INDEX `fk_producto_ubicacion_idx` (`id_ubicacion` ASC),
  CONSTRAINT `fk_producto_categoria`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `dbinventario`.`Categoria` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_marca`
    FOREIGN KEY (`id_marca`)
    REFERENCES `dbinventario`.`Marca` (`id_marca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_ubicacion`
    FOREIGN KEY (`id_ubicacion`)
    REFERENCES `dbinventario`.`Ubicacion` (`id_ubicacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`Venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Venta` (
  `id_venta` INT NOT NULL AUTO_INCREMENT,
  `fecha_venta` DATE NOT NULL,
  `total` DECIMAL(12,2) NOT NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_venta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`Detalle_venta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Detalle_venta` (
  `id_detalle_venta` INT NOT NULL AUTO_INCREMENT,
  `id_venta` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `precio_venta_uni` DECIMAL(12,2) NOT NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_detalle_venta`),
  INDEX `fk_productos_idx` (`id_producto` ASC),
  INDEX `fk_factura_idx` (`id_venta` ASC),
  CONSTRAINT `fk_productos`
    FOREIGN KEY (`id_producto`)
    REFERENCES `dbinventario`.`Producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura`
    FOREIGN KEY (`id_venta`)
    REFERENCES `dbinventario`.`Venta` (`id_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`movimientos_stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`movimientos_stock` (
  `id_mov_stock` INT NOT NULL AUTO_INCREMENT,
  `id_productos` INT NOT NULL,
  `fecha_mov` DATETIME NOT NULL,
  `cantidad` INT NOT NULL,
  `tipo_mov` VARCHAR(45) NOT NULL,
  `comentario` VARCHAR(200) NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_mov_stock`),
  INDEX `fk_productos_idx` (`id_productos` ASC),
  CONSTRAINT `fk_productos_mov`
    FOREIGN KEY (`id_productos`)
    REFERENCES `dbinventario`.`Producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`Proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Proveedor` (
  `id_proveedores` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_proveedores`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Compra` (
  `id_compra` INT NOT NULL AUTO_INCREMENT,
  `id_proveedor` INT NOT NULL,
  `fecha_compra` DATE NOT NULL,
  `total` DECIMAL(12,2) NOT NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_compra`),
  INDEX `fk_compra_proveedor_idx` (`id_proveedor` ASC),
  CONSTRAINT `fk_compra_proveedor`
    FOREIGN KEY (`id_proveedor`)
    REFERENCES `dbinventario`.`Proveedor` (`id_proveedores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbinventario`.`Detalle_compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbinventario`.`Detalle_compra` (
  `id_detalle_compra` INT NOT NULL AUTO_INCREMENT,
  `id_compra` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `precio_unitario` DECIMAL(12,2) NOT NULL,
  `activo` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_detalle_compra`),
  INDEX `fk_compra_detallecompra_idx` (`id_compra` ASC),
  INDEX `fk_detallecompra_producto_idx` (`id_producto` ASC),
  CONSTRAINT `fk_detallecompra_compra`
    FOREIGN KEY (`id_compra`)
    REFERENCES `dbinventario`.`Compra` (`id_compra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_detallecompra_producto`
    FOREIGN KEY (`id_producto`)
    REFERENCES `dbinventario`.`Producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
