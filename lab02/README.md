# LAB02: More Java, Eclipse, ANT and the first example of a service with Axis2


**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-2 "Permalink to LAB02: More Java, Eclipse, ANT and Firs example of a service with Axis2")**

How can we automate compiling and executing java programs? What kind of technology is behind this automation? What do we mean, in practical terms, when we speak of a "service"?.&nbsp;In this session, we will provide some answers to these questions. We will briefly introduce how will we work with Eclipse during sessions (although students are free of working with the IDE of their own choosing). We will see an example of what kind of technology is behind the automation that IDEs provide (ANT), and in turn, what language fuels that technology, which is one of the cornerstones of the SOA world (XML). The lesson will finalize with a first example of a service, using Axis2. 

## Guiding Notes

The guiding notes below are a summarized version of what is already on the slides.&nbsp;

* Start by pulling updates from the github repository. In your terminals, execute the following: 

    ```
    $ git fetch upstream
    $ git merge upstream/master
    ```

### Using Eclipse (15 min)

* Create a new *Java Project* in eclipse (File -> New -> Java Project)
* Unmark "Use default location" and select the folder where the source code for this lab is (in your computer). Eclipse should detect the source code inside and add it to the project. 
* Take some minutes to explore the solutions to the exercises of *Lab 01* as they appear in the new version of the HealthProfileReader. Look at:
    * Person.java
    * HealthProfileReader.java
* Run the HealthProfileReader using Eclipse (see the slides to learn how to do it)

### ANT: What if there is no IDE? (30 min)

* When implementing services, you will find yourself often at work with nothing else than a terminal, executing programs from a command line. 
* The example so far (the HealthProfileReader) is simple, but what if you have hundreds of classes and packages? **How to provide similar automation capabilities for compilation and packaging, as the IDE provides?**. That's where ANT comes in.
* ANT is a Java-based build tool that automates repetitive tasks (e.g. compiling source code, running tests, generating documentation) typically, without a graphical user interface directly from the command line.
* Take a look to the *ANT build script* we have in the HealthProfileReader example (the file **build.xml**). 
* ANT scripst define:
    * **Ant Project:** a collection of named targets. Each build file contains one project.
    * **Ant Target:** a fixed series of ant tasks in a specified order that can depend on other named targets.
    * **Ant Task:** something that ant can execute jobs, such as compile, create jars, copy.
    * **Ant properties:** immutable constants set once and used through the whole scritps
* Hands on. Follow these steps while looking at the build.xml in parallel in order to understand what does it take to execute each target.  

* From the command line run:
    
    ```
    ant compile
    ```
    
* We have just executed the targets **init** and **compile**. Now the build folder has some compiled classes that can be execute it. 
* The **compile** target has a **depends** attribute equal to **init**, which is shy this target got executed first
* You can also execute things. Try:

    ```
    ant execute-hprofile
    ```
    
* Finally, classes can all be packaged in one using jar files. Try it like this.

    ```
    ant archive
    ```
    
* In the build folder, there should be a jar file now (project.jar), whose main class is pointed to the *HealthProfileReader*. To execute it, run:

    ```
    java -jar build/project.jar [parameters of the program]
    ```
    
* How about command-line arguments?. Take a look to the target *execute-hprofile-args*. Now run it in the following way: 
    
    ```
    ant execute-hprofile-args-Dcommand=createNewPerson -Did=45 -Dname=Jane -Dlastname=Doe -Dbirthdate="1987-09-23"
    ```

### First Service example with Axis2 (45 min)
* Axis2 is a **web service** engine that implements both client and server sides of web services (originally, only SOAP; from v2 also REST). 
* It is a **middleware** that handles the generation/sending/receiving/dispatching of SOAP messages. It allows you to expose simple Java Classes with its methods and other web applications as web services. 
* First of all, make sure **Tomcat** is running and **Axis2** war is deployed in Tomcat. 
* Once Tomcat is running, follow this link: http://localhost:8080/axis2/services/Version?method=getVersion
* If it shows you something like this, we are OK and you have just **invoked a web service for the first time**.
```xml
    <ns:getVersionResponse xmlns:ns="http://axisversion.sample">
        <ns:return>Hi - the Axis2 version is 1.6.2</ns:return>
    </ns:getVersionResponse>
```
* Now, hit the link "administration" in the axis2 home. The default user/password is admin/axis2. From here, you can deploy/undeploy other services. 

