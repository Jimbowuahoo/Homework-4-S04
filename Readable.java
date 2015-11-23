package Shopping;

import java.util.Arrays;
import java.util.List;

public class Readable extends Item {
	protected String authorName;
	public String getInfo()
	{
		String info = sNo + ",    " + name + ",    " + authorName + ", " + price + ", " + quantity;
		return info;
	}
	public void setParam(String info)
	{
		if (info!=null)
		{
			String data[] = new String[5];
			data = info.split(",");
			sNo = Integer.parseInt(data[0].trim());
			name = data[1];
			price = Integer.parseInt(data[3].trim());
			quantity = Integer.parseInt(data[4].trim());
			authorName = data[2];
		}
		
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
