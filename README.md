Synopsis:

Utility for consuming GlobalAnt Api's

Code Example:

-View files in any particular directory
-View the health check of the applicable
-Filter out users

Motivation:

It is a starter for making GlobalAnt Api's available for use

Installation:

-Install mongodb with database name as "global"
-Download the source and use the unit testing class(GlobalControllerTest.java) to know examples of api usage. It also has methods to make entry in to your Users collection instance.
-Run the attached jar
-Access: http://localhost:8080/login.html
-Use the unit testing api to insert user name and password for login


API Reference:

http://localhost:8080/user
http://localhost:8080/login
http://localhost:8080?directory=c:\
http://localhost:8080\health
http://localhost:8080?firstname=a&lastname=b&profession=c&sex=d&age=30&city=sfo

Tests:

GlobalControllerTest.java

Contributors:


License:
