


import model.ContinentEnum;
import model.Player;
import model.PlayerPositionEnum;
import model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataRetriever {
    DBConnection dbConnection = new DBConnection();

    public Team findTeamById(Integer id) {
        String teamSql = "SELECT TEAM.id,TEAM.name,TEAM.continent from  TEAM where id = ? ";
        String playerSql = "SELECT PLAYER.id,PLAYER.name,PLAYER.age,PLAYER.position,PLAYER.id_team from PLAYER where id_team = ?";
        Team team = null;
        List<Player> players = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(dbConnection.getJdbcURL(), dbConnection.getUsername(), dbConnection.getPassword());
                PreparedStatement preparedStatementTeam = connection.prepareStatement(teamSql);
                PreparedStatement preparedStatementPlayer = connection.prepareStatement(playerSql);
                ){

            //var pstmt = conn.prepareStatement(sql))

            preparedStatementTeam.setInt(1, id);
            preparedStatementPlayer.setInt(1, id);
            ResultSet resultSetTeam = preparedStatementTeam.executeQuery();
            ResultSet resultSetPlayer = preparedStatementPlayer.executeQuery();
            if(resultSetTeam.next()){

                team = new Team(
                        resultSetTeam.getInt("id"),
                        resultSetTeam.getString("name"),
                        ContinentEnum.valueOf(resultSetTeam.getString("continent")),
                        players

                );


            }
            while (resultSetPlayer.next()){
                Player player = new Player(
                        resultSetPlayer.getInt("id"),
                        resultSetPlayer.getString("name"),
                        resultSetPlayer.getInt("age"),
                        PlayerPositionEnum.valueOf(resultSetPlayer.getString("position")) ,
                        team
                );
                players.add(player);
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return  team;
    }
    //List<Player> findPlayers(int page, int size) permettant de récupérer la liste
    //des joueurs à travers une pagination.
    public List<Player> findPlayers(int page, int size){
        String playerSQL = "SELECT PLAYER.id,PLAYER.name,PLAYER.age,PLAYER.position,PLAYER.id_team, T.id, T.name, T.continent from PLAYER inner join TEAM T on PLAYER.id_team = T.id LIMIT ? OFFSET ?";
        List<Player> listPlayers = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(dbConnection.getJdbcURL(), dbConnection.getUsername(),dbConnection.getPassword());
             PreparedStatement psPlayer = connection.prepareStatement(playerSQL);
        ){
            psPlayer.setInt(1,size);
            psPlayer.setInt(2,page);
            ResultSet rsPlayer = psPlayer.executeQuery();
            while (rsPlayer.next()){
                Team team = new Team(
                        rsPlayer.getInt("id_team"),
                        rsPlayer.getString("name"),
                        ContinentEnum.valueOf(rsPlayer.getString("continent")),
                        players
                );
                Player player = new Player(
                        rsPlayer.getInt("id"),
                        rsPlayer.getString("name"),
                        rsPlayer.getInt("age"),
                        PlayerPositionEnum.valueOf(rsPlayer.getString("position")) ,
                        team
                );
                listPlayers.add(player);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPlayers;
    }

public void createPlayers(List<Player> newPlayers) {
    String playerSql = "SELECT id, name, age, position, id_team FROM PLAYER";
    String insertPlayer = "INSERT INTO PLAYER (id, name, age, position, id_team) VALUES (?, ?, ?, ?::position_enum, ?)";


    Connection connexion = null;

    try {
        connexion = DriverManager.getConnection(
                dbConnection.getJdbcURL(),
                dbConnection.getUsername(),
                dbConnection.getPassword()
        );
        connexion.setAutoCommit(false);

        // 1️⃣ Préparer statements
        PreparedStatement psPlayer = connexion.prepareStatement(playerSql);
        PreparedStatement psInsert = connexion.prepareStatement(insertPlayer);

        // 2️⃣ Vérifier les doublons
        ResultSet rsPlayer = psPlayer.executeQuery();
        while (rsPlayer.next()) {
            int existingId = rsPlayer.getInt("id");
            for (Player player : newPlayers) {
                if (player.getId() == existingId) {
                    throw new RuntimeException(
                            "Le joueur '" + player.getName() + "' existe déjà dans la base"
                    );
                }
            }
        }

        // 3️⃣ Insérer tous les joueurs (batch)
        for (Player player : newPlayers) {
            psInsert.setInt(1, player.getId());
            psInsert.setString(2, player.getName());
            psInsert.setInt(3, player.getAge());
            psInsert.setObject(4, player.getPosition_enum(), java.sql.Types.OTHER);
            psInsert.setInt(5, player.getTeam().getId());
            psInsert.addBatch();
        }

        psInsert.executeBatch();
        connexion.commit();
        System.out.println("Tous les joueurs ont été insérés avec succès !");

    } catch (RuntimeException e) {
        // rollback si exception unchecked
        if (connexion != null) {
            try {
                connexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Insertion annulée : " + e.getMessage());
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


}
