Feature: Assign project manager
    Description: An employees assign a project manager to a project
    Actors: Employee

Scenario: An employee is assigned as project manager
    Given there is a project named "project1"
    And the project doesn't have a project manager
    And there is an employee with the initials "joh"
	And the employee is active user
    When the active user assigns "joh" as project manager of the project
    Then the employee is assigned as project manager of the project

	
Scenario: The active user changes the project manager of a project
    Given there is a project named "project1"
    And there is two employees with the initials "joh" and "pet"
    And and the first empolyee is the project manager of the project
    When the active user assigns the second employee as project manager for the project
    Then the project manager of the project is changed to the second employee
