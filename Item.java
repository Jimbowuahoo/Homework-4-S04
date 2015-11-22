package Shopping;
public abstract class Item {
	public abstract String getInfo();
	public abstract int getPrice();
	public abstract void setParam(String info);
	public abstract void changeQuantity(int mod);
	
	protected int price;
	protected int sNo;
	protected String name;
	protected int quantity;
}