* **Example:**
    * From the "Example" folder of this Lab, create a new project with the Example "axis2-quickstart"
    * Check the "service.xml" definition that it will be used to create a WSDL file describing a SOAP endpoint service based on the StockQuoteService java class. This java class takes the stock code of a company (e.g., IBM) and returns its stocks value. 
```
	open resources/META-INF/services.xml
```
* You should see this: 
```
    <service name="StockQuoteService" <!-- The name of the service -->
		scope="application"
		targetNamespace="http://quickstart.samples/"
		<!-- Scope and nameSpace must be later matched inthe build.xml -->
	>
		<!-- a description of what the service does -->
		<description>Stock Quote Service</description>
		
		<!-- the classes that will process incoming messages to the service -->
		<!-- we are using standar message receivers already bundled with axis2 -->
		<messageReceivers>
			<messageReceiver
				mep="http://www.w3.org/2004/08/wsdl/in-only"
				class="org.apache.axis2.rpc.receivers.RPCInOnlyMessageReceiver"
			/>
			<messageReceiver
				mep="http://www.w3.org/2004/08/wsdl/in-out"
				class="org.apache.axis2.rpc.receivers.RPCMessageReceiver"
			/>
		</messageReceivers>
		
		<!-- definition of the xml schema used by the service -->
		<schema schemaNamespace="http://quickstart.samples/xsd"/>
		
		<!-- specification of what Java class is being exposed as a service -->
		<parameter
			name="ServiceClass">samples.quickstart.service.pojo.StockQuoteService
		</parameter>
	</service>
```

* This is the definition of the StockQuoteService. It defines: 
    * one "service" with the name *StockQuoteService* 
    * what java class it is going to be used as a *ServiceClass* (under params), exposing all of its public methods as services (in this case, the *samples.quickstart.service.pojo.StockQuoteService* class).  
    * The other important parameters are *targetNamespace* and *schemaNamespace*, which later has to be reused in the ant build file (we will get there). 
    * What receivers will be used to process incoming messages (the standard axis2 receivers org.apache.axis2.rpc.receivers).  

* To create the service and make it available, we need to do two things: 
    * Create a WSDL (Web Service Description Language) file that describes the SOAP (Simple Object Access Protocol) endpoint. 
    * Package all the necessary classes in a convenient way, so that it can be deployed in Axis2. 
    * We achieve this by using an ANT script. 
* Open the build.xml and explore it. It includes the following axis2 targets:
    * *generate.wsdl*: This target generates the StockQuoteService.wsdl file in the build folder, using the java2wsdl command. Make sure that *targetNamespace* and *schemaTargetNamespace* is same as in service.xml file. See how we first define the command called java2wsdl, implemented by the class org.apache.ws.java2wsdl.Java2WSDLTask. This class is in the axis2 classpath.
    * *generate.service*: This target generates the axis2 archive (which is nothing but a jar actually) in the build folder under the name *StockQuoteService.aar*, which includes the *services.xml* and the compiled classes. You can use this  *.aar file to deploy the service through axis2 webapp. The same target is followed by a simple copy of the package (StockQuoteService.aar) to the folder AXIS2_HOME/repository/services (if you use Axis2 standalone) or to the AXIS2_HOME_TOMCAT/WEB-INF/services, in our case.  This is the deployment. We can also do this manually if we wish.  
    * *generate.client*: This target generates the client side classes. Make sure you run this after executing generate.wsdl so the MyService.wsdl file is present in the build folder.

