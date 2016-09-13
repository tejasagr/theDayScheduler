# theDayScheduler

The welcome file of the web application is Authentication.java.
This file checks if the user has given permission for the application to manipulate their Calendar information.
If the user has, the application then forwards the user to the homepage, home.jsp.

If the user has not given permission then the file get’s the user’s permission via AuthenticationCallback.java and then 
redirects to the homepage.

The homepage, home.jsp, allows the user to view their calendar or use one of two quick tools. 
One is to create a singular event and one is to delete a singular event. 
The form data for these tools is processed by EventCreation.java and EventDeletion.java respectively.

The user can also choose to go to the eventCreator.jsp or eventDeleter.jsp pages for more advanced event management. 
The eventCreator lets users create recurring events between particular dates based on ISKL’s day schedule. 
The eventDeleter, let’s users delete events in bulk based on the event’s name. 
These tools are processed by the files EventCreationAdv.java, EventDeletionTimeBound.java and EventDeletionUnbound.java.

Methods.java is a helper file, that contains useful methods used by other Java files in the program.

main.css is the external stylesheet used by the JSP files.
