package Shopping;
public abstract class Item {
	public abstract String getInfo();//get item info
	public abstract int getPrice();//get item price
	public abstract void setParam(String info); // set item values
	public abstract void changeQuantity(int mod); //subtract from stock
	
	protected int price;//price
	protected int sNo;//serial number
	protected String name;//name
	protected int quantity;//quantity
}

