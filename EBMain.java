package com.wipro.eb.main;

import java.util.*;

import com.wipro.eb.exception.InvalidConnectionException;
import com.wipro.eb.exception.InvalidReadingException;
import com.wipro.eb.service.ConnectionService;

public class EBMain {
	
	public static void main(String[] args) throws InvalidReadingException, InvalidConnectionException
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the previous month reading:");
		int previousReading = Integer.parseInt(sc.nextLine());
		
		System.out.println("Enter the current month reading:");
		int currentReading = Integer.parseInt(sc.nextLine());
		
		System.out.println("Enter the connection type:");
		String connectionType = sc.nextLine();
		
		System.out.println(new ConnectionService().generateBill(previousReading, currentReading, connectionType));
	}

}
