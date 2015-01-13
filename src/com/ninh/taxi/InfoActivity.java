package com.ninh.taxi;

import com.zenapp.taxiviet.R;
import com.zenapp.taxiviet.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

public class InfoActivity extends Activity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layout.web_view_home);


		webView = (WebView) findViewById(R.id.web_view_help);
		webView.getSettings().setDomStorageEnabled(true);
		webView.setVerticalScrollBarEnabled(false);
		webView.setHorizontalScrollBarEnabled(false);
		webView.setFocusableInTouchMode(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/help.html");
	}
	
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				 .setIcon(android.R.drawable.btn_star_big_off)
				.setTitle("Taxi Việt Nam")
				.setMessage("Bạn có muốn đóng ứng dụng")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}

						}).setNegativeButton("No", null).show();
	}
}
