package com.ninh.taxi;

import com.zenapp.taxiviet.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabAndListView extends TabActivity {
	// TabSpec Names
	private static final String DIA_DIEM = "";
	private static final String YEU_THIC = "";
	private static final String INFO = "";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        TabHost tabHost = getTabHost();
        
        // dia diem Tab
        TabSpec inboxSpec = tabHost.newTabSpec(DIA_DIEM);
        // Tab Icon
        inboxSpec.setIndicator(DIA_DIEM, getResources().getDrawable(R.drawable.icon_diadiem));
        Intent inboxIntent = new Intent(this, TaxiListActivity.class);
        // Tab Content
        inboxSpec.setContent(inboxIntent);
        
        // yeu thic Tab
        TabSpec outboxSpec = tabHost.newTabSpec(YEU_THIC);
        outboxSpec.setIndicator(YEU_THIC, getResources().getDrawable(R.drawable.icon_yeuthic));
        Intent outboxIntent = new Intent(this, TaxiTaiListActivity.class);
        outboxSpec.setContent(outboxIntent);
        
        // Profile Tab
        TabSpec profileSpec = tabHost.newTabSpec(INFO);
        profileSpec.setIndicator(INFO, getResources().getDrawable(R.drawable.icon_profile));
        Intent profileIntent = new Intent(this, InfoActivity.class);
        profileSpec.setContent(profileIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(inboxSpec); // Adding  tab
        tabHost.addTab(outboxSpec); // Adding  tab
        tabHost.addTab(profileSpec); // Adding Profile tab
    }
}