public class PlayerNode {

    private PlayerInTournament player;
    private int key;
    private PlayerNode player_left_son;
    private PlayerNode player_middle_son;
    private PlayerNode player_right_son;
    private PlayerNode player_parent;

    public PlayerNode(PlayerInTournament player) {
        this.player = player;
    }

    public PlayerNode(PlayerInTournament player, int key, PlayerNode player_left_son, PlayerNode player_middle_son,
                      PlayerNode player_right_son, PlayerNode player_parent) {
        this.player = player;
        this.key = key;
        this.player_left_son = player_left_son;
        this.player_middle_son = player_middle_son;
        this.player_right_son = player_right_son;
        this.player_parent = player_parent;
    }
}
