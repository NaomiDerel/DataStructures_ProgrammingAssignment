public class FacultyByScoreThenIDTree extends FacultyTree {


    public FacultyByScoreThenIDTree() {
        //we need other construction becase the positive sentinal needs
        // to have infinite score and -infinte id so it would be maximal

        this.root = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(Integer.MIN_VALUE, null), Integer.MAX_VALUE ,  new PlayerInTournament[11]) , null);

        this.root.left = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(Integer.MAX_VALUE, null), Integer.MIN_VALUE ,  new PlayerInTournament[11]) , null);

        this.root.middle = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(Integer.MIN_VALUE, null), Integer.MAX_VALUE ,  new PlayerInTournament[11]) , null);
        this.root.right = null;

        this.root.left.parent = root;
        this.root.middle.parent = root;
        this.max_leaf = this.root.middle;
        this.min_leaf = this.root.left;
    }

    @Override
    public int compare(Node x , Node y)
    { //checks if x > y by score then checks if x.id<y.id ("x > y")
        Node<FacultyInTournament> xn = new Node<FacultyInTournament>((FacultyInTournament)x.node_content , x.parent);
        Node<FacultyInTournament> yn = new Node<FacultyInTournament>((FacultyInTournament)y.node_content , y.parent);
        if (yn.node_content.score == Integer.MIN_VALUE) {
            return 1;
        }
        if (yn.node_content.score == Integer.MAX_VALUE) {
            return -1;
        }
        if (xn.node_content.score == Integer.MIN_VALUE) {
            return -1;
        }
        if (xn.node_content.score == Integer.MAX_VALUE) {
            return 1;
        }
        if (xn.node_content.score > yn.node_content.score) return 1;
        else if (xn.node_content.score == yn.node_content.score)
        {
            if (yn.node_content.faculty.getId() == Integer.MIN_VALUE) {
                return -1;
            }
            else if (yn.node_content.faculty.getId() == Integer.MAX_VALUE) {
                return 1;
            }
            if (xn.node_content.faculty.getId() == Integer.MIN_VALUE) {
                return 1;
            }
            else if (xn.node_content.faculty.getId() == Integer.MAX_VALUE) {
                return -1;
            }
            if (xn.node_content.faculty.getId() < yn.node_content.faculty.getId())
            {
                return 1;
            }
            if (xn.node_content.faculty.getId() == yn.node_content.faculty.getId())
            {
                return 0;
            }
        }
        return -1;
    }


    public Node search(int id , int score)
    {
        Node<FacultyInTournament> X = this.root;
        return searchInner(id, score , X);
    }
    public Node searchInner(int id, int score , Node X) {
        Node<FacultyInTournament> n = X;

        if (id == n.node_content.faculty.getId() && n.left == null) {
            return n;
        }

        Node<FacultyInTournament> temp = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(id , null) , score , new PlayerInTournament[11]));

        if (compare(temp , n.left) <= 0) {
            return searchInner(id, score , n.left);
        }

        if (compare(temp , n.middle) <= 0) {
            return searchInner(id, score , n.middle);
        }

        return searchInner(id, score , n.right);
    }


    public Node Insert_And_Split(Node x,Node z){
        Node<FacultyInTournament> xn = x;
        Node<FacultyInTournament> zn = z;

        Node<FacultyInTournament> l = x.left;
        Node<FacultyInTournament> m = x.middle;
        Node<FacultyInTournament> r = x.right;
        if (r == null){
            if (compare(zn , l)== -1)
                Set_Children(x, z, l, m);
            else if (compare(zn , m)== -1)
                Set_Children(x, l, z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }

        Node<FacultyInTournament> y = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(0 , null) , 0 , new PlayerInTournament[11]));
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

        Node<FacultyInTournament> zn = z;

        Node<FacultyInTournament> y = this.root;
        Node<FacultyInTournament> x;

        while (y.left != null) {
            if (compare(zn , y.left)== -1)
                y = y.left;
            else if (compare(zn , y.middle)== -1)
                y = y.middle;
            else
                y = y.right;
        }

        x = y.parent;
        z = Insert_And_Split(x, zn);

        while (x != this.root) {
            x = x.parent;
            if (z != null)
                z = Insert_And_Split(x, z);
            else Update_Key(x);
        }

        if (z != null) {
            Node<FacultyInTournament> w = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(0 , null) , 0 , new PlayerInTournament[11]));
            Set_Children(w, x, z, null);
            this.root = w;
        }

        Node<FacultyInTournament> p =  this.Predecessor(zn);
        zn.predecessor = p;
        p.successor = zn;
        Node<FacultyInTournament> s =  this.Successor(zn);
        zn.successor = s;
        s.predecessor = zn;

    }

    public void DeleteWithGoalsAndID(int id , int goals)
    {
        Node<FacultyInTournament> X = this.search(id , goals);
        Node<FacultyInTournament> X_predecessor = X.predecessor;
        Node<FacultyInTournament> X_successor = X.successor;

        X_successor.setPredecessor(X_predecessor);
        X_predecessor.setSuccessor(X_successor);

        DeleteWithNode(X);
    }

}
