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
	
#Scenario: An employee tries to create multiple task in the same spot
#    Given an employee has an activity at "08:00, 20.04.2021"
#    When the employee tries to create an activity at "08:00, 20.04.2021"
#    Then the error message "Time slot not available" is given