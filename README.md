# Task Tracker CLI App
## Features
* Add, Update, and Delete tasks
* Mark a task as in progress or done
* List all tasks
* List all tasks that are done
* List all tasks that are not done
* List all tasks that are in progress

# Installation
1. clone the repository
`https://github.com/ruxlsr/TaskManager-cli.git`
`cd taskManager-cli`

2. Compile the source code
 `javac taskmanager/*.java taskmanager/model/*.java`
3. Create the JAR file
  `jar cfe task-cli taskmanager.TaskCli ./taskmanager/*.class taskmanager/model/*.class ./taskmanager/TaskCli.class`
4. Run the app
   `java -jar task-cli <command>`

5. Usage
   `` bash
   java -jar task-cli add <description of the task>
   java -jar task-cli delete <task_id to delete>
   java -jar task-cli update <task_id to update> <new task description>
   java -jar task-cli mark-in-progress <task_id to mark>
   java -jar task-cli mark-done <task_id to mark>
   java -jar task-cli list <done|todo|in-progress|> default <all>
   ``
