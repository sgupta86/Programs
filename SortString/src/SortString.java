/* This class represents program to sort list of Strings */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class: SortString
 * @author saurav
 *
 */
public class SortString {

	public static void main(String[] args) {
		
		List<String> listOfStrings= new ArrayList<String>();
		for(String s: args)
			listOfStrings.add(s);

		Collections.sort(listOfStrings);
		for(int i =0; i <listOfStrings.size();i++){
			System.out.println(listOfStrings.get(i));
		}

	}

}
