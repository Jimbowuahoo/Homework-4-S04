package Shopping;

public class Audio extends Item{
	protected String artistName;
	public String getInfo()
	{
		String info = sNo + ",    " + name + ",    " + artistName + ", " + price + ", " + quantity;//formats info
		return info;//returns info
	}
	public void setParam(String info)//splits info into class variables
	{
		if (info!=null)
		{
			String data[] = info.split(",");//split info string into array by "," delimiter
			sNo = Integer.parseInt(data[0].trim());//set serial number
			name = data[1];//set name
			price = Integer.parseInt(data[3].trim());//set price
			quantity = Integer.parseInt(data[4].trim());//set quantity
			artistName = data[2];//set artist name
		}
	}
	public void changeQuantity(int mod)
	{
		quantity -= mod;//subtract from quantity
	}
	@Override
	public int getPrice()
	{
		return price;//return price
	}
}
