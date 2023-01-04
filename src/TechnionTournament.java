import java.util.ArrayList;

public class TechnionTournament implements Tournament{

    TechnionTournament(){};
    protected FacultyByIDTree facultyByIDTree;
    protected FacultyByScoreThenIDTree facultyByScoreThenIDTree;
    protected PlayerByIDTree playerByIDTree;
    protected PlayerByGoalsThenIDTree playerByGoalsThenIDTree;


    @Override
    public void init() {
        this.facultyByIDTree = new FacultyByIDTree();
        this.facultyByScoreThenIDTree = new FacultyByScoreThenIDTree();
        this.playerByIDTree = new PlayerByIDTree();
        this.playerByGoalsThenIDTree = new PlayerByGoalsThenIDTree();

    }

    @Override
    public void addFacultyToTournament(Faculty faculty) {
        FacultyInTournament newFaculty1 = new FacultyInTournament(faculty);
        Node<FacultyInTournament> newFacultyNode1 = new Node<FacultyInTournament>(newFaculty1);
        FacultyInTournament newFaculty2 = new FacultyInTournament(faculty);
        Node<FacultyInTournament> newFacultyNode2 = new Node<FacultyInTournament>(newFaculty2);
        this.facultyByIDTree.Insert(newFacultyNode1);
        this.facultyByScoreThenIDTree.Insert(newFacultyNode2);
    }

    @Override
    public void removeFacultyFromTournament(int faculty_id){
        Node<FacultyInTournament> wanted = this.facultyByIDTree.search(faculty_id);
        PlayerInTournament[] players = wanted.node_content.players;
        for (PlayerInTournament p: players)
        {
            p.faculty_id = 0;
        }
        this.facultyByIDTree.DeleteWithID(faculty_id);


        int wID = wanted.node_content.faculty.getId();
        int wGoals = wanted.node_content.score;
        wanted = this.facultyByScoreThenIDTree.search(wID , wGoals);
        players = wanted.node_content.players;
        for (PlayerInTournament p: players)
        {
            p.faculty_id = 0;
        }
        this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(wID, wGoals);


        for (PlayerInTournament p: players)
        {
            int pID = p.player.getId();

            Node<PlayerInTournament> np = this.facultyByIDTree.search(pID);
            np.node_content.faculty_id = 0;
            int pg = np.node_content.goals;
            np = this.facultyByScoreThenIDTree.search(pID, pg);
            np.node_content.faculty_id = 0;
        }

    }

    @Override
    public void addPlayerToFaculty(int faculty_id,Player player) {
        PlayerInTournament pit1 = new PlayerInTournament(player , 0 , faculty_id);
        Node<PlayerInTournament> npit1 = new Node<PlayerInTournament>(pit1);
        PlayerInTournament pit2 = new PlayerInTournament(player , 0 , faculty_id);
        Node<PlayerInTournament> npit2 = new Node<PlayerInTournament>(pit2);

        this.playerByIDTree.Insert(npit1);
        this.playerByGoalsThenIDTree.Insert(npit2);

        Node<FacultyInTournament> nfit  = this.facultyByIDTree.search(faculty_id);
        int score = nfit.node_content.score;

        PlayerInTournament[] players = nfit.node_content.players;
        for(int i = 0 ; i < 11 ; i++)
        {
            if (players[i] == null)
            {
                players[i] = pit1;
                break;
            }
        }

        nfit = this.facultyByScoreThenIDTree.search(faculty_id , score);
        players = nfit.node_content.players;
        for(int i = 0 ; i < 11 ; i++)
        {
            if (players[i] == null)
            {
                players[i] = pit1;
                break;
            }
        }
    }

    @Override
    public void removePlayerFromFaculty(int faculty_id, int player_id) {

        Node<FacultyInTournament> f = this.facultyByIDTree.search(faculty_id);
        int score= f.node_content.score;
        PlayerInTournament[] players = f.node_content.players;
        for (int i = 0; i < 11 ; i++)
        {
            if(players[i].player.getId() == player_id){
                players[i] = null;
                break;
            }
        }

        f = this.facultyByScoreThenIDTree.search(faculty_id , score);
        players = f.node_content.players;
        for (int i = 0; i < 11 ; i++)
        {
            if(players[i].player.getId() == player_id){
                players[i] = null;
                break;
            }
        }

        Node<PlayerInTournament> p = this.playerByIDTree.search(player_id);
        int goals = p.node_content.goals;
        p.node_content.faculty_id = 0;

        p = this.playerByGoalsThenIDTree.search(player_id, goals);
        p.node_content.faculty_id = 0;

    }

    @Override
    public void playGame(int faculty_id1, int faculty_id2, int winner,
                         ArrayList<Integer> faculty1_goals, ArrayList<Integer> faculty2_goals) {

    }

    @Override
    public void getTopScorer(Player player) {

    }

    @Override
    public void getTopScorerInFaculty(int faculty_id, Player player) {

    }

    @Override
    public void getTopKFaculties(ArrayList<Faculty> faculties, int k, boolean ascending) {

    }

    @Override
    public void getTopKScorers(ArrayList<Player> players, int k, boolean ascending) {

    }

    @Override
    public void getTheWinner(Faculty faculty) {

    }

    ///TODO - add below your own variables and methods
}
