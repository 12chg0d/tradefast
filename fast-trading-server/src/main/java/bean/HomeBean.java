package bean;

import java.util.ArrayList;

public class HomeBean {
	private ArrayList<String[]> arrForNew;
	private ArrayList<String[]> arrForPop;
	private int currentPage;
	public ArrayList<String[]> getArrForNew() {
		return arrForNew;
	}
	public void setArrForNew(ArrayList<String[]> arrForNew) {
		this.arrForNew = arrForNew;
	}
	public ArrayList<String[]> getArrForPop() {
		return arrForPop;
	}
	public void setArrForPop(ArrayList<String[]> arrForPop) {
		this.arrForPop = arrForPop;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
