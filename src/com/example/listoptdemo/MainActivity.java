package com.example.listoptdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void viewProvider(View view){
		startActivity(new Intent(this, MultiItemListViewActivityWithViewProvider.class));
	}
	public void adapterHolder(View view){
		startActivity(new Intent(this, MultiItemListViewActivityWithAdapterHolder.class));
	}
}

 