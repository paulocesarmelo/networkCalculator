package br.com.networkCalculator.activity;

import com.example.networkcalculator.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity{

	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
		ImageView img1 = (ImageView) findViewById(R.id.imageViewIcon);
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "LearningCurve.ttf");
		tv1.setTypeface(font);
		
		img1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {								
			
				intent = new Intent();
				intent.setClass(MainActivity.this,CalculateActivity.class);
				
				startActivity(intent);
				
				finish();
			
			}
		
		});
	}
	
}
