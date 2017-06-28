# document-management

A document management web service which could download, upload, pass dictionary to multiple client.  
upload and delete functionality are not finished and I don't want to finish them because this a dummy project to evaluate
programmer's capability and it doesn't make sense to double the quantity of code.  
Existing code is clear enough to evaluate the code level.  

## Getting Started
Two endponits finished are:  
http://localhost:8080/api/resource  
http://localhost:8080/api/document/TEST_MY_APP1/version/1  

Please check your localhost port is not occupied by other application as Skype.  

### Prerequisites

Install a Java version 1.8  
Install a Gradle version 4.0  
For system env variable as Path, JAVA_HOME, GRADLE_HOME, please refer to official document as Windows, Linux, MacOS  


### Installing

1 - Clone from Git to local.  
2 - Import as Gradle in Eclipse.  
3 - (Only for windows user) copy all xml file(test1.xml, test2.xml, test3.xml) from src/main/resource/file to your C:\\  
	3.1 - (Only for windows user) go to application.yml, line 12, change "active: windows"  
4 - (Only for MacOS or Linux) create folder /Users/hejunz/src/Java/eworkspace/, put your project document-management to this folder.  
5 - In command line, run ./gradlew build && java -jar build/libs/file-management-0.1.0.jar .  
6 - If command line mode doesn't work, please goto Class BootApplication.java in Eclipse and Run it as MainApplication  
7 - If two server.xml and client.xml are created into C://(for windows)
	and /Users/hejunz/src/Java/eworkspace/document-management/src/main/resource (Linux or mac)  

If it could not work, please refer to me by harryheone@gmail.com.  

## Running the tests

There is no Unit Test because it takes the time I don't have to finish it with high coverage.  
It is simple module project and easy to write test with JUnit/TestNG and Mockito.  
There is little Demo of Unit test in the src/test/main


## Requirement

I analyse the requirement by test document with my personal understanding
so I can't guarantee every use case is designed as your expected.
Please read this requirement and evaluate design of system and quality of code.
This system(Server) will store a lot of document and two dictionary files.
Client could connect to this system by rest api.
Client could fetch dictionary file to know what document server could offer.
Client could download, create(not done), upload(not done), delete(done) document.

About synchronization between DOCUMENT and DICTIONARY
Each time the server is booted, it will update DICTIONARY(server.xml and client.xml) with current
document in the given directory.
If client update/create/delete, DICTIONARY will be updated in same time.
If an unexpected use case happened, ex: a document has be updated by admin in File System of Operation System, 
unfortunately there is no callback/message/notification to client, but when client try to read document, it will
receive error message to ask it to update dictionary.

## Technology Stack

### Why Spring Boot?
Spring Boot integrate all module mandatory to build restful web service or other app.
Instead of add dependencies and install tomcat one by one, it takes just 5 min to build a restful service with Spring Boot.
The official document to Spring Boot is really good, high recommend to read it.

### Why Restful Service?
I assume client and server communicate by HTTP, means in minimum they have a HTTP server(not necessary Tomcat, maybe Nginx).
I choose tomcat with restful Service because it uses NIO to treat multi-client connection.
Of course Netty has better performance but it also take times to configure it in JBoss.
If client and server don't support HTTP(rarely in modern system), ex: only TCP/IP or UDP. 
We need to write multi-thread app with socket api as Skype or ancient MSN. The effort to do this is out of my timesheet. So...

### Concurrent read/write document
Multi-client even Server(Tomcat) will create multi-thread to reply concurrent request.
It will cause problem of concurrent read/write or write/write document.
I use to lightweight ReentrantReadWriteLock to lock read and write. 
It will create serious delay and give the concurrency enough protection.
I think we could use Eventual Consistent solution as PAXOS too if a lot of write, but it is out of scope of this project.

### Cache
I don't use any framework but create a simple class as Cache of Dictionary, because 90% of read is the one of Dictionary.
For keep it thread-saft, I use concurrent map class which is provided by Java 5.
The more elegant way to update cache is that Component of DictionaryUpdateService will notify observers(Cache is 
one of observers) for unique system or send message to MessageQueue for distribute system.
Due to our simplicity of code, I didn't attach Cache as observer of UpdateService.

### DOM4J
Since we could use JAXB to transfer Java to XML but for update, we only update a part of dictionary, 
replace who XML will decrease performance by add more I/O which is often the bottleneck.
And DOM4J could give us better solution only update a part of XML

### Properties and Error handler
Properties and Error handler use the functionality offered by Spring.
@ConfigurationProperties could read value from application.yml/.properties.
I user Spring Profile to distinguish different configuration of different env. My laptop is Mac, 
so I use "linux" profile, it will read/directory to my Mac Folder, you need to change it as Windows.
How profile works please search by Google "Spring Boot Profile example"

@RestControllerAdvice is implemented by SpringAOP and it will intercept all response from Server to client,
if response is the exception defined by @ExceptionHandler, it will intercept it and build response as method return type.


## Code Design
I divide system as Entry, Workflow, Business Center, Repository/ReadService

### Entry
It is very simple to understand, entry is as his name, the first class when restful call arrives or system start.
*EndPoint.java and BootApplication.java are two entries of system.
*EndPoint is response to build response object

### Workflow and Business Center
Workflow is composition of tasks, meanwhile every single task is offered by BusinessCenter.
There are two Business Center, one Dictionary and one Document.
Why I didn't create interface of Business Center but programming directly with implementation?
I don't want to break OO design Rules but Business Center is center of business logic.
It is rarely to update business logic as use existing document to update dictionary, at worst even it is changed,
we could simply modify one method as a task not the whole class, and check workflow use this task.
Why there is no separate class validator?
Because validator is quite simple and easy to integrate with Workflow, but I agree we need to have validator class
if validation logic become complex.


### Repository
It works as DAO or Repository of JPA. It supply us CRUD of xml file, the same as CRUD of Database.
Because there is no ACID as Database, so we use ReentrantReadWriteLock to help us.
Interface is mandatory of this component because requirement could be changed easily. Ex: From XML to JSON/YML/DATABASE
The resolve this issue, we just need create new Implementation of interface and inject it to Business Center.
We respect: Open for extension but close to modification - QQ principle.
Same principle for CacheImpl, if we would like to read from XML not from Cache, just inject XmlImpl to BusinessCenter.

### Mapper
There is not mapper because all level(Entry, Business, Repository) share same object, it the proper solution for now.
But for more complex system we need to have different object for different level as DTO, BO, Response.










