
public class DFA {

	private State start;
	
public DFA(int alphabet, State[] states, State start) {

	this.start=start;
}
	public boolean checkString(String string) {
		char[] chars = string.toCharArray();
		State current = start;
		int next;
		
		for(int i=0; i < chars.length; i++) {
			next = Character.getNumericValue(chars[i]);
			current = current.getTransition(next);
		}
		
		if(current.accepted) {
			return true;
		}
		else {
			return false;
		}
	}
}
