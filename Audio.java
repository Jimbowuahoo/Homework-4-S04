package Shopping;

public class Audio extends Item{
	protected String artistName;
	public String getInfo()
	{
		String info = sNo + ",    " + name + ",    " + artistName + ", " + price + ", " + quantity;
		return info;
	}
	public void setParam(String info)
	{
		if (info!=null)
		{
			String data[] = info.split(",");
			sNo = Integer.parseInt(data[0].trim());
			name = data[1];
			price = Integer.parseInt(data[3].trim());
			quantity = Integer.parseInt(data[4].trim());
			artistName = data[2];
		}
	}
	public void changeQuantity(int mod)
	{
		quantity -= mod;
	}
	@Override
	public int getPrice()
	{
		return price;
	}
}
