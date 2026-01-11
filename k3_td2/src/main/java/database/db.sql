CREATE DATABASE mini_football_db;
CREATE ROLE mini_football_db_manager LOGIN PASSWORD '123456';
GRANT ALL PRIVILEGES ON DATABASE mini_football_db TO mini_football_db_manager;
