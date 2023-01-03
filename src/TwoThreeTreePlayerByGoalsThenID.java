public class TwoThreeTreePlayerByGoalsThenID extends TwoThreeTreePlayer
{



    public TwoThreeTreePlayerByGoalsThenID() {
        //we need other construction because the positive sentinal needs
        // to have infinite score and -infinte id so it would be maximal

        this.root = new Node<PlayerInTournament>(new PlayerInTournament(new Player(Integer.MIN_VALUE, null), 0 ,  0)  , null);

        this.root.left = new Node<PlayerInTournament>(new PlayerInTournament(new Player(Integer.MAX_VALUE, null), Integer.MIN_VALUE ,  0)  , null);


        this.root.middle = new Node<PlayerInTournament>(new PlayerInTournament(new Player(Integer.MIN_VALUE, null), Integer.MAX_VALUE ,  0)  , null);
        this.root.right = null;

        this.root.left.parent = root;
        this.root.middle.parent = root;
        this.max_leaf = this.root.middle;
        this.min_leaf = this.root.left;
    }

    @Override
    public int compare(Node x , Node y)
    { //checks if x > y by score then checks if x.id<y.id ("x > y")
        Node<PlayerInTournament> xn = new Node<PlayerInTournament>((PlayerInTournament)x.node_content , x.parent);
        Node<PlayerInTournament> yn = new Node<PlayerInTournament>((PlayerInTournament)y.node_content , y.parent);
        if (yn.node_content.goals == Integer.MIN_VALUE) {
            return 1;
        }
        if (yn.node_content.goals == Integer.MAX_VALUE) {
            return -1;
        }
        if (xn.node_content.goals == Integer.MIN_VALUE) {
            return -1;
        }
        if (xn.node_content.goals == Integer.MAX_VALUE) {
            return 1;
        }
        if (xn.node_content.goals > yn.node_content.goals) return 1;

        else if (xn.node_content.goals == yn.node_content.goals)
        {
            if (yn.node_content.player.getId() == Integer.MIN_VALUE) {
                return -1;
            }
            if (yn.node_content.player.getId() == Integer.MAX_VALUE) {
                return 1;
            }
            if (xn.node_content.player.getId() == Integer.MIN_VALUE) {
                return 1;
            }
            if (xn.node_content.player.getId() == Integer.MAX_VALUE) {
                return -1;
            }
            if (xn.node_content.player.getId() < yn.node_content.player.getId())
            {
                return 1;
            }

            if (xn.node_content.player.getId() == yn.node_content.player.getId())
            {
                return 0;
            }
        }
        return -1;
    }



    public Node Insert_And_Split(Node x,Node z){
        Node<PlayerInTournament> xn = x;
        Node<PlayerInTournament> zn = z;

        Node<PlayerInTournament> l = x.left;
        Node<PlayerInTournament> m = x.middle;
        Node<PlayerInTournament> r = x.right;
        if (r == null){
            if (compare(zn , l)== -1)
                Set_Children(x, z, l, m);
            else if (compare(zn , m)== -1)
                Set_Children(x, l, z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }
        Node<PlayerInTournament> y = new Node<PlayerInTournament>(new PlayerInTournament(new Player(0 , null) , 0 , 0));
        if (compare(zn , l)== -1){
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        }
        else if (compare(zn , m)== -1) {
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        }
        else if (compare(zn , r)== -1) {
            Set_Children(x, l, m, null);
            Set_Children(y, z, r, null);
        }
        else {
            Set_Children(x, l, m, null);
            Set_Children(y, r, z, null);
        }
        return y;
    }

    public void Insert (Node z) {

        Node<PlayerInTournament> zn = z;
        Node<PlayerInTournament> y = this.root;
        Node<PlayerInTournament> x;

        while (y.left != null) {
            if (compare(zn , y.left)== -1)
                y = y.left;
            else if (compare(zn , y.middle)== -1)
                y = y.middle;
            else
                y = y.right;
        }

        x = y.parent;
        z = Insert_And_Split(x, z);

        while (x != this.root) {
            x = x.parent;
            if (z != null)
                z = Insert_And_Split(x, z);
            else Update_Key(x);
        }

        if (z != null) {
            Node<PlayerInTournament> w = new Node<PlayerInTournament>(new PlayerInTournament(new Player(0 , null) , 0 , 0));
            Set_Children(w, x, z, null);
            this.root = w;
        }

        Node<PlayerInTournament> p =  this.Predecessor(zn);
        zn.predecessor = p;
        p.successor = zn;
        Node<PlayerInTournament> s =  this.Successor(zn);
        zn.successor = s;
        s.predecessor = zn;
    }

}

