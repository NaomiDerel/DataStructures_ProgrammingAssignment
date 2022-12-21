public class PlayerNode {

    private Player player;
    private int faculty_id;
    private int goals;
    private PlayerNode left;
    private PlayerNode middle;
    private PlayerNode right;
    private PlayerNode parent;
    private PlayerNode predecessor;
    private PlayerNode successor;


    public PlayerNode(Player player) {
        this.player = player;
    }

    public PlayerNode(Player player, int goals, int faculty_id, PlayerNode player_parent) {
        this.player = player;
        this.goals = goals;
        this.faculty_id = faculty_id;
        this.left = null;
        this.middle = null;
        this.right = null;
        this.parent = player_parent;
    }
}
