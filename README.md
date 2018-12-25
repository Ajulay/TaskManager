#                      TaskManager

### Prototype for development custom task managers in organisations.

#### *Technologies:*
* Java 1.8
* Maven 4.0.0

#### *Project base structure:*
* App: Class with psvm;
* Executor: Class-entity contains executor data;
* Project: Class-entity contains project data;
* Task: Class-entity contains task data;  
* TaskConstant: Class contains base constants;
* ExecutorsService: Class presents executor data; 
* ProjectService: Class presents project data;
* TaskService: Class presents task data;
* ExecutorDao: Class provides executor data; 
* ProjectDao: Class provides project data;
* TaskDao: Class provides task data.

#### *Base functions:*    
* to see projects write: /pts
* to see executors write: /ex
* to see tasks write: /tks
* to add group task write: project_name, term, priority(1-3), content (, executor_surname1, executor_surname2...)
* to change status task write: /st id(task), NEW (or ONWORKING, FINISHED, FAILED)
* to delete task write: /dt id(task)
* to see all tasks in project write: /ptks project_id  

#### *Build:*

```bash
mvn clean install
```
#### *Deployment:* 
```bash
java -jar task-manager.jar
```
