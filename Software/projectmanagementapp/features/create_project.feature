Feature: Create project
    Description: An employee is able create a project. The project is also added to the project list
    Actors: Employee
	
Scenario: Create project successfully
    Given there is an employee who is active user
    When the active user tries to create a project with the name "Project1"
    Then the project is created and added to the list of projects
	
Scenario: An employee tries to create a project with no name
    Given there is an employee who is active user
    When the active user tries to create a project with the name ""
    Then the error message "A project needs a name" is given
    And the project is not added to the list of projects
    

Scenario: An employee tries to create a project with the same name as an already existing project
    Given there is an employee who is active user
    And there is a project with the name "Project1"
    When the active user tries to create a project with the name "Project1"
    Then the error message "The project name is already taken" is given
    And the project is not added to the list of projects
    
    
    