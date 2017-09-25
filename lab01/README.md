# LAB01: Introduction to the Lab and brief Java review


**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-1 "Permalink to LAB01: Introduction to the Lab and brief Java review")**

What's an example of a Service-Oriented Architecture? How does the architecture of the web look like? Where in that architecture is our work going to be centered during these lab sessions? What tools and technologies are we going to use to implement our services?.&nbsp;In this session, we will provide some answers to these questions, in a very brief manner, before passing to the hands-on practice lab in which we will focus on&nbsp;the main characteristics of the programming language we will use during the lab:&nbsp;Java&nbsp;&nbsp;

## Guiding Notes

The guiding notes below are a summarized version of what is already on the slides.&nbsp;

* Start by browsing the introductory first slides of the lesson, introducing the key components in the&nbsp;Architecture of the Web. These slides will give you an idea of where will be placed the focus of our work in lab sessions and what technologies we will be using.&nbsp;&nbsp;
* Make sure you have downloaded and installed Java in your system&nbsp;. In your terminal/cmd windows, execute
```
$ java -version
```
* If the the binary does not exists, follow [these instructions][15] to download and install (officially, we will use Java 1.8)

* Revise the slides about&nbsp;Programming in Java, which will give you a very basic&nbsp;overview of the Java programming language.
* Examine, compile and execute the classes of the Examples.
* Try it out!
```
# Compile
$ javac examples\HealthProfileReader.java
```
```
# Execute the program
$ java examples.HealthProfileReader Mad Max
Mad Max is not in the database
$ java examples.HealthProfileReader John Doe
John Doe's health profile is: Height=1.72, Weight=85.5
```

* Exercises
    * In Person.java:
        1. Add a Long attribute to store the person's personId (including accessor methods)
        2. Add a String attribute to store the person's birthdate (including accessor methods, use as date format "YYYY-mm-dd")
        3. Update the constructors accordingly to include these attributes
        4. Update the empty constructor to set the personId to a random number between 1 and 9999.
        5. [optional/advanced] Update the empty constructor to set the birthdate string to a random date between 1950 and the current Year. 
    * In HealthProfileReader.java:
        1. Change the database so that the key for each person is the personId.
        2. Add the static method createPerson(Long personId, String firstname, String lastname, String birthdate), which creates a new person and adds it to the Database.
        3. Add the static method displayHealthProfile(Long personId) , which should lookup the person in the database identified by personId and then print the related healthProfile.
        4. Add the static method updateHealthProfile(Long personId, Double height, Double weight) , which should lookup the person in the database identified by personId and update the related healthProfile with the indicated height and weight.
        5. Replace the main program with a version that takes the method name from command-line arguments and to executes it: 
           ```java HealthProfileReader method_name method_parameters```
           ```Example: java HealthProfileReader displayHealthProfile 1```
     * More Exercises in the slides.
* More about Programming in Java
    * [Java Cheat Sheet][16]
    * [Java Introductory Tutorial][17]
    * [Other Java Tutorials][18]
    * [Recommended book on learning programming with Java][19]

## Homework: CheckList for NEXT session

### For the Labs we still need the following applications:

* Java (version 1.8) - [download][1]
* Git (version 2.14) - [download][2]
* Git GUI Client [Check List][3] (recommended: [SourceTree][4])
* Text Editor of your preferences (recommended: [notepad++ 7.5.1][5];  [Sublime Text 3][6])
* Console (Command Prompt; Linux Console)
* IDE - Eclipse for Java EE Developers (Version 4.7 - Oxygen) - [download][7]
	* Install from the Eclipse Market (if not installed): Apache IvyDE - [website][8]
	* Install from the Eclipse Market (if not installed): Maven Integration for Eclipse - [website][9]
* Apache Tomcat (version 8.5) - [download][10]
* Apache Maven (version 3.5) - [download][11]
* Postman (version 5.2)- [download][12]

