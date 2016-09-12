package com.thedayscheduler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import java.io.PrintWriter;
import javax.servlet.http.*;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Authentication extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//resp.setContentType("text/plain");
		//PrintWriter pw = resp.getWriter();
		
		UserService userService = UserServiceFactory.getUserService();
		
		if (!userService.isUserLoggedIn())				//if the user isn't logged in
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		
		Credential credential = Methods.getCredential();
		
		if (credential == null) {						//if the user does not have a credential
			GoogleAuthorizationCodeFlow authFlow = Methods.initializeFlow();
			String authURL = authFlow.newAuthorizationUrl().setRedirectUri(Methods.getRedirectUri(req)).build();
			resp.sendRedirect(authURL);
		}
		
		try {
			Calendar cal = Methods.getCalendarService();
			@SuppressWarnings("unused")
			Events events = cal.events().list("primary").setPageToken(null).execute();
		} catch(GoogleJsonResponseException UNAUTHORIZED) {
			GoogleAuthorizationCodeFlow authFlow = Methods.initializeFlow();
			String authURL = authFlow.newAuthorizationUrl().setRedirectUri(Methods.getRedirectUri(req)).build();
			resp.sendRedirect(authURL);
		}
		
		String username = Methods.getUsername();
		String usernameURL = Methods.replaceAtSymbol(username);
		req.getSession().setAttribute("usernameURL", usernameURL);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		dispatcher.forward(req, resp);
		//resp.sendRedirect("home.jsp");
	}

}
