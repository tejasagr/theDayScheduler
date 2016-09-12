<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="images/TDS_Icon.ico" />
		<title>eventDeleter</title>
		<link rel="stylesheet" type="text/css" href="main.css">
		</head>
	<body>
		<div class="website">
		
			<div class="header">
				<img src="images/TDS_Icon.png" alt="TDS Icon" class="logo"/>
				<h1> theDayScheduler </h1>
			</div> <!--header-->
	
			<table class="navbar">
				<tr>
					<td class="navbut">			<a href="home.jsp">			<div class="navhover">	home 		</div></a></td>
					<td class="navbut">			<a href="eventCreator.jsp">	<div class="navhover">	eventCreator</div></a></td>
					<td class="navbut current">	<a href="eventDeleter.jsp">	<div class="navhover">	eventDeleter</div></a></td>
					<td class="navbut">			<a href="incomplete.html">	<div class="navhover"> 	help		</div></a></td>
				</tr>
			</table>

			<form action="EventDeletionTimeBound" method="post" class="eventCreator">
				<h2> Delete Recurring Events Between Two Dates </h2>
<br>			Event Name: <input type="text" name="eventName">	&emsp;&emsp;&emsp;
				Start Date: <input type="date" name="startDate"> 	&emsp;
				End Date:   <input type="date" name="endDate"> 		&emsp;
				<br>
				<input type="submit" value="Delete Event(s)">
			</form>
			
			<form action="EventDeletionUnbound" method="post" class="eventCreator">
				<h2> Delete <span class="italic">All</span> Recurring Events </h2>
<br>			Event Name: <input type="text" name="eventName">
				<br>
				<input type="submit" value="Delete Event(s)">
			</form>
			
			<br>
			<div class="wrapper">
				<iframe
					src="https://calendar.google.com/calendar/embed?src=${usernameURL}"
					style="border: 0"
					width="900"
					height="600"
					frameborder="0"
					scrolling="no"
				></iframe>
			</div>
				
			<div class="footer">
				<div class="footrow1">	<a href="http://www.google.com/calendar"> 	Google Calendar		</a> </div>
				<div class="footrow1"> 	<a href="incomplete.html"> 					Contact Us 			</a> </div>
				<div class="footrow1"> 	<a href="incomplete.html"> 					GitHub Files 		</a> </div>

				<div class="clearfloat"> </div>

				<div class="footrow2"> Created By: Tejas Agrawal &nbsp; | &nbsp; Last Updated: 12 September 2016 </div>

			</div> <!--footer-->	
						
		</div> <!-- website -->	
	</body>
</html>