public class FacultyInTournament {
    protected Faculty faculty;
    protected int score;
    protected PlayerInTournament[] players;

    public FacultyInTournament() {
    }

    public FacultyInTournament(Faculty faculty)
    {
        this.faculty = faculty;
        this.score = 0;
        this.players = new PlayerInTournament[11];
    }

    public FacultyInTournament(Faculty faculty, int score, PlayerInTournament[] players) {
        this.faculty = faculty;
        this.score = score;
        this.players = players;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public int getScore() {
        return score;
    }

    public PlayerInTournament[] getPlayers() {
        return players;
    }

    public void updateScoreBy(int score) {
        this.score += score;
    }

    public void setPlayers(PlayerInTournament[] players) {
        this.players = players;
    }
}
