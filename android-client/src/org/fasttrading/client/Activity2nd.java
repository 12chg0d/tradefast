package org.fasttrading.client;

import java.util.Collections;

import org.fasttrading.client.R;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.System;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity2nd extends AbstractAsyncActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        ImageView loginView=(ImageView)findViewById(R.id.login);
		loginView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
    }
    private void displayResponse(Message response) {
		Toast.makeText(this, response.getText(), Toast.LENGTH_LONG).show();
	}

	// ***************************************
	// Private classes
	// ***************************************
	private class FetchSecuredResourceTask extends AsyncTask<Void, Void, Message> {

		private String username;

		private String password;

		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();
			
			// build the message object
			EditText editText = (EditText) findViewById(R.id.username);
			this.username = editText.getText().toString();

			editText = (EditText) findViewById(R.id.password);
			this.password = editText.getText().toString();
		}

		@Override
		protected Message doInBackground(Void... params) {
			final String url = getString(R.string.base_uri) + "/getmessage";

			// Populate the HTTP Basic Authentitcation header with the username and password
			HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setAuthorization(authHeader);
			requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

			try {
				// Make the network request
				Log.d(TAG, url);
				ResponseEntity<Message> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Message.class);
				return response.getBody();
			} catch (HttpClientErrorException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return new Message(0, e.getStatusText(), e.getLocalizedMessage());
			}
		}

		@Override
		protected void onPostExecute(Message result) {
			dismissProgressDialog();
			displayResponse(result);
		}

	}
}
    

