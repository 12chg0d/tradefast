package bean;

import java.util.ArrayList;

public class CreateOrderBean {
	private int sum_point;
	private int sum_money;
	private int item_amount;
	private ArrayList<String> forDelete;
	public int getSum_point() {
		return sum_point;
	}
	public void setSum_point(int sum_point) {
		this.sum_point = sum_point;
	}
	public int getItem_amount() {
		return item_amount;
	}
	public void setItem_amount(int item_amount) {
		this.item_amount = item_amount;
	}
	public int getSum_money() {
		return sum_money;
	}
	public void setSum_money(int sum_money) {
		this.sum_money = sum_money;
	}
	public ArrayList<String> getForDelete() {
		return forDelete;
	}
	public void setForDelete(ArrayList<String> forDelete) {
		this.forDelete = forDelete;
	}
}
