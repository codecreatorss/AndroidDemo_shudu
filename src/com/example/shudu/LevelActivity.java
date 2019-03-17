package com.example.shudu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class LevelActivity extends Activity{
	private static final int MENU1 = 1; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new ShuduView(this));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, MENU1, 1, "返回");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) { 
		case 1:
			Intent i=new Intent().setClass(LevelActivity.this, MainActivity.class);
			startActivity(i);
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	 private long exitTime = 0;  
		@Override  
		public boolean onKeyDown(int keyCode, KeyEvent event) {  
		    if(keyCode == KeyEvent.KEYCODE_BACK  
		            && event.getAction() == KeyEvent.ACTION_DOWN){  
		        if((System.currentTimeMillis()-exitTime) > 2000){  
		            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();  
		            exitTime = System.currentTimeMillis();  
		        } else {  
		           finish();  
		            System.exit(0);  
		        }  
		        return true;  
		    }  
		    return super.onKeyDown(keyCode, event);  
		}     
}
