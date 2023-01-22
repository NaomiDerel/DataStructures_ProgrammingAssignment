public class FacultyInTournament {
    protected Faculty faculty;
    protected int score;
    protected PlayerInTournament[] players;

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

    public String getName() {
        return this.faculty.getName();
    }

    public void updateScoreBy(int score) {
        this.score += score;
    }

    public Player findMax()
    {
        PlayerInTournament[] players = this.players;
        PlayerInTournament max = players[0];
        for(int i = 1 ; i < 11 && players[i] != null ; i++)
        {
            if (players[i].goals > max.goals)
            {
                max = players[i];
            }
        }
        return max.player;
    }




}

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

    public Player findMax()
    {
        PlayerInTournament[] players = this.players;
        PlayerInTournament max = players[0];
        for(int i = 1 ; i < 11 && players[i] != null ; i++)
        {
            if (players[i].goals > max.goals)
            {
                max = players[i];
            }
        }
        return max.player;
    }




}
