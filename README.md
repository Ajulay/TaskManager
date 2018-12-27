#                      TaskManager

### Prototype for development custom task managers in organisations.

#### *Technologies:*
* Java 1.8
* Maven 4.0.0

#### *Project base structure:*
* App: Class with psvm;
* Assigner: Class-entity contains executor data;
* Assignee: Class-entity contains relation between Assigner and Task;
* Project: Class-entity contains project data;
* Task: Class-entity contains task data;  
* ServiceConstant: Class contains base constants;
* Status: enum - START, INPROGRESS, FINISHED, FAILED;
* AssignerService: Class presents assigner (executor) data; 
* AssigneeService: Class presents assignee data;
* ProjectService: Class presents project data;
* TaskService: Class presents task data;
* AssignerDao: Class provides assigner (executor) data; 
* AssigneeDao: Class provides assignee data; 
* ProjectDao: Class provides project data;
* TaskDao: Class provides task data;
* Exception: package com.ajulay.exception contains custom exceptions;
* AbstractCommand: Class-interface for executing command;
* Api: package com.ajulay.api contains base interfaces for data access.

#### *Base functions:*    
* to see base function type: /help;
* to exit from application type: /exit. 

#### *Build:*

```bash
mvn clean install
```
#### *Deployment:* 
```bash
java -jar ./task-manager.jar
```
