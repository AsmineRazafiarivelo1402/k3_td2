package model;

import java.util.List;
import java.util.Objects;

public class Team {
    private int id;
    private  String name;
    private ContinentEnum continnent_enum;
    private List<Player> players;

    public Team(ContinentEnum continnent_enum, int id, String name, List<Player> players) {
        this.continnent_enum = continnent_enum;
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public ContinentEnum getContinnent_enum() {
        return continnent_enum;
    }

    public void setContinnent_enum(ContinentEnum continnent_enum) {
        this.continnent_enum = continnent_enum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getId() == team.getId() && Objects.equals(getName(), team.getName()) && getContinnent_enum() == team.getContinnent_enum() && Objects.equals(getPlayers(), team.getPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getContinnent_enum(), getPlayers());
    }
     public Integer getPlayersCount(){
        return 0;
     }
}

