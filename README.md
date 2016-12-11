# An Asynchronous RMI String Comparison Service
An asynchronous, remote string comparison service built using Java and Java  EE. This was a project created for the Distributed Systems module in the Software Development course in GMIT.

## Project outline
The project looks at the client side in which the user enters two strings and an algorithm by which to calculate the distance between them. The client-side task is put into a queue which is being checked by a thread every few seconds. The thread sends the task up to a remote server which has a service called comparisonService. This service gets the distance and returns it to the client.

## Running it
1. Clone the project
```bash
git clone https://github.com/Niksdope/Asynchronous-RMI-Service/
```

2. Run the server
```
java -cp ./string-service.jar ie.gmit.sw.RemoteServer
```

3. Place the comparator.war inside the apache-tomcat../webapps folder

4. Start the tomcat server by running the startup.exe file inside the bin folder within apache-tomcat directory

5. Connect to the client project on your browser
```http
localhost:8080/comparator
```

## References
Technology | Link 
--- | ---
Apache Tomcat 9 | https://tomcat.apache.org/download-90.cgi 
Apache commons-lang | https://commons.apache.org/proper/commons-lang/download_lang.cgi 