## Configure your Github account and repository:
* If you are not familiar with git, follow the tutorial at [Try github][7]&nbsp;(15 minutes)&nbsp;
* Create your account in [github][13] (if you haven't)
* Fork the lab repository to your account (i.e., create a copy of the repository in your github account)
    * Go to https://github.com/mdrodas/introsde.
    * Click on "Fork" in the upper right corner of the page.
    
* Clone your fork in your local machine (i.e., download your copy to your machine)
```
    git clone https://github.com/YOUR_USERNAME/introsde.git
```
* Add the original repository as a remote, in order to Fetch future updates on the original repository (in case there is any)
```
    git remote add upstream https://github.com/mdrodas/introsde.git
```

## Learn the Lab session workflow:

1. Before each session of the Lab, pull the changes from the original repository (i.e., update your local version with changes from the original).
```
    git fetch upstream; 
    git merge upstream/master;
```
 * Alternatively, execute a pull (does fetch and merge).
```
    git pull upstream master; 
```
2. Put your personal work in your own folder. We will not push changes of our personal workspaces to github.
3. Then, at the end of each session of work, add your changes to your repository stash:
```
     git add myworkspace
```
4. Commit your changes to your local repository
```
     git commit -m "my work for labXX"
```
5. Push your changes to your github repository
```
     git push master
```

## Additional Configurations

### Setting up PATH variables;

We need to create HOME variables and add the bin folder to the PATH for the following applications (if it was not already added):
* Java (JAVA_HOME and bin folder added to PATH).
* Ant (ANT_HOME and bin folder added to PATH).
* Maven (MAVEN_HOME and bin folder added to PATH).
* Tomcat (CATALINA_HOME and bin folder added to PATH).

#### In windows:;

* Control Panel -&gt; System -&gt; Advanced tab -&gt; Environment Variables -&gt; System Variables
* Edit the 'path' variable and add the location of your bin folder into the existing value. In versions of windows lower than windows 10 the path is concatenated with semicolons.
```PATH = C:\binfolder\;C:\Program Files\Java\jdk1.8\bin\;```
#### In Unix/Linux/Mac:;
* Add the following to the file named .bash_profile (or .profile), located in your home directory
```export PATH=$PATH:/path/to/your/binary/folder/```

### Installing Tomcat 

* First, install tomcat. [tomcat website][10]. 
* Download the zip version of the latest version of Tomcat application manager. 
* Unzip it somewhere (e.g. /opt or C:\) 
* Set environment variables: 
``` 
# if you are in unix/linux/mac or you are using msysgit from windows
export CATALINA_HOME=/opt/apache-tomcat-8.5
# windows (better use the Windows Environment configuration)
set CATALINA_HOME=C:\apache-tomcat-8.5
```
* Start the server (to make things easier, add also these binaries to your PATH)
```
# if you are in unix/linux/mac or you are using msysgit from windows
$CATALINA_HOME/bin/startup.sh
# windows
%CATALINA_HOME%\bin\startup.bat
```
* [CHECKPOINT 1] Now, go to http://localhost:8080/ and if you see the apache tomcat page, you are fine.

### Installing Axis2
 
* Download [axis2][14]. 
* For the installation you should follow the [axis2 Installation Guide][15]. The [guide][15] shows two methods of installation:
	
    1. Installing Axis2 as a Standalone Server using the Standard Binary Distribution.
    2. (Recommended) Installing Axis in a Servlet Container (Using the axis2.war).
	
The following are a summary of the You can either download the war package directly, or download the binary distribution, unzip it somewhere and then build the war. Let's do the second. For this lab session, I downloaded the axis2-1.7.6-bin.zip distribution. 
```
    unzip axis2-1.7.6-bin.zip  
    mv axis2-1.7.6 /opt
```

* Now, you need to enter the webapp folder in the axis home and create the package war of axis2. How? **Using ant** ;-)
```
    cd /opt/axis2-1.7.6/webapp
    ant create.war
    ...
    create.war:
        [war] Building war: /opt/axis2-1.7.6/dist/axis2.war
    ...
```
* Deploy the war in tomcat. For this, you need to access the tomcat manager in your browser (http://localhost:8080/manager/html) 
* Check the configuration file $TOMCAT_HOME/conf/tomcat-users.xml to enable the administration of tomcat (add or uncomment the following line, if it is there)
```
    <user username="admin" password="whateverpasswordyouwantiuseadmin" roles="admin-gui,manager-gui"/>
```
* Now, you can access the manager in  http://localhost:8080/manager/html and deploy axis war. 
* Go to the section Deploy -> WAR file to deploy -> Choose File and select the war you have just created.  
* Now that it is deployed, you can open axis2 
```
    Follow this link http://localhost:8080/axis2. 
```

* Should show you a page with 3 links: services, validate, administration. Open validate. 
```
    Follow the link http://localhost:8080/axis2/axis2-web/HappyAxis.jsp
```

* [CHECKPOINT 2] It should should show you a HappyAxis page with the list of needed libraries and their status (if they are or not in your system).  If the Happy Axis page is coming with GREEN color then it means that axis2 is successfully deployed.


[1]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[2]: https://git-scm.com/downloads
[3]: https://git-scm.com/downloads/guis
[4]: https://www.sourcetreeapp.com/
[5]: https://notepad-plus-plus.org/download/v7.5.1.html
[6]: https://www.sublimetext.com/3
[7]: http://www.eclipse.org/downloads/eclipse-packages/
[8]: http://marketplace.eclipse.org/content/apache-ivyde%E2%84%A2
[9]: http://marketplace.eclipse.org/content/maven-integration-eclipse-luna
[10]: https://tomcat.apache.org/download-80.cgi
[11]: https://maven.apache.org/download.cgi
[12]: https://www.getpostman.com/
[13]: http://github.com
[14]: http://axis.apache.org/axis2/java/core/download.html
[15]: http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

[16]: http://introcs.cs.princeton.edu/java/11cheatsheet/
[17]: http://www.vogella.com/tutorials/JavaIntroduction/article.html#firstjava
[18]: http://www.mkyong.com/all-tutorials-on-mkyong-com/
[19]: http://introcs.cs.princeton.edu/java/home/