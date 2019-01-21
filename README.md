#                      TaskManager

### Prototype for development custom task managers in organisations.

#### *Technologies:*
* Java 1.8
* Maven 4.0.0

#### *Project base structure:*
* com.ajulay.App: Class with psvm;
* User: Class-entity contains executor data;
* Assignee: Class-entity contains relation between Assigner and Task;
* Project: Class-entity contains project data;
* Task: Class-entity contains task data;  
* ServiceConstant: Class contains base constants;
* Status: Enum - START, IN_PROGRESS, FINISHED, FAILED;
* UserService: Class presents assigner (executor) data; 
* AssigneeService: Class presents assignee data;
* ProjectService: Class presents project data;
* TaskService: Class presents task data;
* UserDao: Class provides assigner (executor) data; 
* AssigneeDao: Class provides assignee data; 
* ProjectDao: Class provides project data;
* TaskDao: Class provides task data;
* Domain: Class for data transporting;
* Exception: Package com.com.com.ajulay.exception contains custom exceptions;
* AbstractCommand: Class-interface for executing command;
* Command: Package com.com.com.ajulay.command contains Command classes including custom ones;
* Api: Package com.com.com.ajulay.api contains base interfaces for data access.

#### *Base functions:*    
* to see base function type: /help;
* to login type: /login or /reg (if no login);
* to exit from application type: /exit. 

#### *Build:*

```bash
mvn clean install
```
#### *Deployment:* 
```bash
java -jar ./task-manager.jar
```
