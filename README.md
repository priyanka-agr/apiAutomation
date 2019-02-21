# Backend APIs
#### MAVEN Project

## What's inside:
```xml
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/junit/junit -->
        </dependency>
```
 
```xml
<dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>x.xx</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
```

```xml
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>x.xx</version> 
             <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->       
        </dependency> 
```    
```xml
<dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-chrome-driver</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver -->
        </dependency>
```    
```xml
<dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>x.xx</version>
            <scope>test</scope>
            <!-- https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
        </dependency>
```
```xml
<dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-path</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/com.jayway.jsonpath/json-path -->
        </dependency>
```
       
```xml
<dependency>
            <groupId>com.vaadin.external.google</groupId>
            <artifactId>android-json</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/com.vaadin.external.google/android-json -->
        </dependency>
```       
```xml
<dependency>
            <groupId>com.google.apis</groupId>
            <artifactId>google-api-services-gmail</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/com.google.apis/google-api-services-gmail -->
        </dependency>
```
```xml
<dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
        </dependency>
```

```xml
        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp -->
        </dependency>
```

 ```xml
        <dependency>
            <groupId>com.google.api-client</groupId>
            <artifactId>google-api-client</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/com.google.api-client/google-api-client -->
        </dependency>
```
        
```xml
<dependency>
            <groupId>com.google.oauth-client</groupId>
            <artifactId>google-oauth-client-jetty</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client -->
        </dependency>
```

```xml
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>x.xx</version>
            <!-- https://mvnrepository.com/artifact/com.github.javafaker/javafaker -->
        </dependency>
```

### Important Note(s):
```text
You should place rest-assured before the JUnit dependency declaration in your pom.xml / build.gradle in order to make sure that the correct version of Hamcrest is used.
```

### Setup dependencies:
```bash
brew cask install java8
```

```git
nano ~/.bash_profile
```
```text
Paste the below :
```
```bash
export JAVA_HOME=$(/usr/libexec/java_home)
export PATH=$JAVA_HOME/bin:$PATH
SAVE AND EXIT TERMINAL
```
```text
TODO @Priyanka take a look and update dependencies
```

### Check that JAVA_HOME and ANDROID_HOME is set:
```bash
$echo $JAVA_HOME
```

### Clone the project:
* Clone in your Workspace & change the current working directory to the local project
```bash
git clone https://github.com/priyanka-agr/apiAutomation.git
```
* Initialize the local directory as a Git repository.
```bash
git init
```
* Add URL for the remote repository where your local repository will be pushed
```bash
git remote add origin https://github.com/priyanka-agr/apiAutomation.git
```
* Open IntelliJ, select "Import Project"

* Select the "apiAutomation "project folder from your local machine

* In the next page, select "Import project from external model", and select "Maven"

* When the project opens, the Maven dependencies will automatically download (Select Auto-Sync option from IDE)


### Read if you are looking for more information:
 :books: [java-client] (https://github.com/appium/java-client)

 :books: [MAVEN] (https://mvnrepository.com/)

 :books: [REST-ASSURED] (https://mvnrepository.com/artifact/io.rest-assured/rest-assured)

 :books: [jUnit] (https://mvnrepository.com/artifact/junit/junit)

```text
```
### Project Structure

```
src
├── main
│   └── resources
│       ├── data
│       │   
│       └── config.properties
│           
│       
└── test
        └── java
            ├── com.tajawal
                ├──apiactivator
                ├── hotels
            
                     
```

```text
```
### Run demo tests
```shell

```

### Frequently Asked Questions
```text
Tcs can be run from IDE, right click on class or test case name and click on run/Debug

```




