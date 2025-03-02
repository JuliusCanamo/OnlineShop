/***
 * Student Name: Laraib Saeed
 * Student ID: 21145515
 * Course Code: COMP503
 * Assessment Item: Individual Programming Assignment Part A [Online Shop Rating Application]
 ***/
package assignment;

public class Product 
{
	//Instance variables
	
	private String modelName;
	private String manufacturerName;
	private double retailPrice;
	private double reliabilityRating;
	private int numOfConsumers;
	
	//----------------------------------------------------------------------------------
	//Constructors
	//CONSTRUCTOR 1
	public Product(String modelName,String manufacturerName,double retailPrice)
	{
		this.modelName=modelName;
		this.manufacturerName=manufacturerName;
		this.retailPrice=retailPrice;
		this.reliabilityRating=5.0; //Default value is full 5.0
		this.numOfConsumers=1;
	}
	
	public Product(String modelName,String manufacturerName)
	{
		this.modelName=modelName;
		this.manufacturerName=manufacturerName;
		this.retailPrice=0.0;
		this.reliabilityRating=5.0;
		this.numOfConsumers=1;
	}
	//----------------------------------------------------------------------------------
	//Methods
	//Method: Get & Set 
	
	//Model name	
	public String getModelname()
	{
		return this.modelName;
	}
	
	//Manufacturer name
	public String getManufacturername()
	{
		return this.manufacturerName;
	}
	
	//Retail Price
	public void setRetailprice(double retailPrice)
	{
		this.retailPrice=retailPrice;
	}
	
	public double getRetailprice()
	{
		return this.retailPrice;
	}
	
	//Reliability rating
	public double getReliabilityrating()
	{
		return this.reliabilityRating;
	}
	
	//Number of consumers rated the product
	public int getNumofconsumers()
	{
		return this.numOfConsumers;
	}
	
	//-----------------------------------------------------------------------
	///Method: To String 
	@Override
	public String toString()
	{
		String out="";
		
			out="Product Details \n"+
				"  ---------------\n"+
			  " Model Name: "+this.modelName
			  +"\n Manufacturer Name: "+this.manufacturerName
			  +"\n Retail Price: $"+this.retailPrice
			  +"\n Reliability Rating: "+this.reliabilityRating+
			  "(based on "+this.numOfConsumers+" customer ratings)\n";
		return out;
	}
	//----------------------------------------------------------------------
	//Method: Reliability Rating
	public double rateReliability(double reliabilityRating)
	{
		int oldValue=this.numOfConsumers;
		double oldRating=this.reliabilityRating;
		
		if (this.reliabilityRating>=0.0 && this.reliabilityRating <=5.0)
		{
			++numOfConsumers;
		}
		
		 this.reliabilityRating=(oldRating*oldValue+reliabilityRating)/this.numOfConsumers;
		 
		 return reliabilityRating;
	
	}

}
