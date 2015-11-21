package Shopping;
import java.util.Scanner;

public class User {
	private String username;
	private String password;
	public String getUsername(){
		Scanner reader = new Scanner(System.in);
		System.out.print("Username: ");
		username = reader.next();
		reader.close();
		return username;
	}
	public String getPassword()
	{
		if (getUsername() == "ADMIN")
		{
			Scanner reader = new Scanner(System.in);
			System.out.print("Password: ");
			password = reader.next();
			reader.close();
		}
		return password;
	}
}
