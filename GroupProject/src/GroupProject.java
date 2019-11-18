import java.awt.Component;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GroupProject {
	private FileReader reader;
	private BufferedReader buffer;
	ArrayList<Character> states = new ArrayList();
	ArrayList<Character> acceptStates = new ArrayList();
	private char startState;
	int alphabet;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String path;
		

		 JPanel pane = new JPanel();
	     pane.setLayout(new GridLayout(2, 2, 2, 2));
	     
	     JTextField pathField = new JTextField(5);
	     JTextField stringField = new JTextField(5);
	     
	     pane.add(new JLabel("Please enter a file path:"));
	     pane.add(pathField);
	   

	     pane.add(new JLabel("Please enter a string to test:"));
	     pane.add(stringField);
	     
	     in.close();
	    
	     Component frame = null;
	     JOptionPane.showMessageDialog(frame, pane);
		  path = pathField.getText();
		  String input = stringField.getText();
		/*path = JOptionPane.showInputDialog("Enter a file path.");
		String input = JOptionPane.showInputDialog("Enter a string to be tested.");
*/

		

		GroupProject sp = new GroupProject(path);
		DFA dfa = sp.create();
		
		if(dfa.checkString(input)) {
			JOptionPane.showMessageDialog(frame,"String accepted!");
		}
		else {
			JOptionPane.showMessageDialog(frame,"String rejected.");
		}
	}

	public GroupProject(String file) {
		try {
			this.reader = new FileReader(file);
			this.buffer = new BufferedReader(reader);
		}
		catch(FileNotFoundException e) {
			System.out.println("Text file not found.");
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	//make new dfa
	public DFA create() {
		DFA dfa = new DFA(1, null, null);
		try {
		//Adding alphabets
		int alphabet = (buffer.readLine().length()-1)/2;
		char[] line = buffer.readLine().toCharArray();
		//Adding states
		for(int i=1;i<line.length;i++) {
			this.states.add(line[i]);
		}
		//Add start state
		
		this.startState = buffer.readLine().toCharArray()[0];
		
		//Add accept states
		line = buffer.readLine().toCharArray();
		for(int i=1;i<line.length;i++) {
			this.acceptStates.add(line[i]);
		}
		//make array of states
		State[] state = new State[states.size()];
		int temp = 0;
		for(char s:states) {
			boolean accepted = false;
			
			for(char accept:acceptStates) {
				if(s==accept) {
					accepted = true;
			}
			}
			state[temp] = new State(alphabet,accepted,s);
			temp++;
			}
		//transition function
		while(buffer.ready()) {
			String currentLine;
			currentLine = buffer.readLine();
			char[] transition = currentLine.toCharArray();
			
			State start = this.findState(transition[1], state);
			State end = this.findState(transition[7], state);
			
			start.addTransition(Character.getNumericValue(transition[3]), end);
			
			//build the dfa
			dfa = new DFA(alphabet,state,this.findState(startState, state));
		}
	
		}
	
		catch(Exception e) {
			System.out.println("Error.");
			e.printStackTrace(System.out);
			System.exit(1);
		}
		return dfa;
		}
	public State findState(char id, State[] arr) {
		for(State s:arr) {
			if(s.id==id) {
				return s;
			}
		}
		return null;
	}
}


