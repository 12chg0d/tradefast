package bean;

public class ItemBean {
	private String Iname;
	private String description;
	private String price_money_only;
	private String price_point_only;
	private String price_point_couple;
	private String price_money_couple;
	private String cat;
	private String picture;
	public String getIname() {
		return Iname;
	}
	public void setIname(String iname) {
		Iname = iname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPrice_money_only() {
		return price_money_only;
	}
	public void setPrice_money_only(String price_money_only) {
		this.price_money_only = price_money_only;
	}
	public String getPrice_point_only() {
		return price_point_only;
	}
	public void setPrice_point_only(String price_point_only) {
		this.price_point_only = price_point_only;
	}
	public String getPrice_point_couple() {
		return price_point_couple;
	}
	public void setPrice_point_couple(String price_point_couple) {
		this.price_point_couple = price_point_couple;
	}
	public String getPrice_money_couple() {
		return price_money_couple;
	}
	public void setPrice_money_couple(String price_money_couple) {
		this.price_money_couple = price_money_couple;
	}
}
