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
    
Scenario: Tries to search for a project by Id successfully
    Given there is an employee who is active user
    And there is a project with the name "Project1"
	When the employee searches for the project with the Id "210001"
	Then the project "Project1" is given to the user.
	
Scenario: Tries to search for a project by Title successfully
    Given there is an employee who is active user
    And there is a project with the name "Project1"
	When the employee searches for the project with the Title for "Project1"
	Then the project "Project1" is given to the user.
	
Scenario: Tries to search for a project by Id unsuccessfully
    Given there is an employee who is active user
	When the employee searches for the project with the Id "210001"
	Then the error message "project does not exist" is given

Scenario: Tries to search for a project by Title unsuccessfully
    Given there is an employee who is active user
	When the employee searches for the project with the Title for "Project1"
	Then the error message "project does not exist" is given
    