**Observation:** in the build.xml, replace the properties AXIS2_HOME and AXIS2_TOMCAT_HOME to make it point to your local installations. If you are didn't download the binary version of axis2, remove all the references to AXIS2_HOME. 

Now, generate the WSDL file and generate the packaged version of the service doing the following
	
	ant generate.wsdl
	ant generate.service

And finally, we can deploy it by simply copying the *StockQuoteService.aar* in the services directory of axis2. The services directory is the following for our case. 
	
	$TOMCAT_HOME/webapps/axis2/WEB-INF/services/ 

You can now see that the StockQuoteServices is in the list of available services by going to 
	
	http://localhost:8080/axis2/axis2-admin/listService

If you click on the link to the service, you will see the wsdl specification of this SOAP endpoint

	http://localhost:8080/axis2/services/StockQuoteService?wsdl
	
And to call the specific service, you just need to ask for the service getPrice (which is a method in the StockQuoteService class definition) with the proper parameter as follows

	http://localhost:8080/axis2/services/StockQuoteService/getPrice?symbol=IBM
	
The answer is a soap message as follows: 
	
	<ns:getPriceResponse xmlns:ns="http://quickstart.samples/xsd">
		<ns:return>42.0</ns:return>
	</ns:getPriceResponse>
	
The StockQuoteService class has also an update operation which if called, updates the stock value for a company symbol. 

	http://localhost:8080/axis2/services/StockQuoteService/update?symbol=IBM&price=150
	
Calling the getPrice service now will give

	<ns:getPriceResponse xmlns:ns="http://quickstart.samples/xsd">
		<ns:return>150</ns:return>
	</ns:getPriceResponse>

Now, your turn. Do the exercises!!

### Exercise 1
* Remember, put your code in your local workspace ('myworkspace'), just to avoid future conflicts if things change in the official repository. For this exercise, create a copy of the HealthProfileExample as a starting point. 
* Add the method *getBMI* to the HealthProfile class, which returns the BMI of the person according to the following formula: **mass(kg) / (heigh(m))^2**
* Add the option to call this method in the HealthProfileReader program
* In the build.xml, add and execution target for each of the methods in our main program (e.g., "createNewPerson", "getBMI", etc.) 
* Suggestions: 
    * Make your directory structure as follow
       * Main Directory (named after the project)
           * src: source files 
           * build: destination of built files
           * classes: java compiled classes  
           * dist: destination of packaged files (e.g. *.jar).
    * Make build.xml file with the following targets
        * init: creates the build and dist directories
        * compile: compiles your project and put the classes in build/classes
        * archive: creates a distributable jar file in 'dist'
        * execute: runs the project with three integer arguments
        * clean: goes back to the original state  

Try it! 

### Exercise 2

* Expose the HealthProfileReader through an axis2 web service

## Additional notes

### Why do we need apache ant? 

Can't we just use an IDE and forget about?. Yes, we can use an IDE and forget about it. The purpose for this lab, however, is to understand workflow that we follow when creating first a java program and then making it available through a service: (the generation of the service specification, the compilation of the java program, how it all works together, etc.) 

### Other suggested tutorials/articles

* [Java Introduction (if you have never seen it before)][6]
* [ANT extended tutorial][7]
* If you have issues with the installation of any related tool read the Readme of lab00. 

[1]: https://drive.google.com/file/d/0B7ShzcEnCJFNVi1LWERhbVFoQ3c/edit?usp=sharing
[2]: https://github.com/cdparra/introsde/tree/master/lab02/Example/
[3]: https://drive.google.com/file/d/0B7ShzcEnCJFNNWttdGRvZmpIZUE/edit?usp=sharing
[4]: http://tomcat.apache.org/
[5]: http://axis.apache.org/axis2/java/core/download.cgi
[6]: http://www.vogella.com/articles/JavaIntroduction/article.html#firstjava
[7]: http://www.vogella.com/articles/ApacheAnt/article.html#antoverview_definition
