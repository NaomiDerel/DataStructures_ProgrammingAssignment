import java.util.ArrayList;

public class TechnionTournament implements Tournament{

    TechnionTournament(){}
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
        Node<FacultyInTournament> newFacultyNode1 = new Node<>(newFaculty1);
        FacultyInTournament newFaculty2 = new FacultyInTournament(faculty);
        Node<FacultyInTournament> newFacultyNode2 = new Node<>(newFaculty2);
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
        Node<PlayerInTournament> new_pit1 = new Node<PlayerInTournament>(pit1);
        PlayerInTournament pit2 = new PlayerInTournament(player , 0 , faculty_id);
        Node<PlayerInTournament> new_pit2 = new Node<PlayerInTournament>(pit2);

        this.playerByIDTree.Insert(new_pit1);
        this.playerByGoalsThenIDTree.Insert(new_pit2);

        Node<FacultyInTournament> new_fit  = this.facultyByIDTree.search(faculty_id);
        int score = new_fit.node_content.score;

        PlayerInTournament[] players = new_fit.node_content.players;
        for(int i = 0 ; i < 11 ; i++)
        {
            if (players[i] == null)
            {
                players[i] = pit1;
                break;
            }
        }

        new_fit = this.facultyByScoreThenIDTree.search(faculty_id , score);
        players = new_fit.node_content.players;
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

        //Update faculty tree by id
        Node<FacultyInTournament> f1 = this.facultyByIDTree.search(faculty_id1);
        Node<FacultyInTournament> f2 = this.facultyByIDTree.search(faculty_id2);

        int prev_score1 = f1.node_content.score;
        int prev_score2 = f2.node_content.score;

        PlayerInTournament[] players1 = f1.node_content.players;
        PlayerInTournament[] players2 = f2.node_content.players;

        if (winner == 1)
            f1.node_content.updateScoreBy(3);
        else if (winner == 2)
            f2.node_content.updateScoreBy(3);
        else if (winner == 0) {
            f1.node_content.updateScoreBy(1);
            f2.node_content.updateScoreBy(1);
        }

        //Update faculty tree by score
        FacultyInTournament newFaculty1 = new FacultyInTournament(new Faculty(faculty_id1, f1.node_content.faculty.getName()), prev_score1, players1);
        Node<FacultyInTournament> newFacultyNode1 = new Node<>(newFaculty1);

        FacultyInTournament newFaculty2 = new FacultyInTournament(new Faculty(faculty_id2, f2.node_content.faculty.getName()), prev_score2, players2);
        Node<FacultyInTournament> newFacultyNode2 = new Node<>(newFaculty2);
        this.facultyByScoreThenIDTree.Insert(newFacultyNode2);

        if (winner == 1) {
            newFacultyNode1.node_content.updateScoreBy(3);
            this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id1, prev_score1);
            this.facultyByScoreThenIDTree.Insert(newFacultyNode1);
        }
        else if (winner == 2) {
            newFacultyNode2.node_content.updateScoreBy(3);
            this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id2, prev_score2);
            this.facultyByScoreThenIDTree.Insert(newFacultyNode2);
        }
        else if (winner == 0) {
            newFacultyNode1.node_content.updateScoreBy(1);
            this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id1, prev_score1);
            this.facultyByScoreThenIDTree.Insert(newFacultyNode1);

            newFacultyNode2.node_content.updateScoreBy(1);
            this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id2, prev_score2);
            this.facultyByScoreThenIDTree.Insert(newFacultyNode2);
        }

        //Update player tree by id

        for (int id : faculty1_goals) {
            Node<PlayerInTournament> p = this.playerByIDTree.search(id);
            int prev_goals = p.node_content.goals;
            p.node_content.updateGoalsBy(1);

            p = this.playerByGoalsThenIDTree.search(id, prev_goals);
            p.node_content.updateGoalsBy(1);
        }

        for (int id : faculty2_goals) {
            Node<PlayerInTournament> p = this.playerByIDTree.search(id);
            int prev_goals = p.node_content.goals;
            p.node_content.updateGoalsBy(1);

            p = this.playerByGoalsThenIDTree.search(id, prev_goals);
            p.node_content.updateGoalsBy(1);
        }

    }

    @Override
    public void getTopScorer(Player player) {
        Node<PlayerInTournament> player_node = this.playerByGoalsThenIDTree.max_leaf.predecessor;
        Player top_player = player_node.node_content.player;
        player.setName(top_player.getName());
        player.setId(top_player.getId());
    }

    @Override
    public void getTopScorerInFaculty(int faculty_id, Player player) {
        Node<FacultyInTournament> fid1 = this.facultyByIDTree.search(faculty_id);
        PlayerInTournament top_player = fid1.node_content.players[0];
        if(top_player != null) {
            player.setName(top_player.player.getName());
            player.setId(top_player.player.getId());
        }
        else //?
            player = null;
    }

    @Override
    public void getTopKFaculties(ArrayList<Faculty> faculties, int k, boolean ascending) {
        ArrayList<Faculty> top_k = new ArrayList<>();
        Node<FacultyInTournament> x = this.facultyByScoreThenIDTree.max_leaf; //sentinel

        for(int i=0; i<k; i++){
            x = x.predecessor;
            top_k.add(x.node_content.faculty);
        }

        if (!ascending)
            faculties.addAll(top_k);
        else {
            for(int i=k-1; i>=0; i--) {
                faculties.add(top_k.remove(i));
            }
        }
    }

    @Override
    public void getTopKScorers(ArrayList<Player> players, int k, boolean ascending) {
        ArrayList<Player> top_k = new ArrayList<>();
        Node<PlayerInTournament> x = this.playerByGoalsThenIDTree.max_leaf; //sentinel

        for(int i=0; i<k; i++){
            x = x.predecessor;
            top_k.add(x.node_content.player);
        }

        if (!ascending)
            players.addAll(top_k);
        else {
            for(int i=k-1; i>=0; i--) {
                players.add(top_k.remove(i));
            }
        }
    }

    @Override
    public void getTheWinner(Faculty faculty) {
        Node<FacultyInTournament> faculty_node = this.facultyByScoreThenIDTree.max_leaf.predecessor;
        Faculty top_faculty = faculty_node.node_content.faculty;
        faculty.setName(top_faculty.getName());
        faculty.setId(top_faculty.getId());
    }

}
