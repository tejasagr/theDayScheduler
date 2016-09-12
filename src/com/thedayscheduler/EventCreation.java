package com.thedayscheduler;

import java.io.IOException;
import javax.servlet.http.*;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;

@SuppressWarnings("serial")
public class EventCreation extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		com.google.api.services.calendar.Calendar cal = Methods.getCalendarService();
		
		String eventName 	= req.getParameter("eventName");
		String start 		= req.getParameter("start") + ":00+08:00";
		String end 			= req.getParameter("end") 	+ ":00+08:00";
		String calendarId 	= "primary";
		
		Event event = new Event();
		event.setSummary(eventName);
		
		DateTime startDT = new DateTime(start);
		EventDateTime startEDT = new EventDateTime()
				.setDateTime(startDT);
		event.setStart(startEDT);
		
		DateTime endDT = new DateTime(end);
		EventDateTime endEDT = new EventDateTime()
				.setDateTime(endDT);
		event.setEnd(endEDT);
		 
		cal.events().insert(calendarId, event).execute();
		
		resp.sendRedirect("home.jsp");
		
	}
	
}