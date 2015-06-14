package br.com.networkCalculator.activity;

import java.math.BigDecimal;
import java.util.ArrayList;


import br.com.networcCalculator.calculation.NetworkCalculation;

import com.example.networkcalculator.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends Activity{
	
	private int n1, n2, n3, n4, mask;
	private BigDecimal qtd;
	private String m;
	private String[] ip;
	
	Button calculateButton;
	Button eraseButton;
	Button exitButton;
	EditText et1;
	EditText et2;
	EditText et3;
	EditText et4;
	EditText etMask;
	
	private TextView tvHost, tvMask, tvNet, tvBroad;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate);
		
		calculateButton = (Button) findViewById(R.id.btnCalculate);
		eraseButton = (Button) findViewById(R.id.btnErase);
		exitButton = (Button) findViewById(R.id.btnExit);
		et1 = (EditText) findViewById(R.id.editTextIP1);
		et2 = (EditText) findViewById(R.id.editTextIP2);
		et3 = (EditText) findViewById(R.id.editTextIP3);
		et4 = (EditText) findViewById(R.id.editTextIP4);
		etMask = (EditText) findViewById(R.id.editTextMask);
		
		tvHost = (TextView) findViewById(R.id.ViewHosts);
		tvMask = (TextView) findViewById(R.id.ViewMask);
		tvNet = (TextView) findViewById(R.id.ViewNetwork);
		tvBroad = (TextView) findViewById(R.id.ViewBroadcast);
		
		
		calculateButton.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				n1 = Integer.parseInt(et1.getText().toString());
				n2 = Integer.parseInt(et2.getText().toString());
				n3 = Integer.parseInt(et3.getText().toString());
				n4 = Integer.parseInt(et4.getText().toString());
				mask = Integer.parseInt(etMask.getText().toString());
				
				ip = new String[2];
				NetworkCalculation nc = new NetworkCalculation(n1, n2, n3, n4, mask);
								
				if(!nc.hasConstraint()){
					qtd = nc.calcularQtdHosts(mask);
					m = nc.calcularMascara(mask);
					ip = nc.calcularIp();
				
					setValues();
				}else{
					
					AlertDialog builder = new AlertDialog.Builder(CalculateActivity.this).create(); 
					 
					builder.setTitle("Constraint"); 
					 
					builder.setMessage("Reserved IP");					
					
					builder.setButton("OK", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int which) {
								eraseValues();
							}
							});
					builder.setIcon(R.drawable.ic_launcher);
					builder.show();
					
					
				}
			}
		});
		
		eraseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				eraseValues();
				et1.requestFocus();
				
			}
		});
		
		exitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CalculateActivity.this.finish();
			}
		});
		
	}
	
	public void eraseValues(){
		et1.setText(null);
		et2.setText(null);
		et3.setText(null);
		et4.setText(null);
		etMask.setText(null);
		tvHost.setText("Host: ");
		tvMask.setText("Mask (Decimal): ");
		tvNet.setText("Network IP: ");
		tvBroad.setText("Broadcast IP: ");
	}
	
	public void setValues(){
		tvHost.setText("Hosts: "+qtd);
		tvMask.setText("Mask (Decimal): "+m);
		tvNet.setText("Network IP: "+ip[0]);
		tvBroad.setText("Broadcast IP: "+ip[1]);
	}
	
}
