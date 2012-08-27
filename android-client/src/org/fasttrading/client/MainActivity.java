/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fasttrading.client;
//http://10.0.2.2:8080/spring-android-basic-auth-server
import java.util.Collections;

import org.fasttrading.client.R;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author Roy Clarkson
 */
public class MainActivity extends AbstractAsyncActivity {

	protected static final String TAG = MainActivity.class.getSimpleName();

	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_layout);

		// Initiate the request to the protected service
		final Button submitButton = (Button) findViewById(R.id.submit);
		submitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new FetchSecuredResourceTask().execute();
			}
		});
		ImageView loginView=(ImageView)findViewById(R.id.login);
		loginView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),Activity2nd.class);
				startActivity(intent);
				finish();
			}
		});
		
	}
	
	// ***************************************
	// Private methods
	// ***************************************
	private void displayResponse(Member response) {
		Toast.makeText(this, response.name, Toast.LENGTH_LONG).show();
	}

	// ***************************************
	// Private classes
	// ***************************************
	private class FetchSecuredResourceTask extends AsyncTask<Void, Void, Member> {

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
		protected Member doInBackground(Void... params) {
			final String url = getString(R.string.base_uri) + "/getprofile";
			// Populate the HTTP Basic Authentitcation header with the username and password
			HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setAuthorization(authHeader);
			requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
			

		    try{
		    	ResponseEntity<Member> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Member.class);
		        return response.getBody();
		    }catch (Exception e) {
		    	return new Member("Ã³Ñ°ªÑÂ","à©Ô¹ºÓÃØ§","2/427","086-782-8889","12chg0d@gmail.com");	    	
		    }
		}

		@Override
		protected void onPostExecute(Member result) {
			dismissProgressDialog();
			displayResponse(result);
		}

	}
	
}
