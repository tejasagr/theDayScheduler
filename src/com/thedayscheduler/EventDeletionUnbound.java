package com.thedayscheduler;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

@SuppressWarnings("serial")
public class EventDeletionUnbound extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		com.google.api.services.calendar.Calendar cal = Methods.getCalendarService();
		
		String eventName = req.getParameter("eventName");
		
		//show events
		String pageToken = null;
		do {
		  Events events = cal.events().list("primary").setPageToken(pageToken).execute();
		  List<Event> items = events.getItems();
		  for (Event event : items) {
			  if (event.getSummary().equals(eventName)) {
				  cal.events().delete("primary", event.getId()).execute();
			  }
		  }
		  pageToken = events.getNextPageToken();
		}	while (pageToken != null);
		
		resp.sendRedirect("eventDeleter.jsp");
		
	}
	
}