package model;
import java.util.Objects;

public class Player {
    private int id;
    private String name;
    private int age;
    private PlayerPositionEnum position_enum;
    private Team team;

    public Player(int age, int id, String name, PlayerPositionEnum position_enum, Team team) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.position_enum = position_enum;
        this.team = team;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public PlayerPositionEnum getPosition_enum() {
        return position_enum;
    }

    public void setPosition_enum(PlayerPositionEnum position_enum) {
        this.position_enum = position_enum;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getId() == player.getId() && getAge() == player.getAge() && Objects.equals(getName(), player.getName()) && Objects.equals(getPosition_enum(), player.getPosition_enum()) && Objects.equals(getTeam(), player.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getPosition_enum(), getTeam());
    }

    public  String getTeamName(){
        return "";
    }
}
