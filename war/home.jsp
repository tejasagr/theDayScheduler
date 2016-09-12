<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/TDS_Icon.ico" />
<title>Home</title>
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
				<td class="navbut current">	<a href="home.jsp">			<div class="navhover">	home 		</div></a></td>
				<td class="navbut">			<a href="eventCreator.jsp">	<div class="navhover">	eventCreator</div></a></td>
				<td class="navbut">			<a href="eventDeleter.jsp">	<div class="navhover">	eventDeleter</div></a></td>
				<td class="navbut">			<a href="incomplete.html">	<div class="navhover"> 	help		</div></a></td>
			</tr>
		</table>

		<div class="floatleft sidebar">
			<h2 class="textcenter">&emsp;&emsp;&nbsp;QUICK TOOLS </h2>
			
			<form action="EventCreation" method="post" class="quickform">
				<h3> Create a Single Event </h3>
<br>			Your event name: <input type="text" name="eventName">
<br>			Start: <input type="datetime-local" name="start">
<br>			End:   <input type="datetime-local" name="end">
<br>			<input type="submit" value="Create Event">
			</form>
<br>			
			<form action="EventDeletion" method="post" class="quickform">
				<h3> Delete A Single Event </h3>
<br>			Your event name: 	<input type="text" name="eventName">
<br>			Event date:			<input type="date" name="eventDate">
<br>			<input type="submit" value="Delete Event">
			</form>
			
			<span class="note">
				Go to the eventCreator or the eventDeleter for more advanced options.
			</span>
			
		</div>
	
		<div class="wrapper">
				<iframe class="calendar"
					src="https://calendar.google.com/calendar/embed?src=${usernameURL}"
					style="border: 0"
					width="620"
					height="420"
					frameborder="0"
					scrolling="no"
				></iframe>
			
		</div> <!-- calendar -->
		<br>
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