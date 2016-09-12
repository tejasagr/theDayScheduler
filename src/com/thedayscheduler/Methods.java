package com.thedayscheduler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
//import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
//import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.*;
import com.google.appengine.api.users.UserServiceFactory;

public class Methods {
	//configurations
	private static NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final AppEngineDataStoreFactory DATA_STORE_FACTORY = AppEngineDataStoreFactory.getDefaultInstance();
	static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	static GoogleClientSecrets clientSecrets = null;
	
	static GoogleClientSecrets getClientSecrets() throws IOException {
		if (clientSecrets == null) {
				InputStream inputStream = Methods.class.getResourceAsStream("/client_secrets.json");
				clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputStream));
		}
		    return clientSecrets;
	}
	
	static GoogleAuthorizationCodeFlow initializeFlow() throws IOException {
		return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
		        getClientSecrets(), Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(
		        DATA_STORE_FACTORY).build();
		
	}
	
	static String getUserId() {
		return UserServiceFactory.getUserService().getCurrentUser().getUserId();
	}
	
	static String getUsername() {
		return UserServiceFactory.getUserService().getCurrentUser().toString();
	}
	
	static Credential getCredential() throws IOException {
		return initializeFlow().loadCredential(getUserId());
	}
	
	static Calendar getCalendarService() throws IOException {
		Calendar.Builder calBuilder = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredential())
			    .setApplicationName("TheDayScheduler");
		return calBuilder.build();
	}
	
	
	static String getRedirectUri(HttpServletRequest req) {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}
	
	static String replaceAtSymbol(String email) {
		return email.replaceAll("@", "%40");
	}
}
