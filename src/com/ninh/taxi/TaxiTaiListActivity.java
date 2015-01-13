package com.ninh.taxi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;

import com.ninh.adapter.TaxiListAdapter;
import com.ninh.adapter.TaxiTaiListAdapter;
import com.ninh.beans.TaxiList;
import com.ninh.dbhelper.DatabaseHelper;
import com.zenapp.taxiviet.R;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TaxiTaiListActivity extends Activity {
	private Spinner spinner1;
	private ProgressDialog pDialog;
	Cursor c = null;
	ListView lv;
	ArrayList<TaxiList> tx_list = new ArrayList<TaxiList>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.taxi_tai_list_activity);
		lv = (ListView) findViewById(R.id.list);
		lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
//			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				final String number = ((TextView) view.findViewById(R.id.to)).getText().toString();
				final String name = ((TextView) view.findViewById(R.id.subject)).getText().toString();
				new AlertDialog.Builder(TaxiTaiListActivity.this)
				 .setIcon(android.R.drawable.sym_call_incoming)
				.setTitle("Taxi Viá»‡t Nam")
				.setMessage("Gá»�i tá»›i Taxi "+name)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								try{
									Intent callIntent = new Intent(Intent.ACTION_CALL);
								    callIntent.setData(Uri.parse("tel:\""+split(number)+ "\""));
								    startActivity(callIntent);
									}catch(Exception e)
									{
										Toast.makeText(TaxiTaiListActivity.this, "Call: " +split(number), Toast.LENGTH_SHORT).show();
										e.printStackTrace();
									}
							}

						}).setNegativeButton("No", null).show();
				
				
			}
		});	
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		 // Loading spinner data from database
        loadSpinnerData();
        spinner1.setSelection(23);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			    	tx_list.clear();
			    	new loaddb().execute(arg2);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}
	
	 
    String split(String input){
//    	Log.d("Split ---------------------"," : " +input.replace(" ", ""));
    	return input.replace(" ", "");
   }

	class loaddb extends AsyncTask<Integer, String, String> {

		@Override
		protected String doInBackground(Integer... params) {
			
			DatabaseHelper myDbHelper = new DatabaseHelper(TaxiTaiListActivity.this);
			try{
			myDbHelper.createDatabase();
			myDbHelper.openDataBase();
			for(int i = 0; i<= params.length;i++)
			{
				int a = params[i] + 1;
				String sql ="SELECT t1.id,id_city,number,seat,img,name ,startprice,nextprice,pricedetail FROM  tx_list t1 INNER JOIN tx_com t2 ON t1.id_com = t2.id WHERE t1.id_city = "+a+" and type = 0";
//				Log.d("Ninh", sql);
				c = myDbHelper.rawQuery(sql, null);
				
				if (c.moveToFirst()) {
					do {
						String id = c.getString(0);
						String id_city = c.getString(1);
						String number = c.getString(2);
						String seat = c.getString(3);
						String img = c.getString(4);
						String name = c.getString(5);
						String startprice = c.getString(6);
						String nextprice = c.getString(7);
						String pricedetail = c.getString(8);
						String type = c.getString(8);
						tx_list.add(new TaxiList(id,id_city, number, seat, img, name, startprice,nextprice,pricedetail,type));
					} while (c.moveToNext());
				}
			}
			
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			c.close();
			myDbHelper.close();
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			pDialog.dismiss();
			final TaxiTaiListAdapter adapter = new TaxiTaiListAdapter(TaxiTaiListActivity.this);
			for (int i = 0; i < tx_list.size(); i++) {
				adapter.add(tx_list.get(i));
			}
			runOnUiThread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					lv.setAdapter(adapter);
				}
			});
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(TaxiTaiListActivity.this);
			pDialog.setMessage("load...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

	}
	
	
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.btn_star_big_off)
				.setTitle("Taxi Viá»‡t Nam")
				.setMessage("Báº¡n cÃ³ muá»‘n Ä‘Ã³ng á»©ng dá»¥ng")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}

						}).setNegativeButton("No", null).show();
	}
	  private void loadSpinnerData() {
		  	DatabaseHelper myDbHelper = new DatabaseHelper(TaxiTaiListActivity.this);
	        List<String> lables = myDbHelper.getAllLabels();
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, lables);
	        dataAdapter
	                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner1.setAdapter(dataAdapter);
	    }
	
}
