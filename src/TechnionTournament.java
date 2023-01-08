import java.util.ArrayList;

public class TechnionTournament implements Tournament {

    // This tree contains the faculties in the tournament, sorted by id.
    // We use it to find any faculty by its id in O(log(n)).
    // Use to extract the score in order to find a faculty in the second tree.
    // Contains players.
    protected FacultyByIDTree facultyByIDTree;


    // This tree contains the faculties in the tournament, sorted by their score first and id second.
    // We can find a faculty in it through the finding the score from facultyByIDTree in O(log(n)).
    // Do not use it to find players!
    protected FacultyByScoreThenIDTree facultyByScoreThenIDTree;


    // This tree contains the players in the tournament, sorted by id.
    // We use it to find any player (free agent or from faculty) by its id in O(log(n)).
    // Use to extract only the basic information on it: player id and name, goals.
    protected PlayerByIDTree playerByIDTree;


    // This tree contains the players in the tournament, sorted by score first and id second.
    // We can find a player in it through the finding the goals from playerByIDTree in O(log(n)).
    protected PlayerByGoalsThenIDTree playerByGoalsThenIDTree;

    TechnionTournament() {
    }

    /**
     * Initialize the 4 data structures we need to maintain the tournament.
     */
    @Override
    public void init() {
        this.facultyByIDTree = new FacultyByIDTree();
        this.facultyByScoreThenIDTree = new FacultyByScoreThenIDTree();
        this.playerByIDTree = new PlayerByIDTree();
        this.playerByGoalsThenIDTree = new PlayerByGoalsThenIDTree();
    }

    /**
     * Add the new faculty to the tournament, by updating both data structures in charge of faculties.
     *
     * @param faculty - new faculty with id and name.
     */
    @Override
    public void addFacultyToTournament(Faculty faculty) {
        FacultyInTournament newFaculty1 = new FacultyInTournament(faculty);
        Node<FacultyInTournament> newFacultyNode1 = new Node<>(newFaculty1);
        this.facultyByIDTree.Insert(newFacultyNode1);

        FacultyInTournament newFaculty2 = new FacultyInTournament(faculty);
        Node<FacultyInTournament> newFacultyNode2 = new Node<>(newFaculty2);
        this.facultyByScoreThenIDTree.Insert(newFacultyNode2);
    }

    /**
     * Remove the faculty matching faculty_id key (id is unique) from the tournament,
     * by removing it from both data structures in charge of faculties.
     * Update the players in the faculty to be free agents.
     * Update both player trees with players as free agents.
     *
     * @param faculty_id - faculty identifier.
     */
    @Override
    public void removeFacultyFromTournament(int faculty_id) {

        Node<FacultyInTournament> faculty_to_remove = this.facultyByIDTree.search(faculty_id);
        int wGoals = faculty_to_remove.node_content.score;
        PlayerInTournament[] players = faculty_to_remove.node_content.players;

        // Update both faculty trees
        this.facultyByIDTree.DeleteWithID(faculty_id);
        this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id, wGoals);

