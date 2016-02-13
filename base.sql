CREATE DATABASE IF NOT EXISTS `mkalenik_hibernate`
  CHARACTER SET 'utf8mb4'
  COLLATE 'utf8mb4_general_ci';
  CREATE USER 'mkalenik'@'%' IDENTIFIED BY 'mkalenik';
GRANT ALL PRIVILEGES ON mkalenik_hibernate . * TO 'mkalenik'@'%';