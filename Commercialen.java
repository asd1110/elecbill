package com.wipro.eb.entity;

public class Commercial extends Connection
{
	public Commercial(int currentReading, int previousReading,float slabs[])
	{
		super(currentReading, previousReading, slabs);
	}
	
	public float computeBill()
	{
		float[] s = super.getSlabs();
		
		float amount =(float) ((s[0] * 5.2) + (s[1] * 6.8) + (s[2] * 8.3));
		float electricityDuty = 0.0f;
		
		if(amount >= 10000)
			electricityDuty = amount * 0.09f;
		else if(amount >= 5000)
			electricityDuty = amount * 0.06f;
		else
			electricityDuty = amount * 0.02f;
		
		return (amount + electricityDuty);
	}
}
