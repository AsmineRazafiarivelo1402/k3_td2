import io.github.cdimascio.dotenv.Dotenv;
import model.Player;
import model.Team;

import java.util.List;

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


    }
}
