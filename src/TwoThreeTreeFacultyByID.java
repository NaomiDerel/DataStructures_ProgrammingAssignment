public class TwoThreeTreeFacultyByID extends TwoThreeTreeFaculty{


    public TwoThreeTreeFacultyByID() {
    }


    @Override
    public Boolean compare(Node x , Node y) { //checks if x.id > y.id , "x > y"
        Node<FacultyInTournament> xn = new Node<FacultyInTournament>((FacultyInTournament)y.node_content , y.parent);
        Node<FacultyInTournament> yn = new Node<FacultyInTournament>((FacultyInTournament)y.node_content , y.parent);
        return (xn.node_content.faculty.getId() - yn.node_content.faculty.getId() > 0);
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
