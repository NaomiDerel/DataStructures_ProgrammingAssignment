public class PlayerInTournament {

    protected Player player;
    protected int faculty_id;
    protected int goals;


    public PlayerInTournament(Player player) {
        this.player = player;
        this.goals = 0;
        this.faculty_id = 0;
    }

    public PlayerInTournament(Player player, int goals, int faculty_id) {
        this.player = player;
        this.goals = goals;
        this.faculty_id = faculty_id;
    }

    public void updateGoalsBy(int goal) {
        this.goals += goal;
    }
}
