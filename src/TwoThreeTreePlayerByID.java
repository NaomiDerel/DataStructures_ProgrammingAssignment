public class TwoThreeTreePlayerByID extends TwoThreeTreePlayer {
    public int compare(Node x , Node y) { // compare x , y , return 1 if x>1 , 0 if x = y , -1 if x<y
        Node<PlayerInTournament> xn = new Node<PlayerInTournament>((PlayerInTournament)x.node_content , x.parent);
        Node<PlayerInTournament> yn = new Node<PlayerInTournament>((PlayerInTournament)y.node_content , y.parent);
        if (yn.node_content.player.getId() == Integer.MIN_VALUE) {
            return 1;
        }
        if (yn.node_content.player.getId() == Integer.MAX_VALUE) {
            return -1;
        }
        if (xn.node_content.player.getId() == Integer.MIN_VALUE) {
            return -1;
        }
        if (xn.node_content.player.getId() == Integer.MAX_VALUE) {
            return 1;
        }
        if (xn.node_content.player.getId()  >  yn.node_content.player.getId()){
            return 1;
        }
        if (xn.node_content.player.getId()  ==  yn.node_content.player.getId()){
            return 0;
        }
        return -1;
    }

    public Node search(int id)
    {
        Node<PlayerInTournament> X = this.root;
        return searchInner(id , X);
    }
    public Node searchInner(int id , Node X) {
        Node<PlayerInTournament> n = X;

        if (id == n.node_content.player.getId() && n.left == null) {
            return n;
        }
        else if (id <= n.left.node_content.player.getId()) {
            return searchInner(id , n.left);
        }

        else if (id <= n.middle.node_content.player.getId()) {
            return searchInner(id , n.middle);
        }

        return searchInner(id , n.right);
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
        Node<PlayerInTournament> y = new Node<PlayerInTournament>(new PlayerInTournament(new Player(0 , null)));
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
            Node<PlayerInTournament> w = new Node<PlayerInTournament>(new PlayerInTournament(new Player(0 , null)));
            Set_Children(w, x, z, null);
            this.root = w;
        }
    }


}
