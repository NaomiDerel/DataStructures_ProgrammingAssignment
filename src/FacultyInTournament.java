public class FacultyInTournament {

    private final Faculty faculty;
    private int score;
    private PlayerInTournament[] players;

    public FacultyInTournament(Faculty faculty) {
        this.faculty = faculty;
        this.score = 0;
        this.players = new PlayerInTournament[11];
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PlayerInTournament[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerInTournament[] players) {
        this.players = players;
    }
}
