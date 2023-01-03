public class TwoThreeTreeFacultyByID extends TwoThreeTreeFaculty{


    public TwoThreeTreeFacultyByID() {
    }


    @Override
    public int compare(Node x , Node y) { //checks if x.id > y.id , "x > y"
        Node<FacultyInTournament> xn = new Node<FacultyInTournament>((FacultyInTournament)x.node_content , x.parent);
        Node<FacultyInTournament> yn = new Node<FacultyInTournament>((FacultyInTournament)y.node_content , y.parent);
        if (yn.node_content.faculty.getId() == Integer.MIN_VALUE) {
            return 1;
        }
        if (yn.node_content.faculty.getId() == Integer.MAX_VALUE) {
            return -1;
        }
        if (xn.node_content.faculty.getId() == Integer.MIN_VALUE) {
            return -1;
        }
        if (xn.node_content.faculty.getId() == Integer.MAX_VALUE) {
            return 1;
        }
        if (xn.node_content.faculty.getId() > yn.node_content.faculty.getId()){
            return 1;
        }
        if (xn.node_content.faculty.getId() == yn.node_content.faculty.getId()){
            return 1;
        }
        return -1;
    }


    public Node search(int id)
    {
        Node<FacultyInTournament> X = this.root;
        return searchInner(id , X);
    }
    public Node searchInner(int id , Node X) {
        Node<FacultyInTournament> n = X;

        if (id == n.node_content.faculty.getId() && n.left == null) {
            return n;
        }
        else if (id <= n.left.node_content.faculty.getId()) {
            return searchInner(id , n.left);
        }

        else if (id <= n.middle.node_content.faculty.getId()) {
            return searchInner(id , n.middle);
        }

        return searchInner(id , n.right);
    }

    public Node Insert_And_Split(Node x,Node z){
        Node<FacultyInTournament> xn = x;
        Node<FacultyInTournament> zn = z;

        Node<FacultyInTournament> l = x.left;
        Node<FacultyInTournament> m = x.middle;
        Node<FacultyInTournament> r = x.right;
        if (r == null){
            if (compare(zn , l) == -1)
                Set_Children(x, z, l, m);
            else if (compare(zn , m)== -1)
                Set_Children(x, l, z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }
        Node<FacultyInTournament> y = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(0 , null)));
        if (compare(zn , l) == -1){
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        }
        else if (compare(zn , m) == -1) {
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        }
        else if (compare(zn , r) == -1) {
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
            if (compare(zn , y.left) == -1)
                y = y.left;
            else if (compare(zn , y.middle) == -1)
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
            Node<FacultyInTournament> w = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(0 , null)));
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

    public void DeleteWithID(int id)
    {
        Node<FacultyInTournament> X = this.search(id);
        DeleteWithNode(X);
    }
}
