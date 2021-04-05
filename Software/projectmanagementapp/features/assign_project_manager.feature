Feature: Assign project manager
    Description: The employees assign a project manager to a project
    Actors: Employee

Scenario: An employee is assigned as project manager
    Given there is a project with the name "project1"
    And the "project1" doesn't have a project manager
    And there is an employee with the intials "joh"
	And the employee is active user
    When the active user assigns "joh" as project manager of the project
    Then "joh" is assigned as project manager of the project

	

#Scenario: A project already has a project manager, and the employee tries to assign another 
#    project manager
#    Given there is a project "project1"
#    And "John" is the project manager of "project1"
#    And there is an employee "Hannibal"
#    When the active user assigns "Hannibal" as project manager for "project1"
#    Then the error message "The project already has a project manager" is given