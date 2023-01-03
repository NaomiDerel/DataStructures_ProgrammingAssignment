import java.util.*;
import java.lang.AssertionError;
public class Main {


    public static void main(String[] args) {

        TwoThreeTreePlayerByID T = new TwoThreeTreePlayerByID();
        Player p1 = new Player(5 , "A");
        PlayerInTournament pit1 = new PlayerInTournament(p1 , 0 , 10);
        Node<PlayerInTournament> npit1= new Node<>(pit1);
        T.Insert(npit1); //so far good

        Player p2 = new Player(6 , "B");
        PlayerInTournament pit2 = new PlayerInTournament(p2 , 0 , 10);
        Node<PlayerInTournament> npit2= new Node<>(pit2);
        T.Insert(npit2);

        Player p3 = new Player(7 , "C");
        PlayerInTournament pit3 = new PlayerInTournament(p3 , 0 , 10);
        Node<PlayerInTournament> npit3= new Node<>(pit3);
        T.Insert(npit3);

        Player p4 = new Player(3 , "D");
        PlayerInTournament pit4 = new PlayerInTournament(p4 , 0 , 10);
        Node<PlayerInTournament> npit4= new Node<>(pit4);
        T.Insert(npit4);

        Player p5 = new Player(4 , "E");
        PlayerInTournament pit5 = new PlayerInTournament(p5 , 0 , 10);
        Node<PlayerInTournament> npit5= new Node<>(pit5);
        T.Insert(npit5);

        Node<PlayerInTournament> X = T.Successor(npit1);
        Node<PlayerInTournament> Y = T.Predecessor(npit1);
        Node<PlayerInTournament> Ma = T.max_leaf;
        Node<PlayerInTournament> Mi = T.min_leaf;

        System.out.println(0);






//        TwoThreeTreeFacultyByScoreThenID T = new TwoThreeTreeFacultyByScoreThenID();
//        Faculty p1 = new Faculty(5 , "A");
//        FacultyInTournament pit1 = new FacultyInTournament(p1 , 8 , new Player[11]);
//        Node<FacultyInTournament> npit1= new Node<>(pit1);
//        T.Insert(npit1); //so far good
//
//        Faculty p2 = new Faculty(6 , "B");
//        FacultyInTournament pit2 = new FacultyInTournament(p2 , 6 , new Player[11]);
//        Node<FacultyInTournament> npit2= new Node<>(pit2);
//        T.Insert(npit2); //so far good
//
//        Faculty p3 = new Faculty(7 , "C");
//        FacultyInTournament pit3 = new FacultyInTournament(p3 , 6 , new Player[11]);
//        Node<FacultyInTournament> npit3= new Node<>(pit3);
//        T.Insert(npit3); //so far good
//
//        Faculty p4 = new Faculty(3 , "D");
//        FacultyInTournament pit4 = new FacultyInTournament(p4 , 1 , new Player[11]);
//        Node<FacultyInTournament> npit4= new Node<>(pit4);
//        T.Insert(npit4); //so far good
//
//        Faculty p5 = new Faculty(4 , "E");
//        FacultyInTournament pit5 = new FacultyInTournament(p5 , 7 , new Player[11]);
//        Node<FacultyInTournament> npit5= new Node<>(pit5);
//        T.Insert(npit5); //so far good
//
//        Node<FacultyInTournament> X = T.search(4 , 7);
//        T.DeleteWithGoalsAndID(4,7);
//
//        System.out.println(0);

    }



















































































//    public static void main(String[] args) {
//
//        /** Initializing the tournament **/
//        TechnionTournament tournament = new TechnionTournament();
//        tournament.init();
//        /** End of initializing the Tournament **/
//
//        /** Adding faculties to the Tournament **/
//        Map<Integer,String> faculties = new HashMap<>();
//        faculties.put(1,"CS");
//        faculties.put(2,"EE");
//        faculties.put(3,"IE");
//        faculties.put(4,"BME");
//		faculties.put(5,"MED");
//		faculties.put(6,"CE");
//
//        for(Map.Entry<Integer,String> f : faculties.entrySet()){
//            Faculty faculty = new Faculty(f.getKey(), f.getValue());
//            tournament.addFacultyToTournament(faculty);
//        }
//
//        /** Adding players to the Tournament **/
//        Player player = new Player(0,"");
//        String[] names = {"Asil","Yuval", "Noga", "Adam","Yuval", "Ziyech", "Moshe", "Amit", "Amir", "Omer", "Maroon", "Ido"};
//        int[] faculties_id = {1,1,2,2,3,3,4,4,5,5,6,6};
//        for (int i = 0; i < names.length; i++) {
//            player.setId(i+1);
//            player.setName(names[i]);
//            tournament.addPlayerToFaculty(faculties_id[i],player);
//        }
//
//
//        /** Playing some games between faculties **/
//        ArrayList<Integer> home_faculty_goals = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            home_faculty_goals.add(1);
//        }
//
//        ArrayList<Integer> away_faculty_goals = new ArrayList<>();
//        away_faculty_goals.add(5);
//        away_faculty_goals.add(6);
//
//        tournament.playGame(1,3,1,home_faculty_goals,away_faculty_goals);
//        tournament.getTopScorer(player);
//        boolean expression = player.getId() == 1 && player.getName().equals("Asil");
//        Assert(expression);
//
//        home_faculty_goals.clear();
//        away_faculty_goals.clear();
//
//        home_faculty_goals.add(6);
//        home_faculty_goals.add(6);
//        away_faculty_goals.add(7);
//        away_faculty_goals.add(8);
//
//        tournament.playGame(3,4,1,home_faculty_goals,away_faculty_goals);
//        tournament.getTopScorer(player);
//        expression =  player.getId() == 1 && player.getName().equals("Asil");
//        Assert(expression);
//
//        /** Removing Teams from Tournament and getting the best scorers **/
//        tournament.removeFacultyFromTournament(2);
//
//        ArrayList<Player> scorers = new ArrayList<>();
//        tournament.getTopKScorers(scorers,2,true);
//
//        expression = scorers.get(1).getId() == 1  && scorers.get(1).getName().equals("Asil");
//        Assert(expression);
//        expression = scorers.get(0).getId() == 6 && scorers.get(0).getName().equals("Ziyech");
//        Assert(expression);
//
//        tournament.getTopScorerInFaculty(1,player);
//        expression = player.getId() == 1;
//        Assert(expression);
//
//
//        home_faculty_goals.clear();
//        away_faculty_goals.clear();
//        tournament.playGame(4,1,0,home_faculty_goals,away_faculty_goals);
//
//        tournament.removePlayerFromFaculty(1,1);
//        tournament.getTopScorer(player);
//
//        expression = player.getId() == 1 && player.getName().equals("Asil");
//        Assert(expression);
//
//        ArrayList<Faculty> top_faculties = new ArrayList<>();
//        tournament.getTopKFaculties(top_faculties,2,false);
//
//        /** pay attention that until now we have current standing **/
//        /*
//           ____________________________________
//           || Team_id || Team_name || Points ||
//           ||_________||___________||________||
//           ||    1    ||    CS     ||    4   ||
//           ||    3    ||    IE     ||    3   ||
//           ||    4    ||    CE     ||    1   ||
//           ||_________||___________||________||
//
//         */
//
//        expression = top_faculties.get(0).getId() == 1 && top_faculties.get(0).getName().equals("CS");
//        Assert(expression);
//        expression = top_faculties.get(1).getId() == 3 && top_faculties.get(1).getName().equals("IE");
//        Assert(expression);
//
//        /** Get the winner faculty **/
//        Faculty faculty = new Faculty(0,"");
//        tournament.getTheWinner(faculty);
//        expression = faculty.getId() == 1 && faculty.getName().equals("CS");
//        Assert(expression);
//
//        System.out.println("Congratulations You Have passed the Test ");
//    }
//
//    public static void Assert(boolean expression){
//        if (!expression){
//            throw new AssertionError();
//        }
//    }
}