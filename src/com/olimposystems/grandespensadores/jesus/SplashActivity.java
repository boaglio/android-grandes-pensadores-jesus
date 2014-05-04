package com.olimposystems.grandespensadores.jesus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity implements Runnable {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Handler handler = new Handler();
		handler.postDelayed(this,3000);
	}

	public void run() {
		startActivity(new Intent(this,GrandesPensadoresActivity.class));
		finish();
	}
}
