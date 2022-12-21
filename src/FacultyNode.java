public class FacultyNode {

    private FacultyInTournament faculty;
    private FacultyNode faculty_left_son;
    private FacultyNode faculty_middle_son;
    private FacultyNode faculty_right_son;
    private FacultyNode faculty_parent;


    public FacultyNode(FacultyInTournament faculty) {
        this.faculty = faculty;
    }

    public FacultyNode(FacultyInTournament faculty, FacultyNode faculty_parent){

        this.faculty = faculty;
        this.faculty_parent = faculty_parent;
        this.faculty_left_son = null;
        this.faculty_middle_son = null;
        this.faculty_right_son = null;
    }



}
