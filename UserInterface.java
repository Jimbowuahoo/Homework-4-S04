package Shopping;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.FileReader;
import java.io.BufferedReader;

public class UserInterface {
	private String readables[];
	private String audioProducts[];
	private String users[];
	private String Books[];
	private String eBooks[];
	private String CD[];
	private String MP3[];
	private int currentPage;
	private User user;
	private Scanner reader = new Scanner(System.in);
	private Item items[];
	//need a function to load items into readables and audio
	
	public void readFiles(String filename, String array[])
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String CurrentLine;
			while ((CurrentLine = br.readLine()) != null) {
				int i = 0;
				array[i] = CurrentLine;
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void Initializer()
	{
		readFiles("Users.txt", users);
		readFiles("Books.txt", Books);
		readFiles("Ebooks.txt", eBooks);
		readFiles("CD.txt", CD);
		readFiles("MP3.txt", MP3);
	}
	
	public void logIn()
	{
		System.out.print("Hello, welcome. \nWould you like to sign in or sign up?\n1. Sign In\n2. Sign Up");
		int userInput = reader.nextInt();
		if (userInput == 1)
		{
			System.out.print("Enter your username: ");
			String username = user.getUsername();
			if(Arrays.asList(users).contains(username) == true)
			{
				System.out.print("Hello, " + username);
				mainMenu();
			}
			else
			{
				System.out.print("Username not found, no access");
				logIn();
			}
		}
		else if (userInput == 2)
		{
			System.out.print("Please enter a username: ");
			String newUser = reader.next();
			try {
			    Files.write(Paths.get("Users.txt"), newUser.getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
			    //exception
			}
			readFiles("Users.txt", users);
			logIn();
		}
		else
		{
			System.out.print("Not a valid command.");
			logIn();
		}
	}
	
	public void mainMenu()
	{
		System.out.print("1.View items by category\n2. View shopping cart\n3. Sign out");
		int userInput = reader.nextInt();
		if (userInput == 1)
		{
			categoryMenu();
		}
		else if (userInput == 2)
		{
			//shopping cart function
		}
		else if (userInput == 3)
		{
			logIn();
		}
		else
		{
			mainMenu();
		}
	}
	
	public void categoryMenu()
	{
		System.out.print("1.Readables\n2. audio\n-1 to go to previous menu");
		int userInput = reader.nextInt();
		if(userInput == 1)
		{
			getReadables();
		}
		else if (userInput == 2)
		{
			getAudio();
		}
		else if (userInput == -1)
		{
			mainMenu();
		}
		else
		{
			System.out.print("Unsupported input");
			categoryMenu();
		}
	}
	
	public int getCurrentPage()
	{
		return currentPage;
	}
	public int changeCurrentPage()
	{
		return currentPage;
	}
	public void getReadables()
	{
		
	}
	public void getAudio()
	{
		
	}
	public void showReadables()
	{
		System.out.print("S.No   Name of Book       Author         Price($)       Quantity in Store    Type");
		for(int i = 0; i < Books.length; i++)
		{
			System.out.print(Books[i]);
		}
		for(int i = 0; i< eBooks.length; i++)
		{
			System.out.print(eBooks[i]);
		}
		System.out.print("Choose your option: ");
		int userInput = reader.nextInt();
		System.out.print("Press -1 to go back");
		
		for(int i = 0; i<items.length;i++)
		{
			if (userInput == items[i].sNo)
			{
				System.out.print("Item added to shopping cart");
				//add to shopping cart function
				showReadables();
			}
			else if (userInput == -1)
			{
				categoryMenu();
			}
			else
			{
				System.out.print("Unsupported input");
				showReadables();// might need another function for this whole part for ease of use
			}
		}
	}
	public void showAudioProducts()
	{
		System.out.print("S.No   Name               Artist         Price($)       Quantity in Store    Type");
		for(int i = 0; i < CD.length; i++)
		{
			System.out.print(CD[i]);
		}
		for(int i = 0; i< MP3.length; i++)
		{
			System.out.print(MP3[i]);
		}
		System.out.print("Choose your option: ");
		int userInput = reader.nextInt();
		System.out.print("Press -1 to go back");
		
		for(int i = 0; i<items.length;i++)
		{
			if (userInput == items[i].sNo)
			{
				System.out.print("Item added to shopping cart");
				//add to shopping cart function
				showAudioProducts();
			}
			else if (userInput == -1)
			{
				categoryMenu();
			}
			else
			{
				System.out.print("Unsupported input");
				showAudioProducts();// might need another function for this whole part for ease of use
			}
		}
	}
}
