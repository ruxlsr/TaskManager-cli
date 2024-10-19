# Task Tracker CLI App
 Simple CLI app for managing task
## Features
* Add, Update, and Delete tasks
* Mark a task as in progress or done
* List all tasks
* List all tasks that are done
* List all tasks that are not done
* List all tasks that are in progress

# Installation
1. clone the repository
```bash
git clonehttps://github.com/ruxlsr/TaskManager-cli.git`
cd TaskManager-cli
```

3. Compile the source code
```bash
javac taskmanager/*.java taskmanager/model/*.java
```
4. Create the JAR file
```bash
jar cfe task-cli taskmanager.TaskCli ./taskmanager/*.class taskmanager/model/*.class ./taskmanager/TaskCli.class
```
5. Run the app
```bash
java -jar task-cli <command>
```

# Usage
   ``` bash
   # Add a new task 
   java -jar task-cli add <description of the task>
   # Delete a task
   java -jar task-cli delete <task_id to delete>
   # update a task
   java -jar task-cli update <task_id to update> <new task description>
   # mark task as in progress
   java -jar task-cli mark-in-progress <task_id to mark>
   # mark task as done
   java -jar task-cli mark-done <task_id to mark>
   # list task: default, all task are listed
   java -jar task-cli list <done|todo|in-progress|> 
   ```



**Project provided by : [roadmap.sh](https://roadmap.sh/projects/task-tracker)**
