
STEPS TO SET UP AN SPRING WORKSPACE:
====================================

1. Create a Legacy project Using Spring MVC Template
2. This will create the necessary folder strcuture. Make sure you delete the following files from the created workspace
      1. web.xml (inside WEB-INF) folder
      2. folder called spring under the WEB-INF\Spring
3. Copy over from pom.xml (laps app is reference)
      1. All the properties 
      2. All the dependencies
      3. All the plug-ins      
4. Files and Folders to copy (laps app is reference)
	  1. The two initializer files from init folder
	      In WebAppConfig.java
	      Change the annotation accordingly:
	      @ComponentScan(basePackages="edu.iss.laps")
	  2. application.properties and i18n folder from the src/resources folder 
	      In the application.properties file change the following properties accordingly
	      db.url=jdbc:mysql://localhost:3306/sa44
		  db.username=root
		  db.password=password
		  entitymanager.packages.to.scan=edu.iss.laps.model      
	  3. Copy to WEB-INF folder
	      1. copy folder decorators (alter if needed)
	      2. copy the decorators.xml
	      3. check if you have views folder and a jsp file called home is present to test your home controller
	   4. Copy to web-app and change where necessary
	      1. css (style sheet)
	      2. image (for logo, glossy buttons etc)
	      3. js (for all your client side stuff)
	
5. The Build Process
    1. Select Maven Menu > Update Porject (to bring in needed libraries)
    2. Run As > Maven install (to build the plugin once)
    3. Make sure Menu Project>Clean and Build has worked
    4. Run As >Maven Clean
    5. Run As> Run on server
    
    [Check for errors on console and troubleshoot]
    
 
    
6. When the build is successful open a browser and put the url to be tested.

   In this case it is 
   http://localhost:8080/laps/home/print
   
   Normally it is http://localhost:8080/ followed by the <artifact id> you have created in the pom file
   
   
 
   
   Hope you can reconstruct the system.
     Cheers.
    
    
	    
	      
	      