package com.ninh.taxi;

import com.ninh.beans.TaxiList;
import com.ninh.dbhelper.DatabaseHelper;
import com.zenapp.taxiviet.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailTaxi extends Activity {
	Cursor c = null;
	String Title = null;
	String noi_dung = null;
	String id = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_single_content);
		Intent i = getIntent();
		Title = i.getStringExtra("Title");
		noi_dung = i.getStringExtra("noi_dung");
		id = i.getStringExtra("id");
		TextView txt_name = (TextView) findViewById(R.id.tx_name);
		TextView txt_number = (TextView) findViewById(R.id.tx_number);
		TextView txt_seat = (TextView) findViewById(R.id.tx_seat);
		TextView txt_giastart = (TextView) findViewById(R.id.tx_gia);
		TextView txt_giaend = (TextView) findViewById(R.id.tx_gia_end);
		TextView txt_details = (TextView) findViewById(R.id.tx_details);
		ImageView thumb=(ImageView)findViewById(R.id.imageView1);

		DatabaseHelper myDbHelper = new DatabaseHelper(DetailTaxi.this);
		myDbHelper.createDatabase();
		myDbHelper.openDataBase();
		try {
			c = myDbHelper
					.rawQuery(
							"SELECT id_city,number,seat,img,name ,startprice,nextprice,pricedetail FROM  tx_list t1 INNER JOIN tx_com t2 ON t1.id_com = t2.id WHERE t1.id = \""
									+ id + "\"", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (c.moveToFirst()) {
			String id_city = c.getString(0);
			String number = c.getString(1);
			String seat = c.getString(2);
			String img = c.getString(3);
			String name = c.getString(4);
			String startprice = c.getString(5);
			String nextprice = c.getString(6);
			String pricedetail = c.getString(7);

			txt_name.setText(name);
			txt_number.setText(number);
			txt_seat.setText(seat + " Chỗ");
			txt_giaend.setText("Giá mở cửa: " + startprice + " vnđ\n" +"KM tiếp theo: " + nextprice + " vnđ" );
			txt_giastart.setText("Bảng giá Taxi ");
			if (pricedetail.equals("")) {
				txt_details.setText("Chưa Cập Nhật");
			} else {
				txt_details.setText(pricedetail);
			}
			if(img == null)
			{
				img ="icon_taxi.png";
			}else if (img == "saigon_logo.png")
			{
				img ="icon_taxi.png";
			}else if (img == "277_logo.png")
			{
				img ="haibaybay_logo.png";
			}
			
			thumb.setImageResource(getResources().getIdentifier(split(img),"drawable", getPackageName()));
			
		}

		findViewById(R.id.btnback).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						finish();
					}
				});
		final String phone = ((TextView) findViewById(R.id.tx_number)).getText().toString();
		
		findViewById(R.id.btncall).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						try{
							Intent callIntent = new Intent(Intent.ACTION_CALL);
						    callIntent.setData(Uri.parse("tel:\""+split_number(phone)+ "\""));
						    startActivity(callIntent);
							}catch(Exception e)
							{
								Toast.makeText(DetailTaxi.this, "Call: " +split_number(phone), Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							}
					}
				});
		
		findViewById(R.id.img_call_tx).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						try{
							Intent callIntent = new Intent(Intent.ACTION_CALL);
						    callIntent.setData(Uri.parse("tel:\""+split_number(phone)+ "\""));
						    startActivity(callIntent);
							}catch(Exception e)
							{
								Toast.makeText(DetailTaxi.this, "Call: " +split_number(phone), Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							}
					}
				});

	}
	
	 String split(String input){
		 return input.replace(".png", "");
	   }
	 String split_number(String input){
		 return input.replace(" ", "");
	   }

}
