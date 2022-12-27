public class PlayerInTournament {

    protected Player player;
    protected int faculty_id;
    protected int goals;


    public PlayerInTournament(Player player) {
        this.player = player;
    }

    public PlayerInTournament(Player player, int goals, int faculty_id) {
        this.player = player;
        this.goals = goals;
        this.faculty_id = faculty_id;
    }
}
