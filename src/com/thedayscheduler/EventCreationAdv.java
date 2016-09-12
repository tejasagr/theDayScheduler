package com.thedayscheduler;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.*;
import com.google.api.services.calendar.model.*;
import com.google.api.client.util.DateTime;

@SuppressWarnings("serial")
public class EventCreationAdv extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		com.google.api.services.calendar.Calendar cal = Methods.getCalendarService();
		String ampangId 	=	"iskl.edu.my_7fqt0f2sj8odprhnalgdsa1a5k@group.calendar.google.com";
		String melawatiId  	=	"iskl.edu.my_o4tp15b8o4b9iaj1t69b93s62o@group.calendar.google.com";
	//	PrintWriter pw = resp.getWriter();
		
		String eventName 	= req.getParameter("eventName");
		String startDate 	= req.getParameter("startDate");
		String endDate 		= req.getParameter("endDate");
		String startTime	= req.getParameter("startTime");
		String endTime		= req.getParameter("endTime");
		String campus		= req.getParameter("campus");
		String[] dayAll 	= req.getParameterValues("day");
		
/*		pw.println("Your days");
		for (int k = 0; k < dayAll.length; k++)
			pw.print(dayAll[k] + " ");
		pw.println();
*/		
			
		String calendarId = null;
		if 		(campus.equals("isklMelewati")) {
			calendarId = melawatiId;
		}
		else if	(campus.equals("isklAmpang")) 	{
			calendarId = ampangId;
		}
		
		DateTime timeMin 	= new DateTime(startDate+"T00:00:00+08:00");
		DateTime timeMax 	= new DateTime(endDate	+"T00:00:00+08:00");
		
		String pageToken = null;
		do {
			  Events events = cal.events().list(calendarId)
					  .setPageToken(pageToken)
					  .setSingleEvents(true)
					  .setTimeMin(timeMin)
					  .setTimeMax(timeMax)
					  .execute();
			  List<Event> items = events.getItems();
			  for (Event event : items) {					//go through all days
				  for (int c = 0; c <dayAll.length; c++) {	//check if it is a day we want to schedule
					  if (event.getSummary().contains(dayAll[c])) {
						  String date = event.getStart().getDate().toString();
						  String startStr 	= date + "T" + startTime + ":00+08:00";
						  String endStr		= date + "T" + endTime	 + ":00+08:00";
						  
						  EventDateTime startEDT = new EventDateTime()
								  .setDateTime(new DateTime(startStr));
						  EventDateTime endEDT = new EventDateTime()
								  .setDateTime(new DateTime(endStr));
						  
						  Event myEvent = new Event()
								  .setSummary(eventName)
								  .setStart(startEDT)
								  .setEnd(endEDT);
						  cal.events().insert("primary", myEvent).execute();
					  }
				  }
			  }
			  pageToken = events.getNextPageToken();
		}	while (pageToken != null);
		
		
/*		PrintWriter pw = resp.getWriter();
		pw.println(	eventName 	+ "" + 
					startDate 	+ "" + 
					endDate 	+ "" + 
					startTime 	+ "" + 
					endTime		+ "" + 
					campus 		+ "" + 
					day 		+ "" + 
					calendarId		   );
*/		
		
		
		resp.sendRedirect("eventCreator.jsp");
		
	}
	
}