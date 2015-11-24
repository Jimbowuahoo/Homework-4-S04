package Shopping;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class UserInterface {
	private String[] users = new String[30];//stores the lines of user.txt
	private String[] Books = new String[30];//stores the lines of books.txt
	private String[] EBooks = new String[30];//stores the lines of ebooks.txt
	private String[] CD = new String[30];//stores the lines of cd.txt
	private String[] MP3 = new String[30];//stores the lines of mp3.txt
	private Book[] book = new Book[30];//array of books
	private eBook[] eBook = new eBook[30];//array of ebooks
	private CD[] cd = new CD[30];//array of cd
	private MP3[] mp3 = new MP3[30];//array of mp3
	private User user = new User(); //user as an object
	private Scanner reader = new Scanner(System.in); //for user inputs
	private float subtotal = 0; //subtotal
	private float eTax = 0; //environment tax
	private float HST = 0; //hst
	private float Shipping = 0; //shipping fee
	private ShoppingCart cart = new ShoppingCart(); //shopping cart as an object
	private String username; //name of user
	private int ID = 0;
	
	//function reads files and puts into an array
	public void readFiles(String filename, String[] array)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String CurrentLine;//current line
			int i = 0;
			while ((CurrentLine = br.readLine()) != null) {
				array[i] = CurrentLine;//put current line into the array
				i++;//increment
			}

		} catch (IOException e) {
			e.printStackTrace(); //catch exceptions
		} 
	}
	
	//initalize everything
	public void Initializer()
	{
		//reads each .txt and places the lines into arrays
		readFiles("User.txt", users);
		readFiles("Books.txt", Books);
		readFiles("Ebooks.txt", EBooks);
		readFiles("CD.txt", CD);
		readFiles("MP3.txt", MP3);
		
		//set the values associated with each item
		for (int i = 0; i<Books.length; i++)
		{
			book[i] = new Book();//initialize book to not be null
			book[i].setParam(Books[i]);//set parameters
		}
		for (int i = 0; i < EBooks.length; i++)
		{
			eBook[i] = new eBook();//initialize ebook to not be null
			eBook[i].setParam(EBooks[i]);//set parameters
		}
		for (int i = 0; i < CD.length; i++)
		{
			cd[i] = new CD();//init cd to not be null
			cd[i].setParam(CD[i]);//set parameters
		}
		for (int i = 0; i< MP3.length; i++)
		{
			mp3[i] = new MP3();//init mp3 to not be null
			mp3[i].setParam(MP3[i]);//set parameters
		}
	}
	
	//login menu
	public void logIn()
	{
		//menu display
		System.out.print("Hello, welcome. \nWould you like to sign in or sign up?\n1. Sign In\n2. Sign Up \nEnter Command:");
		//get user input
		int userInput = reader.nextInt();
		
		//process user input
		if (userInput == 1)
		{
			System.out.print("Enter your username: ");
			username = user.getUsername(reader);//get username
			if(Arrays.asList(users).contains(username))//check if the array holding all the usernames have the user in it
			{
				System.out.print("Hello, " + username + "\n");// confirms it
				mainMenu();//go to main menu
			}
			else
			{
				System.out.print("Username not found, no access\n"); //no username found
				logIn();
			}
		}
		else if (userInput == 2)
		{
			System.out.print("Please enter a username: ");// register new user
			String newUser = reader.next();//gets a username
			try {
				File file = new File("User.txt");//open user.txt
				
				if(!file.exists())//in case it doesn't exist
				{
	    			file.createNewFile();
	    		}
				
				FileWriter fileWriter = new FileWriter("User.txt", true); //append
				BufferedWriter bw = new BufferedWriter(fileWriter);// declare buffer writer
				bw.write("\n" + newUser);//add the new user
				bw.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			readFiles("Users.txt", users);//read the new user file
			System.out.print("User registered.\n");//confirmation
			
			logIn();// go to login
		}
		else
		{
			System.out.print("Not a valid command.\n");//invalid command
			logIn();//go to login
		}
	}
	
	//main menu
	public void mainMenu()
	{
		System.out.print("1.View items by category\n2. View shopping cart\n3. Sign out \nEnter command: ");//display menu
		int userInput = reader.nextInt();//get user input
		//process user input
		if (userInput == 1)
		{
			categoryMenu();//go to category
		}
		else if (userInput == 2)
		{
			System.out.print("\n" + cart.getContent());//displays content of cart
			System.out.print("Press anything and enter to exit\nCommand:");//display menu
			String Input = reader.next();//get any input
			//go back to main menu no matter what
			if (Input == "-1")
			{
				mainMenu();
			}
			else
			{
				mainMenu();
			}
			
		}
		else if (userInput == 3)
		{
			logIn();//go back to login
		}
		else
		{
			mainMenu();//go to main menu
		}
	}
	
	//function for category menu
	public void categoryMenu()
	{
		System.out.print("1.Readables\n2. audio\n-1 to go to previous menu\nEnter Command:");//dislay menu
		int userInput = reader.nextInt();//get input
		//process input
		if(userInput == 1)
		{
			showReadables(); //readables menu
		}
		else if (userInput == 2)
		{
			showAudioProducts(); // audio menu
		}
		else if (userInput == -1)
		{
			mainMenu(); // main menu
		}
		else
		{
			System.out.print("Unsupported input");//tell user he/she has entered wrong value
			categoryMenu(); //category menu
		}
	}
	
	//function for adding readables to file
	public void getReadables()
	{
		System.out.print("\nEnter Command: ");//ask for command
		System.out.print("\nPress -1 to go back");
		int userInput = reader.nextInt();//input
		
		if (userInput == 0)
		{
			checkOut();//checkout
		}
		
		for(int i = 0; i<book.length;i++)//loop to check serial numbers
		{
			if (userInput == book[i].sNo)//check book sNo
			{
				System.out.print("Enter the amount: ");//amount user needs
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded <= book[i].quantity)//if amount is reasonable
				{
					System.out.print(book[i].name +" added to shopping cart\n");//print message
					book[i].changeQuantity(amountAdded);//change quantity of books
					cart.addItem(username, book[i].sNo, book[i].name, amountAdded);//add item(s) to cart
					cart.addContent(username, book[i].sNo, book[i].name, amountAdded);
					//increase subtotals and taxes
					subtotal += amountAdded*book[i].getPrice();
					eTax += amountAdded*book[i].getPrice()*0.02;
					Shipping += amountAdded*book[i].getPrice()*0.1;
					HST += amountAdded*book[i].getPrice()*0.13;
				}
				else
				{
					System.out.print("Invalid Number, try again.");//wrong amount
				}
				getReadables();//back to readables menu
			}
			if (userInput == eBook[i].sNo)
			{
				System.out.print("Enter the amount: ");//ask for amount of ebooks
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded <= eBook[i].quantity)//check if amount is reasonable
				{
					System.out.print(eBook[i].name +" added to shopping cart");
					eBook[i].changeQuantity(amountAdded);//change quantity of items held
					cart.addItem(username, eBook[i].sNo, eBook[i].name, amountAdded);//add items to cart
					cart.addContent(username, eBook[i].sNo, eBook[i].name, amountAdded);
					//increment subtotal and taxes
					subtotal += amountAdded*book[i].getPrice();
					HST += amountAdded*book[i].getPrice()*0.13;
				}
				else
				{
					System.out.print("Invalid Number, try again.");//invalid number
				}
				getReadables();//back to menu
			}
		}
		if (userInput == -1)
		{
			categoryMenu();//go to category menu
		}
		else
		{
			System.out.print("Unsupported input");//unsupported input
			getReadables();//back to menu
		}
		
	}
	public void getAudio()
	{
		System.out.print("\nEnter Command: ");//ask for which product
		System.out.print("\nPress -1 to go back");
		int userInput = reader.nextInt();//gets input
		//process serial numbers
		if (userInput == 0)
		{
			checkOut();//checkout
		}
		
		for(int i = 0; i<cd.length;i++)
		{
			if (userInput == cd[i].sNo)//for cd
			{
				System.out.print("Enter the amount: ");//asks for amount bought
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded <= cd[i].quantity)//checks for valid quantity
				{
					System.out.print(cd[i].name +" added to shopping cart\n");//message
					cd[i].changeQuantity(amountAdded);//subtract from total
					cart.addItem(username, cd[i].sNo, cd[i].name, amountAdded);//add item to cart
					cart.addContent(username, cd[i].sNo, cd[i].name, amountAdded);
					//add subtotal and taxes
					subtotal += amountAdded*book[i].getPrice();
					eTax += amountAdded*book[i].getPrice()*0.02;
					Shipping += amountAdded*book[i].getPrice()*0.1;
					HST += amountAdded*book[i].getPrice()*0.13;
				}
				else
				{
					//invalid number
					System.out.print("\nInvalid Number, try again.");
				}
				getAudio();//back to menu
			}
			if (userInput == mp3[i].sNo)//checks mp3 sNo
			{
				System.out.print("Enter the amount: ");//ask for amount
				int amountAdded = reader.nextInt();
				if (amountAdded > 0 && amountAdded <= mp3[i].quantity)//checks if valid quantity
				{
					System.out.print(mp3[i].name +" added to shopping cart");//message
					mp3[i].changeQuantity(amountAdded);//sub from total
					cart.addItem(username, mp3[i].sNo, mp3[i].name, amountAdded);//add to cart
					cart.addContent(username, mp3[i].sNo, mp3[i].name, amountAdded);
					//add subtotal and tax
					subtotal += amountAdded*book[i].getPrice();
					HST += amountAdded*book[i].getPrice()*0.13;
				}
				else
				{
					System.out.print("\nInvalid Number, try again.");//invalid number
				}
				getAudio();//back to menu
			}
		}
		
		if (userInput == -1)
		{
			categoryMenu();//previous menu
		}
		else
		{
			System.out.print("\nUnsupported input");//unsupported input
			showAudioProducts();//back to menu
		}
		
	}
	//readables menu
	public void showReadables()
	{
		System.out.print("S.No   Name of Book       Author         Price($)       Quantity in Store    Type\n");//format
		for(int i = 0; i < book.length; i++)//goes through array
		{
			if (book[i].sNo != 0)//if blank, don't display
			{
				System.out.print(book[i].getInfo() + "       Book\n");//display books 
			}
		}
		for(int i = 0; i< eBook.length; i++)//goes through array
		{
			if (eBook[i].sNo != 0)//if blank, don't display
			{
				System.out.print(eBook[i].getInfo() + "       eBook\n");//displays ebooks
			}
		}
		getReadables();//go to purchase function
		
	}
	
	//show audio menu
	public void showAudioProducts()
	{
		System.out.print("S.No   Name          Artist   Price($)  Quantity  Type\n");
		for(int i = 0; i< cd.length; i++)//goes through array
		{
			if (cd[i].sNo != 0)//if blank, don't display
			{
				System.out.print(cd[i].getInfo() + "       CD\n");//display cd
			}
		}
		for(int i = 0; i< mp3.length; i++)//goes through array
		{
			if (mp3[i].sNo != 0)//if blank, don't display
			{
				System.out.print(mp3[i].getInfo() + "       MP3\n");//display mp3
			}
		}
		getAudio();
	}
	
	public void updateID()
	{
		try {
			File file = new File("ConID.txt"); 
			//Check to see if cart file for user already exists
			if(!file.exists()){
				file.createNewFile(); //if no cart exists for user, create one
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader("ConID.txt")))
		{
			String CurrentLine;//current line
			while ((CurrentLine = br.readLine()) != null) {
				ID = Integer.parseInt(CurrentLine.trim());//put current line into the array
			}

		} catch (IOException e) {
			e.printStackTrace(); //catch exceptions
		}
		ID++;
		try {
			File file = new File("ConID.txt"); 
			//Check to see if cart file for user already exists
			if(!file.exists()){
				file.createNewFile(); //if no cart exists for user, create one
			}
			
			//Write product info to cart file for user
			FileWriter fileWriter = new FileWriter("ConID.txt");
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(ID ); //Populate cart
			bw.close();
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//checkout function
	public void checkOut()
	{
		System.out.print("Billing Information:\n  Name \t\t\tQuantity\t\tPrice");
		for(int i = 0; i < 100; i++)
		{
			if (cart.getName(i) != null)
			{
				System.out.print("\n" + cart.getName(i) +"\t\t\t" + cart.getQuantity(i));
			}
		}
		System.out.print("\n  Subtotal \t\t \t\t" + subtotal);
		System.out.print("\nEvironment Tax \t\t 2%\t\t" + eTax);
		System.out.print("\n  HST \t\t 13% \t\t" + HST);
		System.out.print("\nShipping \t\t 10% \t\t" + Shipping);
		System.out.print("\n\t\t \t\t _________");
		System.out.print("\nTotal \t\t\t\t" + (subtotal+eTax+Shipping+HST));
		
		System.out.print("\nDo you want to pay? Yes or No: ");
		String input = reader.next();
		if (input.equals("Yes")||input.equals("yes")||input.equals("yEs")||input.equals("YEs")||input.equals("YES")||input.equals("YeS")||input.equals("yES")||input.equals("yeS"))
		{
			System.out.print("\nConfirmation ID: U100" + ID );
			System.out.print("\nShipping to: " + username);
			cart.clearCart(username);
			//updateID();
			mainMenu();
		}
		else if (input.equals("No")||input.equals("no")||input.equals("nO")||input.equals("NO"))
		{
			categoryMenu();
		}
		else
		{
			System.out.print("\nInvalid");
			checkOut();
		}
	}
	
	
}
