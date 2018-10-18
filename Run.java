public class Run {


	/**
	* instatiate a run class
	* we only need the first and the last indices for the run objects
	* @param null
	*
	*
	*/
	
	private int first_Index; 
	private int last_Index; 

	public Run(int first, int last) {
		this.first_Index = first; 
		this.last_Index = last; 
	}

	public int getFirst() {
		return first_Index; 
	}

	public int getLast() {
		return last_Index; 
	}
}
