public class TwoThreeTreeFacultyID {

    private FacultyNode root;
    private FacultyNode max_leaf;

    public TwoThreeTreeFacultyID() {

        this.root = new FacultyNode(new Faculty(Integer.MAX_VALUE, null), null, 0 , new Player[11]);

        this.root.left = new FacultyNode(new Faculty(Integer.MIN_VALUE, null), null, Integer.MIN_VALUE , new Player[11]);
        this.root.middle = new FacultyNode(new Faculty(Integer.MAX_VALUE, null), null, Integer.MAX_VALUE , new Player[11]);
        this.root.right = null;

        this.root.left.parent = root;
        this.root.middle.parent = root;
    }

    public FacultyNode Successor(FacultyNode x) {
        FacultyNode y;
        FacultyNode z = x.parent;

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

        if (y.faculty.getId() < Integer.MAX_VALUE)
            return y;
        else return null;
    }

    public FacultyNode Predecessor(FacultyNode x) {
        FacultyNode y;
        FacultyNode z = x.parent;

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

        if (y.faculty.getId() < Integer.MAX_VALUE)
            return y;
        else return null;
    }

    public void Update_Key(FacultyNode x){
        x.faculty.setId(x.left.faculty.getId());
        if (x.middle != null) {
            x.faculty.setId(x.middle.faculty.getId());
        }
        if (x.right != null) {
            x.faculty.setId(x.right.faculty.getId());
        }
    }

    public void Set_Children(FacultyNode x,FacultyNode left, FacultyNode middle, FacultyNode right) {
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

    public FacultyNode Insert_And_Split(FacultyNode x,FacultyNode z){
        FacultyNode l = x.left;
        FacultyNode m = x.middle;
        FacultyNode r = x.right;
        if (r == null){
            if (z.faculty.getId() < l.faculty.getId())
                Set_Children(x, z, l, m);
            else if (z.faculty.getId() < m.faculty.getId())
                Set_Children(x, l, z, m);
            else
                Set_Children(x, l, m, z);
            return null;
        }
        FacultyNode y = new FacultyNode();
        if (z.faculty.getId() < l.faculty.getId()){
            Set_Children(x, z, l, null);
            Set_Children(y, m, r, null);
        }
        else if (z.faculty.getId() < m.faculty.getId()) {
            Set_Children(x, l, z, null);
            Set_Children(y, m, r, null);
        }
        else if (z.faculty.getId() < r.faculty.getId()) {
            Set_Children(x, l, m, null);
            Set_Children(y, z, r, null);
        }
        else {
            Set_Children(x, l, m, null);
            Set_Children(y, r, z, null);
        }
        return y;
    }

    public void Insert (FacultyNode z) {

        FacultyNode y = this.root;
        FacultyNode x;

        while (y.left != null) {
            if (z.faculty.getId() < y.left.faculty.getId())
                y = y.left;
            else if (z.faculty.getId() < y.middle.faculty.getId())
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
            FacultyNode w = new FacultyNode();
            Set_Children(w, x, z, null);
            this.root = w;
        }
    }


    public FacultyNode borrowOrMerge(FacultyNode y)
    {
        FacultyNode z = y.parent;
        if (y==z.left)
        {
            FacultyNode x = z.middle;
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
            FacultyNode x = z.left;
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
        FacultyNode x = z.middle;
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

    public void Delete(FacultyNode x) {
        FacultyNode y = x.parent;

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
