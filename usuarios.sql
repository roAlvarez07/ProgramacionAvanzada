CREATE TABLE `usuarios` (
  `Nro_Cuenta` int(11) DEFAULT NULL,
  `NIP` int(11) DEFAULT NULL,
  `Saldo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO `usuarios`(`Nro_Cuenta`, `NIP`, `Saldo`) VALUES (123456, 12345, 480);
INSERT INTO `usuarios`(`Nro_Cuenta`, `NIP`, `Saldo`) VALUES (1234, 1234, 1480);
