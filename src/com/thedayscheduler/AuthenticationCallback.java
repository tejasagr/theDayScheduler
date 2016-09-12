package com.thedayscheduler;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

@SuppressWarnings("serial")
public class AuthenticationCallback extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		GoogleAuthorizationCodeFlow authFlow = Methods.initializeFlow();
		
		GoogleAuthorizationCodeTokenRequest tokenRequest =
				authFlow.newTokenRequest(req.getParameter("code")).setRedirectUri(Methods.getRedirectUri(req));
		
		GoogleTokenResponse tokenResponse = tokenRequest.execute();
		String userId = Methods.getUserId();
		
		authFlow.createAndStoreCredential(tokenResponse, userId);
		
		resp.sendRedirect("/");
	}
	
}
