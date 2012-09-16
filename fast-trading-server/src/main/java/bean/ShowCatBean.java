package bean;

import java.util.ArrayList;

/**
 *This bean is created for throwing the data about the user which can be used in many classes.
 */
public class ShowCatBean {
 	private ArrayList<String[]> arr;
	//Getter and Setter for each field.
	public ArrayList<String[]> getArr() {
		return arr;
	}

	public void setArr(ArrayList<String[]> arr) {
		this.arr = arr;
	}
}
