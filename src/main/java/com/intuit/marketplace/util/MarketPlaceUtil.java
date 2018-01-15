package com.intuit.marketplace.util;

import java.util.Date;

public class MarketPlaceUtil {

	
	public static boolean isBidDateOver(Date bidDate,Date projectDate)
	{
		boolean isBidClosed=false;
		
		if(bidDate.compareTo(projectDate)>0)
		{
			isBidClosed= true;
		}
		return isBidClosed;
	}
}
