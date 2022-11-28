package dealershipCRUD;

import javafx.beans.property.*;

public class Dealership{
	private StringProperty id;
	private IntegerProperty mileage;
	private IntegerProperty mpg;
	private DoubleProperty cost;
	private DoubleProperty salesPrice;
	private BooleanProperty sold;
	private DoubleProperty profit;
	
	public Dealership(){
		
	}
	
	public Dealership(String id, int mileage, int mpg, double cost, double salesPrice, boolean sold, double profit) {
		this.id = new SimpleStringProperty(id);
		this.mileage = new SimpleIntegerProperty(mileage);
		this.mpg = new SimpleIntegerProperty(mpg);
		this.cost = new SimpleDoubleProperty(cost); 
		this.salesPrice = new SimpleDoubleProperty(salesPrice); 
		this.sold = new SimpleBooleanProperty(sold);
		this.profit = new SimpleDoubleProperty(profit);
		
	}

	public String getId() {
		return id.get();
	}

	public void setId(String i) {
		id.set(i);
	}

	public int getMileage() {
		return mileage.get();
	}

	public void setMileage(int m) {
		mileage.set(m);
	}

	public int getMpg() {
		return mpg.get();
	}

	public void setMpg(int g) {
		mpg.set(g);
	}

	public double getCost() {
		return cost.get();
	}

	public void setCost(double c) {
		cost.set(c);
	}

	public double getSalesPrice() {
		return salesPrice.get();
	}

	public void setSalesPrice(double s) {
		salesPrice.set(s);
	}

	public Boolean getSold() {
		return sold.get();
	}

	public void setSold(boolean d) {
		sold.set(d);
	}

	public double getProfit() {
		return profit.get();
	}

	public void setProfit(double p) {
		profit.set(p);
	}
	
	
	
	
	
	
	
	
}