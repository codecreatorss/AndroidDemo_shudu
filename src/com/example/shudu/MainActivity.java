package com.example.shudu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btneasy,btndifficult,btnmedium;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btneasy=(Button)findViewById(R.id.btneasy);
		btndifficult=(Button)findViewById(R.id.btndifficult);
		btnmedium=(Button)findViewById(R.id.btnmedium);
        btneasy.setOnClickListener(easyListener);
        btndifficult.setOnClickListener(difficultListener);
        btnmedium.setOnClickListener(mediumListener);
	}
	 private Button.OnClickListener easyListener=new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//145327698839654127672918543
				//496185372218473956753296481
				//367542819984761235521839764
				// 数独初始化数据的基础
				//005300000800000020070010500
				//400005300010070006003200080
				//060500009004000030000009700

				Common.str = "005320600809050120070910503"
						+ "406085302010473056003206081"
						+ "060502009904060030501039704";
				Intent i=new Intent();
				i.setClass(MainActivity.this,LevelActivity.class);
				startActivity(i);
				finish();
			}
			
		};
     private Button.OnClickListener difficultListener=new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 数独初始化数据的基础
				//800000000003600000070090200
				//050007000000045700000100030
				//001000068008500010090000400
				Common.str = "800000000003600000070090200"
						+ "050007000000045700000100030"
						+ "001000068008500010090000400";
				Intent i=new Intent();
				i.setClass(MainActivity.this,LevelActivity.class);
				startActivity(i);
				finish();
			}
			
		};
	 private Button.OnClickListener mediumListener=new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 数独初始化数据的基础
				//007238000060700050000400002
				//900000867100000003648000005
				//700003000020005030000174900
				Common.str = "007238000060700050000400002"
						+ "900000867100000003648000005"
						+ "700003000020005030000174900";
				Intent i=new Intent();
				i.setClass(MainActivity.this,LevelActivity.class);
				startActivity(i);
				finish();
			}
			
		};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
