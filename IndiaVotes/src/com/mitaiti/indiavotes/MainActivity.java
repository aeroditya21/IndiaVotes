package com.mitaiti.indiavotes;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends Activity {

	AppUser user;
	EditText Name;
	EditText Age;
	int age;
	Gender gender;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 HttpURLConnection conn = null;
	     StringBuilder jsonResults = new StringBuilder();
	     String pythonURL = "http://localhost:8000/polls/get_polls/";
	     try{
	    	 URL url = new URL(pythonURL);
	            conn = (HttpURLConnection) url.openConnection();
	            InputStreamReader in = new InputStreamReader(conn.getInputStream());
	            
	            int read;
	            char[] buff = new char[1024];
	            while ((read = in.read(buff)) != -1) {
	                jsonResults.append(buff, 0, read);
	            }
	     }
	     catch(Exception e){
	            Log.e("ERROR", "Python problem : check logcat!", e);

	     }
	     
	     try {
	            // Create a JSON object hierarchy from the results
	        	Log.d("JSON","Parsing resultant JSON :)");
	            JSONObject jsonObj = new JSONObject(jsonResults.toString());
	            JSONArray predsJsonArray = jsonObj.getJSONArray("fields");
	            Log.d("JSON", predsJsonArray.toString());
	     }
	     catch(Exception e){
	            Log.e("ERROR", "Python problem : check logcat!", e);

	     }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void goCricket(View view) {
		recordUser();
		Log.d("Cricket",user.getName());
		Log.d("Cricket",Integer.toString(user.getAge()));
		Log.d("Cricket",user.getGender().toString());
		user.setTopic(0);
		Intent next = new Intent(this,CricketActivity.class);
		next.putExtra("Topic", "0");
		
		if(Math.random()>0.5)
		{
			user.setQuestion(0);
			next.putExtra("Question","0");
		}
		else
		{
			user.setQuestion(1);
			next.putExtra("Question","1");
		}
		startActivity(next);
	}
	
	public void goBollywood(View view){
		recordUser();
		Log.d("Bollywood",user.getName());
		Log.d("Bollywood",Integer.toString(user.getAge()));
		Log.d("Bollywood",user.getGender().toString());
		user.setTopic(1);
		Intent next = new Intent(this,CricketActivity.class);
		next.putExtra("Topic", "1");

		if(Math.random()>0.5)
		{
			user.setQuestion(0);
			next.putExtra("Question","0");
		}
		else
		{
			user.setQuestion(1);
			next.putExtra("Question","1");
		}
		startActivity(next);
	}
	
	private void recordUser(){
		Name = (EditText) (findViewById(R.id.editText1));
		Age = (EditText) (findViewById(R.id.editText2));
		age = Integer.parseInt(Age.getText().toString());
		user = new AppUser();
		user.setAppUser(Name.getText().toString(), age, gender);
	}

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();

		switch (view.getId()) {
		case R.id.radioButton1:
			if (checked)
				gender = Gender.MALE;
			break;
		case R.id.radioButton2:
			if (checked)
				gender = Gender.FEMALE;
			break;
		}
	}
}
