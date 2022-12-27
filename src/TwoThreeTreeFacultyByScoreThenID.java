public class TwoThreeTreeFacultyByScoreThenID extends TwoThreeTreeFaculty{


    public TwoThreeTreeFacultyByScoreThenID() {
        //we need other construction becase the positive sentinal needs
        // to have infinite score and -infinte id so it would be maximal

        this.root = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(Integer.MAX_VALUE, null), 0 ,  new Player[11]) , null);

        this.root.left = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(Integer.MAX_VALUE, null), Integer.MIN_VALUE ,  new Player[11]) , null);

        this.root.middle = new Node<FacultyInTournament>(new FacultyInTournament(new Faculty(Integer.MIN_VALUE, null), Integer.MAX_VALUE ,  new Player[11]) , null);
        this.root.right = null;

        this.root.left.parent = root;
        this.root.middle.parent = root;
    }

    @Override
    public Boolean compare(Node x , Node y)
    { //checks if x > y by score then checks if x.id<y.id ("x > y")
        Node<FacultyInTournament> xn = new Node<FacultyInTournament>((FacultyInTournament)y.node_content , y.parent);
        Node<FacultyInTournament> yn = new Node<FacultyInTournament>((FacultyInTournament)y.node_content , y.parent);
        if (xn.node_content.score > yn.node_content.score) return true;
        else if (xn.node_content.score == yn.node_content.score)
        {
            if (xn.node_content.faculty.getId() < yn.node_content.faculty.getId())
            {
                return true;
            }
        }
        return false;
    }


    public Node Insert_And_Split(Node x,Node z){
        Node<FacultyInTournament> xn = z;
        Node<FacultyInTournament> zn = z;

        Node<FacultyInTournament> l = x.left;
        Node<FacultyInTournament> m = x.middle;
        Node<FacultyInTournament> r = x.right;
        if (r == null){
            if (!compare(zn , l))
                Set_Children(x, z, l, m);
            else if (!compare(zn , m))
                Set_Children(x, l, z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }
        Node<FacultyInTournament> y = new Node<FacultyInTournament>();
        if (!compare(zn , l)){
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        }
        else if (!compare(zn , m)) {
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        }
        else if (!compare(zn , r)) {
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
            if (!compare(zn , y.left))
                y = y.left;
            else if (!compare(zn , y.middle))
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
            Node<FacultyInTournament> w = new Node<FacultyInTournament>();
            Set_Children(w, x, z, null);
            this.root = w;
        }
    }

}
