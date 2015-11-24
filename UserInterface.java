package Shopping;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.*;

public class UserInterface {
	private String[] users = new String[30];
	private String[] Books = new String[30];
	private String[] EBooks = new String[30];
	private String[] CD = new String[30];
	private String[] MP3 = new String[30];
	private Book[] book = new Book[30];
	private eBook[] eBook = new eBook[30];
	private CD[] cd = new CD[30];
	private Book booki = new Book();
	private MP3[] mp3 = new MP3[30];
	private int currentPage;
	private User user = new User();
	private Scanner reader = new Scanner(System.in);
	private Item items[];
	private float subtotal = 0;
	private float eTax = 0;
	private float HST = 0;
	private float Shipping = 0;
	private ShoppingCart cart = new ShoppingCart();
	private String username;
	//need a function to load items into readables and audio
	
	public void readFiles(String filename, String[] array)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String CurrentLine;
			int i = 0;
			while ((CurrentLine = br.readLine()) != null) {
				array[i] = CurrentLine;
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void Initializer()
	{
		readFiles("User.txt", users);
		readFiles("Books.txt", Books);
		readFiles("Ebooks.txt", EBooks);
		readFiles("CD.txt", CD);
		readFiles("MP3.txt", MP3);
		for (int i = 0; i<Books.length; i++)
		{
			book[i] = new Book();
			book[i].setParam(Books[i]);
		}
		for (int i = 0; i < EBooks.length; i++)
		{
			eBook[i] = new eBook();
			eBook[i].setParam(EBooks[i]);
		}
		for (int i = 0; i < CD.length; i++)
		{
			cd[i] = new CD();
			cd[i].setParam(CD[i]);
		}
		for (int i = 0; i< MP3.length; i++)
		{
			mp3[i] = new MP3();
			mp3[i].setParam(MP3[i]);
		}
	}
	
	public void logIn()
	{
		System.out.print("Hello, welcome. \nWould you like to sign in or sign up?\n1. Sign In\n2. Sign Up \nEnter Command:");
		int userInput = reader.nextInt();
		if (userInput == 1)
		{
			System.out.print("Enter your username: ");
			username = user.getUsername(reader);
			if(Arrays.asList(users).contains(username))
			{
				System.out.print("Hello, " + username + "\n");
				mainMenu();
			}
			else
			{
				System.out.print("Username not found, no access\n");
				logIn();
			}
		}
		else if (userInput == 2)
		{
			System.out.print("Please enter a username: ");
			String newUser = reader.next();
			try {
				File file = new File("User.txt");
				
				if(!file.exists()){
	    			file.createNewFile();
	    		}
				
				FileWriter fileWriter = new FileWriter("User.txt", true);
				BufferedWriter bw = new BufferedWriter(fileWriter);
				bw.write("\n" + newUser);
				bw.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			readFiles("Users.txt", users);
			logIn();
		}
		else
		{
			System.out.print("Not a valid command.\n");
			logIn();
		}
	}
	
	public void mainMenu()
	{
		System.out.print("1.View items by category\n2. View shopping cart\n3. Sign out \nEnter command: ");
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
		System.out.print("1.Readables\n2. audio\n-1 to go to previous menu\nEnter Command:");
		int userInput = reader.nextInt();
		if(userInput == 1)
		{
			showReadables();
		}
		else if (userInput == 2)
		{
			showAudioProducts();
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
		System.out.print("\nEnter Command: ");
		System.out.print("\nPress -1 to go back");
		int userInput = reader.nextInt();
		
		
		for(int i = 0; i<items.length;i++)
		{
			if (userInput == book[i].sNo)
			{
				System.out.print("Enter the amount: ");
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded < book[i].quantity)
				{
					System.out.print(book[i].name +" added to shopping cart\n");
					book[i].changeQuantity(amountAdded);
					cart.addItem();
					//add to shopping cart function
					subtotal += amountAdded*book[i].getPrice();
					eTax += amountAdded*book[i].getPrice()*0.02;
					Shipping += amountAdded*book[i].getPrice()*0.1;
					HST += amountAdded*book[i].getPrice()*0.13;
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
					cart.addItem();
					//add to shopping cart function
					subtotal += amountAdded*book[i].getPrice();
					HST += amountAdded*book[i].getPrice()*0.13;
				}
				else
				{
					System.out.print("Invalid Number, try again.");
				}
				showReadables();
			}
			else if (userInput == 0)
			{
				checkOut();
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
	public void getAudio()
	{
		System.out.print("\nEnter Command: ");
		System.out.print("\nPress -1 to go back");
		int userInput = reader.nextInt();
		
		
		for(int i = 0; i<cd.length;i++)
		{
			if (userInput == cd[i].sNo)
			{
				System.out.print("Enter the amount: ");
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded <cd[i].quantity)
				{
					System.out.print(cd[i].name +" added to shopping cart\n");
					cd[i].changeQuantity(amountAdded);
					cart.addItem();
					//add to shopping cart function
					subtotal += amountAdded*book[i].getPrice();
					eTax += amountAdded*book[i].getPrice()*0.02;
					Shipping += amountAdded*book[i].getPrice()*0.1;
					HST += amountAdded*book[i].getPrice()*0.13;
				}
				else
				{
					System.out.print("\nInvalid Number, try again.");
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
					cart.addItem();
					//add to shopping cart function
					subtotal += amountAdded*book[i].getPrice();
					HST += amountAdded*book[i].getPrice()*0.13;
				}
				else
				{
					System.out.print("\nInvalid Number, try again.");
				}
				showAudioProducts();
			}
			else if (userInput == 0)
			{
				checkOut();
			}
			else if (userInput == -1)
			{
				categoryMenu();
			}
			else
			{
				System.out.print("\nUnsupported input");
				showAudioProducts();
			}
		}
	}
	public void showReadables()
	{
		System.out.print("S.No   Name of Book       Author         Price($)       Quantity in Store    Type\n");
		for(int i = 0; i < book.length; i++)
		{
			if (book[i].sNo != 0)
			{
				System.out.print(book[i].getInfo() + "       Book\n");
			}
		}
		for(int i = 0; i< eBook.length; i++)
		{
			if (eBook[i].sNo != 0)
			{
				System.out.print(eBook[i].getInfo() + "       eBook\n");
			}
		}
		getReadables();
		
	}
	public void showAudioProducts()
	{
		System.out.print("S.No   Name          Artist   Price($)  Quantity  Type\n");
		for(int i = 0; i< cd.length; i++)
		{
			if (cd[i].sNo != 0)
			{
				System.out.print(cd[i].getInfo() + "       CD\n");
			}
		}
		for(int i = 0; i< mp3.length; i++)
		{
			if (mp3[i].sNo != 0)
			{
				System.out.print(mp3[i].getInfo() + "       MP3\n");
			}
		}
		getAudio();
	}
	
	public void checkOut()
	{
		
	}
	
	
}
