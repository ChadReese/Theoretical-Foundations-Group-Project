import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

public class GroupProject {
	private static ArrayList<String> alphabet = new ArrayList();
	private static ArrayList<String> states = new ArrayList();
	private static String startState;
	private static String acceptState;
	private static ArrayList<String> transitionFunctions = new ArrayList();

public static void main(String[] args) throws FileNotFoundException {

	File file = new File("C:/Users/Chad/Desktop/CS/Eclipse/GroupProject/src/Example.txt");
	Scanner scanner = new Scanner(file);
		
	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

	try {
		//Adding alphabets
		String line = null;
		line = reader.readLine();
		String firstAlph = line.substring(1,2);
		String secondAlph = line.substring(3,4);
		alphabet.add(firstAlph);
		alphabet.add(secondAlph);
		
		//Adding states
		line = reader.readLine();
		String firstState = line.substring(1,2);
		String secondState = line.substring(3,4);
		String thirdState = line.substring(5,6);
		String fourthState = line.substring(7,8);
		states.add(firstState);
		states.add(secondState);
		states.add(thirdState);
		states.add(fourthState);
		
		//Add start state
		line = reader.readLine();
		startState = line;
		
		//Add accept states
		line = reader.readLine();
		line = line.substring(1,2);
		acceptState = line;
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println(alphabet);
	System.out.println(states);
	System.out.println(startState);
	System.out.println(acceptState);
	}

}
