# Victor Rasmussen s204475

Feature: Remove tasks, activities, users and the associated data
    Description: An employee removes a task, an activity and a project from the app
    Actors: Employee

Scenario: A project manager deletes a task.	
	Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "projectDeletion"
    And there exist a task with the name "Refactoring" and an estimated time of 20 hours, which is the active task
    And the employee is project manager of the project
	When the employee tries to delete the task
	Then the task is deleted
	
Scenario: A user deletes an activity. 
	Given there is an employee with the initials "joh"
    And the employee is active user
    And the employee has an activity starting at 2021 04 02 0 0 and ending at 2021 04 04 0 0
	When the employee tries to delete the activity
	Then the activity is deleted
	
Scenario: A project manager deletes a project.	
	Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "projectDeletion"
    And the employee is project manager of the project
	When the employee tries to delete the project
	Then the project is deleted
	
Scenario: A user deletes a user
	Given there is an employee with the initials "joh"
	When the employee tries to delete a user
	Then the user is deleted