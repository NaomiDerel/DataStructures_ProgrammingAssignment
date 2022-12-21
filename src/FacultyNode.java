public class FacultyNode {

    protected Faculty faculty;
    protected int score;
    protected Player[] players;

    protected FacultyNode left;
    protected FacultyNode middle;
    protected FacultyNode right;
    protected FacultyNode parent;
    protected FacultyNode predecessor;
    protected FacultyNode successor;


    public FacultyNode() {
    }

    public FacultyNode(Faculty faculty) {
        this.faculty = faculty;
        this.score = 0;
        this.players = new Player[11];

        this.parent = null;
        this.left = null;
        this.middle = null;
        this.right = null;
    }

    public FacultyNode(Faculty faculty, int score , Player[] players){

        this.faculty = faculty;
        this.score = score;
        this.players = players;

        this.parent = null;
        this.left = null;
        this.middle = null;
        this.right = null;
    }

    public FacultyNode(Faculty faculty, FacultyNode faculty_parent , int score , Player[] players){

        this.faculty = faculty;
        this.score = score;
        this.players = players;

        this.parent = faculty_parent;
        this.left = null;
        this.middle = null;
        this.right = null;
    }



}
