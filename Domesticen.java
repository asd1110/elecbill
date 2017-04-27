package com.wipro.eb.entity;

public class Domestic extends Connection
{
	
	public Domestic(int currentReading, int previousReading,float slabs[])
	{
		super(currentReading, previousReading, slabs);
	}
	
	public float computeBill()
	{
		float[] s = super.getSlabs();
		
		float amount =(float) ((s[0] * 2.3) + (s[1] * 4.2) + (s[2] * 5.5));
		
		return amount;
	}

}
