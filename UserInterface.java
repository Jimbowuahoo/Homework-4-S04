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
	private String users[];
	private String Books[];
	private String EBooks[];
	private String CD[];
	private String MP3[];
	private Book book[];
	private eBook eBook[];
	private CD cd[];
	private MP3 mp3[];
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
		readFiles("Ebooks.txt", EBooks);
		readFiles("CD.txt", CD);
		readFiles("MP3.txt", MP3);
		for (int i = 0; i<Books.length; i++)
		{
			book[i].setParam(Books[i]);
		}
		for (int i = 0; i < EBooks.length; i++)
		{
			eBook[i].setParam(EBooks[i]);
		}
		for (int i = 0; i < CD.length; i++)
		{
			cd[i].setParam(CD[i]);
		}
		for (int i = 0; i< MP3.length; i++)
		{
			mp3[i].setParam(MP3[i]);
		}
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
		for(int i = 0; i < book.length; i++)
		{
			System.out.print(book[i].getInfo() + "       Book");
		}
		for(int i = 0; i< eBook.length; i++)
		{
			System.out.print(eBook[i].getInfo() + "      eBook");
		}
		System.out.print("Choose your option: ");
		int userInput = reader.nextInt();
		System.out.print("Press -1 to go back");
		
		for(int i = 0; i<items.length;i++)
		{
			if (userInput == book[i].sNo)
			{
				System.out.print("Enter the amount: ");
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded < book[i].quantity)
				{
					System.out.print(book[i].name +" added to shopping cart");
					book[i].changeQuantity(amountAdded);
					//add to shopping cart function
				}
				else
				{
					System.out.print("Invalid Number, try again.");
				}
				showReadables();
			}
			else if (userInput == eBook[i].sNo)
			{
				System.out.print("Enter the amount: ");
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded < eBook[i].quantity)
				{
					System.out.print(eBook[i].name +" added to shopping cart");
					eBook[i].changeQuantity(amountAdded);
					//add to shopping cart function
				}
				else
				{
					System.out.print("Invalid Number, try again.");
				}
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
		for(int i = 0; i < cd.length; i++)
		{
			System.out.print(cd[i].getInfo() + "       CD");
		}
		for(int i = 0; i< mp3.length; i++)
		{
			System.out.print(mp3[i].getInfo() + "       MP3");
		}
		System.out.print("Choose your option: ");
		int userInput = reader.nextInt();
		System.out.print("Press -1 to go back");
		
		for(int i = 0; i<cd.length;i++)
		{
			if (userInput == cd[i].sNo)
			{
				System.out.print("Enter the amount: ");
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded <cd[i].quantity)
				{
					System.out.print(cd[i].name +" added to shopping cart");
					cd[i].changeQuantity(amountAdded);
					//add to shopping cart function
				}
				else
				{
					System.out.print("Invalid Number, try again.");
				}
				showAudioProducts();
			}
			else if (userInput == mp3[i].sNo)
			{
				System.out.print("Enter the amount: ");
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded < mp3[i].quantity)
				{
					System.out.print(mp3[i].name +" added to shopping cart");
					mp3[i].changeQuantity(amountAdded);
					//add to shopping cart function
				}
				else
				{
					System.out.print("Invalid Number, try again.");
				}
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
