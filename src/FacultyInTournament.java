public class FacultyInTournament {
    protected Faculty faculty;
    protected int score;
    protected Player[] players;

    public FacultyInTournament() {
    }

    public FacultyInTournament(Faculty faculty, int score, Player[] players) {
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

    public Player[] getPlayers() {
        return players;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
