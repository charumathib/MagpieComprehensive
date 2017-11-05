import java.util.Hashtable;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
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
            this.responseMap.put(s, "family");
        }

        for(String s : Arrays.asList("dog", "cat", "fish", "sheep", "bird")){
            this.responseMap.put(s, "pets");
        }

    }
    /** 
     * Statement which is the input
     */
    private String statement ; 

    /**
     * Request Response Map
     */
    private Hashtable <String, String> responseMap = new Hashtable <String, String>();

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
        Set<String> responseSet = new HashSet<String>();
        for (String key : responseMap.keySet()) { 
            if ( contains(key)) { 
                responseSet.add(responseMap.get(key));
            }
        }

        if(responseSet.isEmpty()){
            if(contains("no", "nope", "never")){
                return "Why so negative?";
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
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";

        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }

        return response;
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
        String response = "Tell me more about your ";
        for (String s : responseSet) { 
            response += s + " and ";
        }
        response = response.substring(0, response.length()-4);
        return response;
    }

}
