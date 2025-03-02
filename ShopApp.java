/***
 * Student Name: Laraib Saeed
 * Student ID: 21145515
 * Course Code: COMP503
 * Assessment Item: Individual Programming Assignment Part A [Online Shop Rating Application]
 ***/
package assignment;

import java.util.Scanner;

public class ShopApp 
{
	public static void main(String[] args) 
	{
		Scanner scan=new Scanner(System.in);
		
		OnlineShop onlineShop=new OnlineShop();
		
		//Adds 5 Products to online shop
		onlineShop.add(new Product("ABC45","Clothes&co",15.50));
		onlineShop.add(new Product("TFG74","LivingDecor",24.45));
		onlineShop.add(new Product("SRE23","MusicBeats"));
		onlineShop.add(new Product("MNL31","BeautyShades"));
		onlineShop.add(new Product("OLP98","Techlights",32.99));
		
		//Sets prices for product 3 and 4 that were created with no retail price.
		onlineShop.getProduct(3).setRetailprice(23.40);
		onlineShop.getProduct(4).setRetailprice(25.00);
		
		//Prints out Display Menu
		String menu="Please enter an option:\n\n"
				  +"A. Display Inventory\n"
		          +"B. Rate Product\n"
		          +"C. Exit";
		
		System.out.println(menu);
		
		//Scans users input
		char input= scan.next().charAt(0);
		
//-------------------//OPTIONS//-------------------------------//
		while(true)
		{
					//OPTION A//
			if(input=='A'|| input=='a')
			{
				//Prints out menu
				System.out.println(onlineShop);
				
				//Menu for next option
				System.out.println(menu);
				input= scan.next().charAt(0);
			}
					//OPTION B//
			else if(input=='B' || input=='b')
			{
				//Prints out menu
				System.out.println(onlineShop);
				
				//Prints and scans item number 
				System.out.println("Please enter a number from 1-5:");
				int numberInput= scan.nextInt();
				
				//FOR INVALID OUTPUT
				while(numberInput<=0 || numberInput>5)
				{
					System.out.println("This is an invalid number.");
					System.out.println("Please enter a number from 1-5:");
					numberInput= scan.nextInt();
				}
				
				//Save product from online shop into product details using number input
				Product productDetails=onlineShop.getProduct(numberInput);
				System.out.println(+numberInput+"."+productDetails+"\n");
				
				//Prints and scans rating number
				System.out.println("Please enter a rating from 0.0 to 5.0:");
				double numberRating=scan.nextDouble();
				
				while(numberRating<=0.0 || numberRating>5.0)
				{
					System.out.println("This is an invalid number.");
					System.out.println("Please enter a rating from 0.0 to 5.0:");
					numberRating=scan.nextDouble();
				}
				
				//Invokes Reliability Rating Method and prints updated details
				productDetails.rateReliability(numberRating);
				System.out.println(productDetails);
				
				//--------Menu for next option-----------//
				System.out.println(menu);
				input= scan.next().charAt(0);
				}
					  //OPTION C//
			else if(input=='C' || input=='c')
			{
				System.out.println("Thank you using the app. See you next time.");
				break;
			}
			else
			{	//FOR INVALID OUTPUT FROM THE MENU
				System.out.println("Please enter a valid option! \n");
				System.out.println(menu);
				input= scan.next().charAt(0);
			}
		}
	}
}
