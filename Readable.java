package Shopping;

public class Readable extends Item {
	protected String authorName;//name of author
	public String getInfo()
	{
		String info = sNo + ",    " + name + ",    " + authorName + ", " + price + ", " + quantity;//format info
		return info;//return info
	}
	public void setParam(String info)//set class variables
	{
		if (info!=null)
		{
			String data[] = info.split(",");//split info string into array by "," delimiter
			sNo = Integer.parseInt(data[0].trim());//set serial number
			name = data[1];//set name
			price = Integer.parseInt(data[3].trim());//set price
			quantity = Integer.parseInt(data[4].trim());//set quantity
			authorName = data[2];
		}
		
	}
	public void changeQuantity(int mod)
	{
		quantity -= mod;//subtract from stock
	}
	@Override
	public int getPrice()
	{
		return price;//get price
	}
}
