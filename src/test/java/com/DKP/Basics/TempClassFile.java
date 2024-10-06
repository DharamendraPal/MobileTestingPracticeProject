package com.DKP.Basics;

public class TempClassFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public  void main1()
	{
		System.out.println("Inside main1 method");
	}
	public  void main2()
	{
		try
		{
			System.out.println(5/0);
		}
		
		catch(ArithmeticException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
		}
		
		catch(Throwable t) 
		{
			t.printStackTrace();
		}
		finally
		{
			System.out.println("Inside main2 method");
			System.out.println("Inside Finally block");
		}
	}
	

}
