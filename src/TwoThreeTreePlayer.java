public abstract class TwoThreeTreePlayer implements TwoThreeTree {
    protected Node<PlayerInTournament> root;
    protected Node<PlayerInTournament> max_leaf;
    protected Node<PlayerInTournament> min_leaf;

    public TwoThreeTreePlayer() {

        this.root = new Node<PlayerInTournament>(new PlayerInTournament(new Player(Integer.MAX_VALUE, null), 0 ,  0)  , null);

        this.root.left = new Node<PlayerInTournament>(new PlayerInTournament(new Player(Integer.MIN_VALUE, null), Integer.MIN_VALUE ,  0)  , null);


        this.root.middle = new Node<PlayerInTournament>(new PlayerInTournament(new Player(Integer.MAX_VALUE, null), Integer.MAX_VALUE ,  0)  , null);
        this.root.right = null;

        this.root.left.parent = root;
        this.root.middle.parent = root;
        this.max_leaf = this.root.middle;
        this.min_leaf = this.root.left;
    }


    public Node<PlayerInTournament> Successor(Node x) {
        Node<PlayerInTournament> y;
        Node<PlayerInTournament> z = x.parent;

        while (x == z.right || (z.right == null && x == z.middle)) {
            x = z;
            z = z.parent;
        }

        if ( x == z.left)
            y = z.middle;
        else
            y = z.right;

        while (y.left != null) {
            y = y.left;
        }

        return y;
    }

    public Node<PlayerInTournament> Predecessor(Node x) {
        Node<PlayerInTournament> y;
        Node<PlayerInTournament> z = x.parent;

        while (x == z.left) {
            x = z;
            z = z.parent;
        }

        if (x == z.right)
            y = z.middle;
        else if (x == z.middle)
            y = z.left;
        else
            y = z.left;

        while (y.left != null) { //not a leaf
            if (y.right != null)
                y = y.right;
            else
                y = y.middle;
        }

        return y;
    }

    public void Update_Key(Node x){
        Node<PlayerInTournament> y = x;
        y.node_content.player.setId(y.left.node_content.player.getId());
        y.node_content.goals = y.left.node_content.goals;
        if (y.middle != null) {
            y.node_content.player.setId(y.middle.node_content.player.getId());
            y.node_content.goals = y.middle.node_content.goals;
        }
        if (x.right != null) {
            y.node_content.player.setId(y.right.node_content.player.getId());
            y.node_content.goals = y.right.node_content.goals;
        }
    }

    public void Set_Children(Node x,Node left, Node middle, Node right) {
        x.left = left;
        x.middle = middle;
        x.right = right;
        left.parent = x;
        if (middle != null)
            middle.parent = x;
        if (right != null)
            right.parent = x;
        Update_Key(x);
    }


    public Node borrowOrMerge(Node y)
    {
        Node<PlayerInTournament> z = y.parent;
        if (y==z.left)
        {
            Node<PlayerInTournament> x = z.middle;
            if (x.right != null)
            {
                Set_Children(y, y.left, x.left, null);
                Set_Children(x, x.middle, x.right, null);
            }
            else
            {
                Set_Children(x, y.left, x.left, x.middle);
                Set_Children(z, x, z.right, null);
            }
            return z;
        }

        if (y==z.middle)
        {
            Node<PlayerInTournament> x = z.left;
            if (x.right != null)
            {
                Set_Children(y, x.right, y.left, null);
                Set_Children(x, x.left, x.middle, null);
            }
            else
            {
                Set_Children(x, x.left, x.middle, y.left);
                Set_Children(z, x, z.right, null);
            }
            return z;
        }
        Node<PlayerInTournament> x = z.middle;
        if (x.right != null)
        {
            Set_Children(y, x.right , y.left , null);
            Set_Children(x , x.left , x.middle , null);
        }
        else
        {
            Set_Children(x, x.left , x.middle , y.left);
            Set_Children(z , z.left , x , null);
        }
        return z;

    }

    public void DeleteWithNode(Node x) {
        Node<PlayerInTournament> y = x.parent;

        if (x == y.left)
            Set_Children(y, y.middle, y.right, null);
        else if (x == y.middle)
            Set_Children(y, y.left, y.right, null);
        else
            Set_Children(y, y.left, y.middle, null);

        while (y != null) {
            if (y.middle == null) {
                if (y != this.root)
                    y = borrowOrMerge(y);
                else {
                    this.root = y.left;
                    y.left.parent = null;
                    return;
                }
            }
            else {
                Update_Key(y);
                y = y.parent;
            }
        }
    }
}
