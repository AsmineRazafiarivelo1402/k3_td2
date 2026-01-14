import io.github.cdimascio.dotenv.Dotenv;
import model.ContinentEnum;
import model.Player;
import model.PlayerPositionEnum;
import model.Team;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String am=  dotenv.get("DB_USERNAME");
        System.out.println(am);

    DataRetriever dataRetriever = new DataRetriever();
        Team team = dataRetriever.findTeamById(1);
        System.out.println(team.getName());
        for (Player player : team.getPlayers()) {
            System.out.println(
                    "- " + player.getName()
                            + " | Age: " + player.getAge()
                            + " | Position: " + player.getPosition_enum()
            );
        }
        List<Player> players = dataRetriever.findPlayers(1,3);
        for(Player player : players){
            System.out.println(player.getName());

        }

        Team realMadrid = new Team(1, "Real Madrid CF", ContinentEnum.EUROPA, new ArrayList<>());
        Team barcelona = new Team(2, "FC Barcelona", ContinentEnum.EUROPA, new ArrayList<>());
        Team atletico = new Team(3, "Atlético de Madrid", ContinentEnum.EUROPA, new ArrayList<>());
        Team alAhly = new Team(4, "Al Ahly SC", ContinentEnum.AFRICA, new ArrayList<>());
        Team interMiami = new Team(5, "Inter Miami CF", ContinentEnum.AMERICA, new ArrayList<>());


        List<Player> newPlayers = List.<Player>of(
                new Player(11, "Asmine", 23, PlayerPositionEnum.STR, realMadrid),
                new Player(6, "Vinicius Junior", 23, PlayerPositionEnum.STR, realMadrid),
                new Player(7, "Eduardo Camavinga", 21, PlayerPositionEnum.MIDF, realMadrid),
                new Player(8, "Gavi", 19, PlayerPositionEnum.MIDF, barcelona),
                new Player(9, "Frenkie de Jong", 26, PlayerPositionEnum.MIDF, barcelona),
                new Player(10, "Joao Felix", 24, PlayerPositionEnum.STR, atletico)
        );

        try {
            dataRetriever.createPlayers(newPlayers);

        } catch (RuntimeException e) {
            System.out.println("Insertion annulée : " + e.getMessage());
        }


    }
}
