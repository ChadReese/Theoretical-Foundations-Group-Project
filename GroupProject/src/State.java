
public class State {
	int lang;
	State[] trans;
	char id;
	boolean accepted;
	
	public State(int alphabet, boolean accept, char id) {
		this.lang = alphabet;
		this.trans = new State[alphabet];
		this.id = id;
		this.accepted = accept;
	}
	
	public void addTransition(int index, State s) {
		this.trans[index] = s;	
	}
	
	public State getTransition(int index) {
		return this.trans[index];
	}

}
