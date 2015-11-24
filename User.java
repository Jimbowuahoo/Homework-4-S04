package Shopping;
import java.util.Scanner;

public class User {
	private String username;
	private String password;
	public String getUsername(Scanner reader){
		username = reader.next();
		return username;
	}
	/*public String getPassword()
	{
		if (getUsername(reader) == "ADMIN")
		{
			Scanner reader = new Scanner(System.in);
			System.out.print("Password: ");
			password = reader.next();
			reader.close();
		}
		return password;
	}*/
}
