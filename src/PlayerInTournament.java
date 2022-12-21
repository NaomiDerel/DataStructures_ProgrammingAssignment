public class PlayerInTournament {

    private Player player;
    private int faculty_id;
    private int goals;


    public PlayerInTournament(Player player , int faculty_id)
    {
        player = player;
        faculty_id = faculty_id;
    }


    public Player getPlayer() {
        return player;
    }

    public int getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(int faculty_id) {
        this.faculty_id = faculty_id;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }
}
