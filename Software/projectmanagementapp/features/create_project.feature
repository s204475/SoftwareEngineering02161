Feature: Create project
    Description: An employee is able create a project. The project is also added to the project list
    Actors: Employee
	
Scenario: Create project successfully
    Given there is an employee
    When the active user creates a project with a name
    Then the project is created and added to the list of projects
	
Scenario: An employee tries to create a project with no name
    Given there is an employee
    When the active user tries to create a project which does not have a name
    Then the error message "A project needs a name" is given
    And the project is not added to the list of projects
    
Scenario: An employee tries to create a project with no name
    Given there is an employee
    When the active user tries to create a project which does not have a name
    Then the error message "A project needs a name" is given
    And the project is not added to the list of projects


