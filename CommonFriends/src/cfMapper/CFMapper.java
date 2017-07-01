/* This class represents the mapper class for finding common friends Program*/
package cfMapper;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Class: CFMapper
 * @author saurav
 */
public class CFMapper extends Mapper<Object, Text, Text, Text> {

    private Text friendsPair = new Text();      //represents pair of friends A,B
    private Text friendsList = new Text();      //represents list of friends B D E

    /* Map function
     * Input: Person with his list of friends
     * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
     */
    public void map(Object key, Text value, Context context) throws IOException,
            InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString(), "\n");
        String[] lineArray = null;
        String[] friendsArray = null;
        
        while (itr.hasMoreTokens()) {      	
        	lineArray = itr.nextToken().split(":");        	
        	friendsArray = lineArray[1].split(" ");
        	friendsList.set(lineArray[1]);
        	
        	//Sorting the pair of friends
        	for(int i=0; i<friendsArray.length; i++){       		
        		if(friendsArray[i].compareTo(lineArray[0]) > 0)
        			friendsPair.set(lineArray[0] + "," + friendsArray[i]);
        		else
        			friendsPair.set(friendsArray[i] + "," + lineArray[0]);
        		
        		context.write(friendsPair, friendsList);       
        	}            
        }
    }
}