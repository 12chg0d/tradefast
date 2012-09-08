package bean;
/**
 *This bean is created for throwing the data about the user which can be used in many classes.
 */
public class UserBean {
 	private String username;//Username.
	private String[] topTable;//The top table of index file. 
	private String[] indexing;//The indices which you select.
	private int numTrainRows;
	private int numRows;
	//Getter and Setter for each field.
	public void setTopTable(String[] topTable) {
		this.topTable = topTable;
	}
	public String[] getTopTable() {
		return topTable;
	}
	public void setIndexing(String[] indexing) {
		this.indexing = indexing;
	}
	public String[] getIndexing() {
		return indexing;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setNumTrainRows(int numTrainRows) {
		this.numTrainRows = numTrainRows;
	}
	public int getNumTrainRows() {
		return numTrainRows;
	}
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	public int getNumRows() {
		return numRows;
	}
	
}
