# Victor Rasmussen s204475 og Anders Gad s204496

Feature: Create a report
    Description: A project manager is able create a report. The report is saved as a .txt file.
    Actors: Project manager


Scenario: A project manager creates a report to the default path
    Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And the employee is project manager of the project
    When the employee tries to create a report to default path
    Then the report is created
    
Scenario: A project manager gives a bad path when trying to create a report
    Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And the employee is project manager of the project
    When the employee tries creates a report to the path "this is not a correct path"
    Then the error message "the path was not found" is given
    
Scenario: A project manager creates a report that contains information. 
    Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
	And there exist a task with the name "Refactoring" and an estimated time of 20 hours, which is the active task
	And the employee is project manager of the project
	And the employee with the initials "joh" is assigned to the task
	When the employee tries to create a report to default path
	Then the report contains information    