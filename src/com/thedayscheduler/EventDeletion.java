package com.thedayscheduler;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

@SuppressWarnings("serial")
public class EventDeletion extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		com.google.api.services.calendar.Calendar cal = Methods.getCalendarService();
		
		String eventName = req.getParameter("eventName");
		String eventDate = req.getParameter("eventDate");
		
		DateTime timeMin 	= new DateTime(eventDate+"T00:00:00+08:00");
		DateTime timeMax 	= new DateTime(eventDate+"T23:59:59+08:00");
		
		//show events
		String pageToken = null;
		do {
		  Events events = cal.events().list("primary")
				  .setPageToken(pageToken)
				  .setTimeMin(timeMin)
				  .setTimeMax(timeMax)
				  .execute();
		  List<Event> items = events.getItems();
		  for (Event event : items) {
			  if (event.getSummary().equals(eventName)) {
				  cal.events().delete("primary", event.getId()).execute();
			  }
		  }
		  pageToken = events.getNextPageToken();
		}	while (pageToken != null);
		
		resp.sendRedirect("home.jsp");
		
	}
	
}