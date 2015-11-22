package Shopping;
public class Readable extends Item {
	protected String authorName;
	public String getInfo()
	{
		String info = sNo + ", " + name + ", " + authorName + ", " + price + ", " + quantity;
		return info;
	}
	public void setParam(String info)
	{
		String data[] = info.split(",");
		sNo = Integer.parseInt(data[0]);
		name = data[1];
		price = Integer.parseInt(data[3]);
		quantity = Integer.parseInt(data[4]);
		authorName = data[2];
	}
	public void changeQuantity(int mod)
	{
		quantity -= mod;
	}
	@Override
	public int getPrice()
	{
		return 0;
	}
}
