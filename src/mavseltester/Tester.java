package mavseltester;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import com.uah.graph.MavselEdge;
import com.uah.graph.MavselVertex;
import com.uah.graph.PajekAdapter;
import com.uah.graph.SoniaAdapter;
import com.uah.items.*;
import com.uah.main.dokeos.DokeosLMS;
import com.uah.main.moodle.MoodleLMS;
import edu.uci.ics.jung.graph.Graph;
import java.util.List;



/**
 *
 * @author Pablo
 */
public class Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try{           

            System.out.println("----------------------------");
            System.out.println("----Moodle Course-----------");
            System.out.println("----------------------------");
            
            LMS miLMSmoodle = new MoodleLMS();
            
            //TODO modify the parameters
            miLMSmoodle.configureLMS("URL", "PORT", "Database_Name", "User", "Password");

            //TODO modify idCourse parameter
            Course courseMoodle = miLMSmoodle.getCourse("1");
            
            System.out.println(""+courseMoodle.getFullname());
            System.out.println(""+courseMoodle.getShortname());
            System.out.println(""+courseMoodle.getSummary());
            System.out.println("----------------------------");

            List<Forum> forumsMoodle = miLMSmoodle.getForums(courseMoodle);

            for (Forum forum : forumsMoodle){
                System.out.println("Forum title["+forum.getTitle()+"]");
                List<Discussion> discussionsMoodle = miLMSmoodle.getDiscussions(forum);

                for (Discussion discussion : discussionsMoodle){
                   System.out.println("----Discussion title["+discussion.getTitle()+"]");

                   List<Post> postsMoodle = miLMSmoodle.getPosts(discussion);

                   for (Post post : postsMoodle){
                       System.out.println("--------Post message["+post.getMessage()+"]");
                       Participant participantMoodle = miLMSmoodle.getParticipant(post.getIdParticipant());
                       System.out.println("--------Writen by participant-username["+participantMoodle.getUsername()+"]");                   }
                }
                System.out.println("----------------------------");
            }
            
            System.out.println("\n-----------------------------------------------------");
            System.out.println("---------Moodle Participant-Forum GRAPH--------------");            
            Graph<MavselVertex, MavselEdge> graphMoodle = miLMSmoodle.getParticipantForumGraph(courseMoodle);
            System.out.println(graphMoodle.toString());
            
            PajekAdapter.export("C:/MavselTesterFiles/pajekGraphMoodle.txt", graphMoodle);
            System.out.println("---------PAJEK file created"); 
                           
            SoniaAdapter.export("C:/MavselTesterFiles/soniaGraphMoodle.txt", graphMoodle);
            System.out.println("---------SONIA file created");  
                          
            miLMSmoodle.getForumRaitingInRFile("C:/MavselTesterFiles/ratingsMoodle.dat");
            System.out.println("---------R File created\n\n"); 

            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            
            System.out.println("----------------------------");
            System.out.println("----DOKEOS Course-----------");
            System.out.println("----------------------------");
            LMS miLMSdokeos = new DokeosLMS();
            
            //TODO modify the parameters
            miLMSdokeos.configureLMS("URL", "PORT", "Database_Name", "User", "Password");
         
            //TODO modify idCourse parameter
            Course courseDokeos = miLMSdokeos.getCourse("1");

            System.out.println(""+courseDokeos.getFullname());
            System.out.println(""+courseDokeos.getShortname());
            System.out.println(""+courseDokeos.getSummary());
            System.out.println("----------------------------");

            List<Forum> forumsDokeos = miLMSdokeos.getForums(courseDokeos);

            for (Forum forum : forumsDokeos){
                System.out.println("Forum title["+forum.getTitle()+"]");
                List<Discussion> discussionsDokeos = miLMSdokeos.getDiscussions(forum);

                for (Discussion discussion : discussionsDokeos){
                   System.out.println("----Discussion title["+discussion.getTitle()+"]");

                   List<Post> postsDokeos = miLMSdokeos.getPosts(discussion);

                   for (Post post : postsDokeos){
                       System.out.println("--------Post message["+post.getMessage()+"]");
                       Participant participantDokeos = miLMSdokeos.getParticipant(post.getIdParticipant());
                       System.out.println("--------Writen by participant-username["+participantDokeos.getUsername()+"]");                   }
                }
                System.out.println("----------------------------");
            }

            System.out.println("\n-----------------------------------------------------");
            System.out.println("---------Dokeos Participant-Forum GRAPH--------------");
            
            Graph<MavselVertex, MavselEdge> graphDokeos = miLMSdokeos.getParticipantForumGraph(courseDokeos);            
            System.out.println(graphDokeos.toString());
            
            System.out.println("--------> Pajek file created");
            PajekAdapter.export("C:/MavselTesterFiles/pajekGraphDokeos.txt", graphDokeos);
            
            
            System.out.println("--------> SONIA file created");                  
            SoniaAdapter.export("C:/MavselTesterFiles/soniaGraphDokeos.txt", graphDokeos);
            
            //System.out.println("-------- R file Created------------");            
            //DOKEOS doesn't support this operation           
            
        }catch(Exception e){
           e.printStackTrace();
        }
    }
}
