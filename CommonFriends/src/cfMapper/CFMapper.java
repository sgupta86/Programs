package cfMapper;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CFMapper extends Mapper<Object, Text, Text, Text> {

    private Text friendsPair = new Text();
    private Text friendsList = new Text();

    public void map(Object key, Text value, Context context) throws IOException,
            InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        String[] lineArray = null;
        String[] friendsArray = null;
        
        while (itr.hasMoreTokens()) {      	
        	lineArray = itr.nextToken().split(":");        	
        	friendsArray = lineArray[1].split(" ");
        	friendsList.set(lineArray[1]);
        	
        	for(int i=0; i<friendsArray.length; i++){       		
        		if(friendsArray[i].compareTo(lineArray[0]) < 0)
        			friendsPair.set(lineArray[0] + "," + friendsArray[i]);
        		else
        			friendsPair.set(friendsArray[i] + "," + lineArray[0]);
        		
        		context.write(friendsPair, friendsList);
        	}            
        }
    }
}