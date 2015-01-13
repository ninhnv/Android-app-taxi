package com.ninh.adapter;

import com.ninh.beans.TaxiList;
import com.zenapp.taxiviet.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaxiTaiListAdapter extends ArrayAdapter<TaxiList> {
	
	Context mContext;

	public TaxiTaiListAdapter(Context context) {
		super(context, 0);
		mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(null!=mContext) {
			if(convertView==null)
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_xetai, null);
//			((ImageView)(convertView.findViewById(R.id.image))).setImageResource(mContext.getResources().getIdentifier(getItem(position).GetImg(),"drawable", mContext.getPackageName()));
			((TextView)(convertView.findViewById(R.id.subject))).setText(getItem(position).GetName());
			((TextView)(convertView.findViewById(R.id.to))).setText(getItem(position).GetNumber());
//			((TextView)(convertView.findViewById(R.id.date))).setText(getItem(position).GetSeat()+ " Chỗ");
			((TextView)(convertView.findViewById(R.id.tx_id))).setText(getItem(position).GetID());
		}
		return convertView;
	}
	
}
