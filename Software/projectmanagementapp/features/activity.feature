Feature: Create activity	
    Description: An employee is able create an activity in his/her schedule
    Actors: Employee
	
Scenario: Create activity successfully
    Given there is an employee with the initials "joh"
    And the employee is active user
    When when the active user creates an activity with the name "Holiday"
    Then the activity is created and is added to the employees schedule
    
	
Scenario: An employee tries to create an activity with no name
    Given there is an employee with the initials "joh"
    And the employee is active user
    When when the active user creates an activity with the name ""
    Then the error message "An activity needs a name" is given
    And the activity is not added to the employees schedule
	
Scenario: An employee tries to create an activity in the start, mid or end of another activity
	Given there is an employee with the initials "joh"
	And the employee is active user
    And the employee has an activity starting at 2021 04 02 0 0 and ending at 2021 04 04 0 0
    When the employee tries to create an activity starting at 2021 04 02 0 0
    Then the error message "Timeframe not available" is given
    And the activity is not added to the employees schedule
    When the employee tries to create an activity starting at 2021 04 03 0 0
    Then the error message "Timeframe not available" is given
    And the activity is not added to the employees schedule
    When the employee tries to create an activity starting at 2021 04 04 0 0
    Then the error message "Timeframe not available" is given
    And the activity is not added to the employees schedule
    
Scenario: Assign task activity successfully
    Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And the employee is project manager of the project
    And there is a task in the project
    And there is an employee with the initials "pet"
    When the active user assigns the task to "pet"
    Then the task is added to "pet" activities

Scenario: Assign task activity when not project manager
    Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And the employee is project manager of the project
    And there is an employee with the initials "per"
    And "per" is not project manager of the project
    And there is an employee with the initials "pet"
    When "per" assigns the task to "pet"
    Then the error message "Only project managers can assign tasks" is given
    And  the task is not added to "pet" activities