        int player_goals;
        for (int i = 0; i < 11 && players[i] != null; i++) {
            // Update playerByIDTree
            int pID = players[i].player.getId();
            Node<PlayerInTournament> player_node = this.playerByIDTree.search(pID);
            player_node.node_content.faculty_id = 0;

            // Update playerByGoalsThenIDTree
            player_goals = player_node.node_content.goals;
            player_node = this.playerByGoalsThenIDTree.search(pID, player_goals);
            player_node.node_content.faculty_id = 0;
        }

    }

    /**
     * Add a new player to both player trees, with faculty identifier.
     * Add a new player to both faculty trees in the players array of the correct faculty.
     *
     * @param faculty_id - faculty identifier
     * @param player     - new Player object (need to clone data)
     */
    @Override
    public void addPlayerToFaculty(int faculty_id, Player player) {
        Node<FacultyInTournament> fit;
        PlayerInTournament[] players;
        int score;

        Player p = new Player(player.getId(), player.getName());
        PlayerInTournament pit = new PlayerInTournament(p, 0, faculty_id);

        // Update playerByIDTree
        Node<PlayerInTournament> new_pit1 = new Node<PlayerInTournament>(pit);
        this.playerByIDTree.Insert(new_pit1);

        // Update playerByGoalsThenIDTree
        Node<PlayerInTournament> new_pit2 = new Node<PlayerInTournament>(pit);
        this.playerByGoalsThenIDTree.Insert(new_pit2);

        // Add player to facultyByIDTree
        fit = this.facultyByIDTree.search(faculty_id);
        score = fit.node_content.score;
        players = fit.node_content.players;
        insert_new_player(players, pit);

        // Add player to facultyByScoreThenIDTree
//        fit = this.facultyByScoreThenIDTree.search(faculty_id, score);
//        players = fit.node_content.players;
//        insert_new_player(players, pit);
    }

    /**
     * Add a new player to the faculty players array at the first empty spot.
     *
     * @param players - array of players in faculty.
     * @param pit - new player.
     */
    private void insert_new_player(PlayerInTournament[] players, PlayerInTournament pit) {
        for (int i = 0; i < 11; i++) {
            if (players[i] == null) {
                players[i] = pit;
                break;
            }
        }
    }

    /**
     *
     * @param faculty_id
     * @param player_id
     */
    @Override
    public void removePlayerFromFaculty(int faculty_id, int player_id) {
        Node<PlayerInTournament> p;

        // Remove player from facultyByIDTree
        Node<FacultyInTournament> f = this.facultyByIDTree.search(faculty_id);
        int score = f.node_content.score;

        PlayerInTournament[] players = f.node_content.players;
        remove_player(player_id, players);

//        f = this.facultyByScoreThenIDTree.search(faculty_id , score);
//        players = f.node_content.players;
//        for (int i = 0; i < 11 ; i++)
//        {
//            if(players[i].player.getId() == player_id){
//                players[i] = null;
//                break;
//            }
//        }

        // Turn player into free agent in playerByIDTree
        p = this.playerByIDTree.search(player_id);
        int goals = p.node_content.goals;
        p.node_content.faculty_id = 0;

        // Turn player into free agent in playerByGoalsThenIDTree
        p = this.playerByGoalsThenIDTree.search(player_id, goals);
        p.node_content.faculty_id = 0;

    }

    /**
     * Remove a player from an array of players and move all after it to the left.
     *
     * @param player_id - player to remove
     * @param players - array of players
     */
    private void remove_player(int player_id, PlayerInTournament[] players) {
        boolean found = false;
        for (int i = 0; i < 11; i++) {
            if (players[i] != null && players[i].player.getId() == player_id) {
                players[i] = null;
                found = true;
            } else if (found) {
                players[i - 1] = players[i];
                players[i] = null;
            }
        }
    }

    @Override
    public void playGame(int faculty_id1, int faculty_id2, int winner,
                         ArrayList<Integer> faculty1_goals, ArrayList<Integer> faculty2_goals) {

        Node<PlayerInTournament> p;

        Node<FacultyInTournament> f1 = this.facultyByIDTree.search(faculty_id1);
        Node<FacultyInTournament> f2 = this.facultyByIDTree.search(faculty_id2);

        int prev_score1 = f1.node_content.score;
        int prev_score2 = f2.node_content.score;

        PlayerInTournament[] players1 = f1.node_content.players;
        PlayerInTournament[] players2 = f2.node_content.players;

        //Update facultyByIDTree
        if (winner == 1)
            f1.node_content.updateScoreBy(3);
        else if (winner == 2)
            f2.node_content.updateScoreBy(3);
        else if (winner == 0) {
            f1.node_content.updateScoreBy(1);
            f2.node_content.updateScoreBy(1);
        }

        //Update facultyByScoreThenIDTree
        FacultyInTournament newFaculty1 = new FacultyInTournament(new Faculty(faculty_id1, f1.node_content.faculty.getName()), prev_score1, players1);
        Node<FacultyInTournament> newFacultyNode1 = new Node<>(newFaculty1);

        FacultyInTournament newFaculty2 = new FacultyInTournament(new Faculty(faculty_id2, f2.node_content.faculty.getName()), prev_score2, players2);
        Node<FacultyInTournament> newFacultyNode2 = new Node<>(newFaculty2);

        if (winner == 1) {
            newFacultyNode1.node_content.updateScoreBy(3);
            this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id1, prev_score1);
            this.facultyByScoreThenIDTree.Insert(newFacultyNode1);
        } else if (winner == 2) {
            newFacultyNode2.node_content.updateScoreBy(3);
            this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id2, prev_score2);
            this.facultyByScoreThenIDTree.Insert(newFacultyNode2);
        } else if (winner == 0) {
            newFacultyNode1.node_content.updateScoreBy(1);
            this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id1, prev_score1);
            this.facultyByScoreThenIDTree.Insert(newFacultyNode1);

            newFacultyNode2.node_content.updateScoreBy(1);
            this.facultyByScoreThenIDTree.DeleteWithGoalsAndID(faculty_id2, prev_score2);
            this.facultyByScoreThenIDTree.Insert(newFacultyNode2);
        }

        //Update playerByIDTree and playerByGoalsThenIDTree

        PlayerInTournament newPlayer;
        Node<PlayerInTournament> newPlayerNode;
        ArrayList<Integer> all_goals = new ArrayList<Integer>();
        all_goals.addAll(faculty1_goals);
        all_goals.addAll(faculty2_goals);

        for (int id : all_goals) {
            p = this.playerByIDTree.search(id);
            int prev_goals = p.node_content.goals;
            p.node_content.updateGoalsBy(1);

            p = this.playerByGoalsThenIDTree.search(id, prev_goals);
            newPlayer = new PlayerInTournament(new Player(id, p.node_content.player.getName()), prev_goals, p.node_content.faculty_id);
            newPlayerNode = new Node<PlayerInTournament>(newPlayer);
            newPlayerNode.node_content.updateGoalsBy(1);
            this.playerByGoalsThenIDTree.DeleteWithNode(p);
            this.playerByGoalsThenIDTree.Insert(newPlayerNode);

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
        Player top_player = fid1.node_content.findMax();
        if (top_player != null) {
            player.setName(top_player.getName());
            player.setId(top_player.getId());
        } else
            player = null; // Do something else?
    }

    @Override
    public void getTopKFaculties(ArrayList<Faculty> faculties, int k, boolean ascending) {
        ArrayList<Faculty> top_k = new ArrayList<>();
        Node<FacultyInTournament> x = this.facultyByScoreThenIDTree.max_leaf; //sentinel

        // find top k faculties
        for (int i = 0; i < k; i++) {
            x = x.predecessor;
            top_k.add(x.node_content.faculty);
        }


        if (!ascending)
            faculties.addAll(top_k);
        else {
            for (int i = k - 1; i >= 0; i--) {
                faculties.add(top_k.remove(i));
            }
        }
    }

    @Override
    public void getTopKScorers(ArrayList<Player> players, int k, boolean ascending) {
        ArrayList<Player> top_k = new ArrayList<>();
        Node<PlayerInTournament> x = this.playerByGoalsThenIDTree.max_leaf; //sentinel

        for (int i = 0; i < k; i++) {
            x = x.predecessor;
            top_k.add(x.node_content.player);
        }

        if (!ascending)
            players.addAll(top_k);
        else {
            for (int i = k - 1; i >= 0; i--) {
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
