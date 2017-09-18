# LAB00: Labs Requirements

**Introduction to Service Design and Engineering | University of Trento**

What's an example of a Service-Oriented Architecture? How does the architecture of the web look like? Where in that architecture is our work going to be centered during these lab sessions? What tools and technologies are we going to use to implement our services?.&nbsp;In this session, we will provide some answers to these questions, in a very brief manner, before passing to the hands-on practice lab in which we will focus on&nbsp;the main characteristics of the programming language we will use during the lab:&nbsp;Java&nbsp;&nbsp;


## **Needed Applications**

For the Labs we need the following applications:
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

* Create your account in [Github][13] (if you haven't)
* Fork the lab repository to your account (i.e., create a copy of the repository in your github account)
    ```
    Go to https://github.com/mdrodas/introsde.
    Click on "Fork" in the upper right corner of the page
    ```
* Clone your fork in your local machine (i.e., download your copy to your machine)
    ```
    git clone https://github.com/YOUR_USERNAME/introsde.git
    ```
* Add the original repository as a remote, in order to Fetch future updates on the original repository (in case there is any)
    ```
    git remote add upstream https://github.com/mdrodas/introsde.git
    ```.

	
* Learn the&nbsp;Lab session workflow:
* Before each session of the Lab, pull the changes from the original repository (i.e., update your local version with changes from the original)&nbsp;
    ```
    git fetch upstream &nbsp; .
    git merge upstream/master
    ```
* Put your personal work in your own folder. We will not push changes of our personal workspaces to github.&nbsp;
    * Then,&nbsp;at the end of each session of work, add your changes to your repository stash
           ```
            git add myworkspace
            ```
    * Commit your changes to your local repository
            ```
            git commit -m "my work for labXX"
            ```
    * Push your changes to your github repository
            ```
            git push master
            ```

## Additional notes

### Setting up PATH variables:&nbsp;

#### In windows:&nbsp;

* Control Panel -&gt; System -&gt; Advanced tab -&gt; Environment Variables -&gt; System Variables&nbsp;
* Edit the 'path' variable and append the location of your bin folder onto the existing value (separated by a semicolon).&nbsp;

```PATH = ;C:\Program Files\Java\jdk1.6.0_02in\;```

* Add the following to the file named .bash_profile (or .profile), located in your home directory

```export PATH=$PATH:/path/to/your/binary/folder/```

Do the same for all other binaries you will need (unless the binary folder is already added automatically) (Ant, Tomcat, etc.)&nbsp;

### Installing Tomcat and Axis2 
* First, install tomcat. Go to apache [tomcat website][10]. 
* Download the zip version of the latest version of Tomcat application manager. 
* Unzip it somewhere (e.g. /opt or C:\) 
* Set environment variables: 
```
    # if you are in unix/linux/mac or you are using msysgit from windows
    export CATALINA_HOME=/opt/apache-tomcat-8.5
 
    # windows
    set CATALINA_HOME=C:\apache-tomcat-8.5
```

**Observation:** for those using msysgit in windows, beware that the "\" is a escape character, so you can use either *C:\\apache-tomcat-8.5* or */C/apache-tomcat-8.5* 

* Start the server (to make things easier, add also these binaries to your PATH
```
    # if you are in unix/linux/mac or you are using msysgit from windows
    $CATALINA_HOME/bin/startup.sh

    # windows
    %CATALINA_HOME%\bin\startup.bat
```
* Now, go to http://localhost:8080/ and if you see the apache tomcat cat, you are fine. 
* **Next step:** donwload and install [axis2][14]. You can either download the war package directly, or download the binary distribution, unzip it somewhere and then build the war. Let's do the second. For this lab session, I downloaded the axis2-1.7.6-bin.zip distribution. 
```
    unzip axis2-1.7.6-bin.zip  
    mv axis2-1.7.6 /opt
```
**Observation:** there might be problems with the classpath if you are using only the war distribution. One way of checking exactly what you have in the class path is running *ant SOME_EXISTING_TARGET -diagnostics*. To avoid potential problems, use the binary distribution. 

* Now, you need to enter the weabpp folder in the axis home and create the package war of axis2. How? **Using ant** ;-)
```
    cd /opt/axis2-1.7.6/webapp
    ant create.war
    ...
    create.war:
        [war] Building war: /opt/axis2-1.7.6/dist/axis2.war
    ...
```
* Deploy the war in tomcat. For this, you need to access the tomcat manager in your browser (http://localhost:8080/manager/html) 
* By default, Tomcat does not enable admin or manager access. To enable it, you will have to edit the $TOMCAT_HOME/conf/tomcat-users.xml manually by adding the following (or uncommenting if it is there)
```
    <user username="admin" password="whateverpasswordyouwantiuseadmin" roles="manager-gui,tomcat"/>
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

* It should should show you a HappyAxis page with the list of needed libraries and their status (if they are or not in your system).  If the Happy Axis page is coming with GREEN color then it means that axis2 is successfully deployed. 



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
