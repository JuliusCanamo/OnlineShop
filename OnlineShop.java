/***
 * Student Name: Laraib Saeed
 * Student ID: 21145515
 * Course Code: COMP503
 * Assessment Item: Individual Programming Assignment Part A [Online Shop Rating Application]
 ***/
package assignment;

public class OnlineShop 
{
	private Product[] inventory= new Product[5];
	private int nProduct=this.inventory.length;		

	//------------------------------------------------------------------
	//Constructor
	public OnlineShop ()
	{
		for(int i=0;i<inventory.length;i++)
		{
		this.inventory[i]=new Product("","",0.0);
		}
		
		this.nProduct=0;		
	}
	//------------------------------------------------------------------
	//METHOD:Adds products to inventory
	public void add(Product inventory)
	{
			if(nProduct<5)
			{
				this.inventory[nProduct]=inventory;
			}
			nProduct++;
	}
	//------------------------------------------------------------------
	//METHOD: toString
	//toString references to product's class toString
	
	public String toString()
	{
		String out="";
		
		for(int i=1;i<this.nProduct+1;i++)
		{
			out+=i+"." +this.inventory[i-1].toString()+"\n";	
		}				
		return out;
	}
	
	//METHOD: Gets products
	public Product getProduct(int input)
	{	
		if((input>=1) && (input<=5 ))
		{
			return this.inventory[input-1];
		}
		else
		{
			return null;
		}
		
	}

} 