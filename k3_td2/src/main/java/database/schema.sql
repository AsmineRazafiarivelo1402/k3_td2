CREATE TYPE position_enum
    AS
    ENUM('GK', 'DEF', 'MIDF', 'STR');
CREATE TYPE continent_enum
    AS
    ENUM('AFRICA', 'EUROPA', 'ASIA',
'AMERICA');
CREATE TABLE TEAM(
                       id int,
                       name varchar(150),
                       continent continent_enum NOT NULL

);

CREATE TABLE PLAYER(
    id int,
    name varchar(150),
    age int,
    position position_enum NOT NULL ,
    id_team int
);

---SELECT---
SELECT TEAM.id,TEAM.name,TEAM.continent from  TEAM where id = ? ;
SELECT PLAYER.id,PLAYER.name,PLAYER.age,PLAYER.position,PLAYER.id_team from PLAYER;

--List<Player> findPlayers(int page, int size) permettant de récupérer la liste
--des joueurs à travers une pagination.
SELECT PLAYER.id,PLAYER.name,PLAYER.age,PLAYER.position,T.id, T.name, T.continent from PLAYER inner join TEAM T on PLAYER.id_team = T.id LIMIT = 5 OFFSET = 5 ;
