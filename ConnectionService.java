package com.wipro.eb.service;

import com.wipro.eb.entity.Commercial;
import com.wipro.eb.entity.Connection;
import com.wipro.eb.entity.Domestic;
import com.wipro.eb.exception.*;

public class ConnectionService {
	
	public boolean validate(int currentReading, int previousReading, String type) throws InvalidReadingException, InvalidConnectionException
	{
		if(((currentReading > previousReading) && (currentReading > 0) && (previousReading > 0)) && (type.equals("Domestic") || type.equals("Commercial")))
		{
			return true;
		}
		else
		{
			if((currentReading < previousReading) || (currentReading < 0) || (previousReading < 0))
			{
				try
				{
					throw new InvalidReadingException();
				}
				catch(InvalidReadingException e)
				{
					System.out.println(e);
				}
			}
			
			if(!type.equals("Domestic") || !type.equals("Commercial"))
			{
				try
				{
					throw new InvalidConnectionException();
				}
				catch(InvalidConnectionException e)
				{
					System.out.println(e);
				}
			}
			return false;
		}
	}
	
	public float calculateBillAmt(int currentReading, int previousReading, String type)
	{
		boolean chk = false;
		String exceptionString = " ";
		float bill;
		
			try 
			{
				chk = validate(currentReading, previousReading, type);
			} 
			catch (InvalidReadingException e) 
			{
				exceptionString = "InvalidReadingException";
				e.printStackTrace();
			} 
			catch (InvalidConnectionException e) 
			{
				exceptionString = "InvalidConnectionException";
				e.printStackTrace();
			}
			
		if(chk == true)
		{
			if(type.equals("Domestic"))
			{
				float[] slab = {50.0f, 50.0f, (currentReading - previousReading)};
				Connection domConn = new Domestic(currentReading, previousReading, slab);
				bill = domConn.computeBill();
			}
			else
			{
				float[] slab = {50.0f,50.0f,((currentReading - previousReading))};
				Connection commConn = new Commercial(currentReading, previousReading, slab);
				bill = commConn.computeBill();
			}
			
			return bill;
		}
		else
		{
			if(exceptionString.equals("InvalidReadingException"))
				return -1.0f;
			else
				return -2.0f;
		}
	}

	public String generateBill(int currentReading, int previousReading, String type) throws InvalidReadingException, InvalidConnectionException 
	{
		float check = calculateBillAmt(currentReading, previousReading, type);
		
		if(check == -1.0f)
		{
			return "Incorrect Reading";
		}
		else if(check == -2.0f)
		{
			return "Invalid ConnectionType";
		}
		else
		{
			return "Amount to be paid: " + check;
		}
	}
}
