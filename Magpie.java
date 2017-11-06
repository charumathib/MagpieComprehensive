import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{

    public Magpie(){
        for(String s : Arrays.asList("father", "mother", "sister", "brother", "aunt", "uncle", "grandma", "grandpa", "dad", "mom")){
            this.responseMap.put(s, "1your family");
        }

        for(String s : Arrays.asList("dog", "cat", "fish", "sheep", "bird")){
            this.responseMap.put(s, "2your pets");
        }

        for(String s : Arrays.asList("mr.leath", "mr.cavanaugh", "mr.rue", "mr.cerrone", "Dr.kopreski", "mr.cerrone")){
            this.responseMap.put(s.toLowerCase(), "3him, he sounds like a great teacher");
        }

        for(String s : Arrays.asList("mrs.o'shaugnessey", "dr.ross", "dr.gueye", "mrs.heath", "ms.everett")){
            this.responseMap.put(s.toLowerCase(), "3her, she sounds like a great teacher");
        }

    }
    /** 
     * Statement which is the input
     */
    private String statement ; 

    /**
     * Request Response Map
     */
    private Map <String, String> responseMap = new LinkedHashMap <String, String>();

    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }

    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        this.statement = statement.toLowerCase();
        String response = "";
        Set<String> responseSet = new TreeSet<String>();
        for (String key : responseMap.keySet()) { 
            if (contains(key)) { 
                responseSet.add(responseMap.get(key));
            }
        }

        if(responseSet.isEmpty()){
            if(contains("no", "nope", "never")){
                return "Why so negative?";
            }
            if(statement.trim().length() == 0){
                return "Say something please";
            }
            return getRandomResponse();
        } 

        return getValidResponse(responseSet);

    }

    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        String[] defaultResponses = {"Interesting, tell me more", "Hmm", "Do you really think so?", "You don't say", "Why do you think so", "Maybe...", "Oh, ok"};
        int whichResponse = (int)(Math.random() * defaultResponses.length);
        return defaultResponses[whichResponse];
    }

    private boolean contains(String ... expected) { 

        for ( String s : expected) { 
            if ( this.statement.indexOf(s) >= 0) { 
                return true ;
            }
        }
        return false;
    }

    private String getValidResponse(Set<String> responseSet){
        String response = "Tell me more about ";
        String tempResponse = "";
        for (String s : responseSet) { 
            s = s.substring(1);
            response += s  + " and ";
        }
        response = response.substring(0, response.length()-4);
        return response;
    }

}
