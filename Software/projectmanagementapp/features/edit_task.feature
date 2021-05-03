Feature: Edit task
	Description: The project manager edits a task on a project
	Actors: Project manager

Scenario: Edits a task successfully
		Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And there exist a task with the name "Refactoring" and an estimated time of 20 hours, which is the active task
    And the employee is project manager of the project
		When the employee tries to change the estimated time to 10 hours
		Then the estimated time of the task is 10 hours
		When the employee tries to change the name to "bugfix"
		Then the name of the task is "bugfix"

Scenario: An employee, who is not the project manager, tries to edit a task
		Given there is an employee with the initials "joh"
		And the employee is active user
    And there is a project with the name "project1"
    And there exist a task with the name "Refactoring" and an estimated time of 20 hours, which is the active task
    And the employee is not project manager of the project
		When the employee tries to change the estimated time to 10 hours
    Then the error message "You have to be a project manager to change or create a task" is given

Scenario: A project mannager tries to edit the name or estimated time of a task to empty
		Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And there exist a task with the name "Refactoring" and an estimated time of 20 hours, which is the active task
    And the employee is project manager of the project
    When the employee tries to change the name to ""
    Then the error message "A task has to have a name and estimed time" is given
    When the employee tries to change the estimated time to 0 hours
		Then the error message "A task has to have a name and estimed time" is given
  
Scenario: A project manager tries to edit a non existing task.	
		Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And the employee is project manager of the project
		When the employee tries to change the name to "bugfix"
		Then the error message "the task does not exist" is given
	
	
