package com.ninh.beans;

import android.util.Log;

public class TaxiList {
	String id;
	String id_city;
	String number;
	String seat;
	String thumb;
	String name;
	String startprice;
	String nextprice;
	String pricedetail;
	String type;

	public TaxiList(String id, String id_city, String number, String seat,
			String thumb, String name, String startprice, String nextprice,
			String pricedetail, String type) {
		this.id = id;
		this.id_city = id_city;
		this.number = number;
		this.seat = seat;
		this.thumb = thumb;
		this.name = name;
		this.startprice = startprice;
		this.nextprice = nextprice;
		this.pricedetail = pricedetail;
		this.type = type;
	}

	public String GetID() {
		return id;
	}

	public String GetID_City() {
		return id_city;
	}

	public String GetNumber() {
		return number;
	}

	public String GetSeat() {
		return seat;
	}

	public String GetThumb() {
		if(thumb == null)
		{
			thumb ="icon_taxi.png";
		}else if (thumb == "saigon_logo.png")
		{
			thumb ="icon_taxi.png";
		}else if (thumb == "277_logo.png")
		{
			thumb ="haibaybay_logo.png";
		}
		return split(thumb);
	}

	public String GetName() {
		return name;
	}

	public String GetStartPrice() {
		return startprice;
	}

	public String GetNextPrice() {
		return nextprice;
	}

	public String GetPriceDetail() {
		return pricedetail;
	}

	public String Gettype() {
		return type;
	}
	
	 String split(String input){
//	    	Log.d("Ninh"," : " +input.replace(".png", ""));
	    	return input.replace(".png", "");
	   }
}
