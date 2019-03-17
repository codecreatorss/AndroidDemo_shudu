package com.example.shudu;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ShuduView extends View{

	//单元格的宽度和高度
	private float width;
	private float height;
	private int selectedX;
	private int selectedY;
	
	private Game game = new Game();
	
	public ShuduView(Context context) {
		super(context);
	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		//计算当前单元格的宽度和高度
		this.width = w / 9f;
		this.height = h / 9f;
		super.onSizeChanged(w, h, oldw, oldh);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		//生成用于绘制背景色的画笔
		Paint backgroundPaint = new Paint();
		//设置画笔的颜色
		backgroundPaint.setColor(getResources().getColor(R.color.shudu_background));
		//绘制背景色
		canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
        
		Paint darkPaint = new Paint();
		darkPaint.setColor(getResources().getColor(R.color.shudu_dark));
        
		Paint hilitePaint = new Paint();
		hilitePaint.setColor(getResources().getColor(R.color.shudu_hilite));
        
		Paint lightPaint = new Paint();
		lightPaint.setColor(getResources().getColor(R.color.shudu_light));
		for(int i = 0; i < 9 ;i++){
			//一下两行代码用户绘制横向的单元格线
			canvas.drawLine(0, i * height, getWidth(), i * height,lightPaint);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilitePaint);
			
			canvas.drawLine(i * width, 0, i * width, getHeight(), lightPaint);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilitePaint);
		}
		
		for(int i = 0 ; i < 9;i++){
			if(i % 3 != 0){
				continue;
			}
			canvas.drawLine(0, i * height, getWidth(), i* height, darkPaint);
			canvas.drawLine(0,i * height + 1,getWidth(), i * height + 1,hilitePaint);
			canvas.drawLine(i * width, 0, i * width, getHeight(), darkPaint);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilitePaint);
		}
		//绘制数字
		Paint numberPaint = new Paint();
		numberPaint.setColor(Color.BLACK);
		numberPaint.setStyle(Paint.Style.STROKE);
		numberPaint.setTextSize( height * 0.75f);
		numberPaint.setTextAlign(Paint.Align.CENTER);
		FontMetrics fm = numberPaint.getFontMetrics();
		float x = width/2;
		
		float y = height / 2 - (fm.ascent + fm.descent) /2;
		for(int i = 0 ; i < 9; i++){
			for(int j = 0; j < 9 ;j++){
				canvas.drawText(game.getTileString(i, j), i * width + x,  j * height + y, numberPaint);
			}
		}
		super.onDraw(canvas);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN){
			return super.onTouchEvent(event);
		}
		
		selectedX = (int)(event.getX() / width);
		selectedY = (int)(event.getY() / height);
		
		int  used [] = game.getUsedTilesByCoor(selectedX, selectedY);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < used.length; i++) {
			sb.append(used[i]);
			Log.d("test", "used="+used[i]);
		}
		
		KeyDialog keyDialog = new KeyDialog(getContext(), used,this);
		keyDialog.show();
		//LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
		//View layoutView = layoutInflater.inflate(R.layout.keypad, null);
		//TextView textView =(TextView)layoutView.findViewById(R.id.usedTextId);
		//textView.setText(sb.toString());
		//AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
		//builder.setView(layoutView);
		//AlertDialog dialog = builder.create();
		//dialog.show();
		return true;
	}
	public void setSelectedTile(int tile) {
		if (game.setTileIfValid(selectedX, selectedY, tile)) {
			invalidate();
		} 
	}
}
