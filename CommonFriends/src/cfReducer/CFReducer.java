package cfReducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CFReducer extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
    	Text[] flists = new Text[2];
        int i = 0;
        for(Text val: values)
        	flists[i++] = new Text(val.toString());       		
        
        String[] flist1 = flists[0].toString().split(" ");
        String[] flist2 = flists[1].toString().split(" ");
        
        List<String> commonfList = new ArrayList<String>();
        for(String friend1 : flist1){
                for(String friend2 : flist2){
                        if(friend1.equals(friend2)){
                        	commonfList.add(friend1);
                        }
                }
        }
        
        StringBuffer commonFriends = new StringBuffer();
        for(int j = 0; j < commonfList.size(); j++){
        	commonFriends.append(commonfList.get(j));
                if(j != commonfList.size() - 1)
                	commonFriends.append(" ");
        }
        
        context.write(key, new Text(commonFriends.toString()));
    }
}

