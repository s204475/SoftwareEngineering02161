Feature: White box tests
	Description: White box tests

Scenario: searchEmployees test 1
	Given these employees are contained in the app 
        | John | joh | 
        | Per | per | 
        | Peter | pet | 
    When there is searched for an employee with the initials "joh"
    Then the employee with the initials "joh" is found

Scenario: searchEmployees test 2
	Given there is an employee with the initials "joh"
    When there is searched for an employee with the initials "joh"
    Then the employee with the initials "joh" is found

Scenario: searchEmployees test 3
	Given that no employees is contained in the app
    When there is searched for an employee with the initials "joh"
    Then the error message "Employee doesn't exist" is given

Scenario: searchEmployees test 4
	Given these employees are contained in the app 
        | Per | per | 
        | Peter | pet | 
    When there is searched for an employee with the initials "joh"
    Then the error message "Employee doesn't exist" is given



Scenario: addActivity test 1
	Given there is an employee with the initials "joh"
    And the employee is active user
    When when the active user creates an activity with the name "Activity 1", the start time 2021 04 04 0 0 and end time 2021 04 04 0 0
    Then the activity is not contained in the employees activities
    And the error message "Timeframe not available" is given

Scenario: addActivity test 2
    Given there is an employee with the initials "joh"
    And the employee is active user
    When when the active user creates an activity with the name "Activity 2", the start time 2021 04 04 0 0 and end time 2021 04 05 0 0
    Then the activity is contained in the employees activities

Scenario: addActivity test 3
    Given there is an employee with the initials "joh"
    And the employee is active user
    When when the active user creates an activity with the name "Activity 3", the start time 2021 04 04 0 0 and end time 2021 04 03 0 0
    Then the activity is not contained in the employees activities
    And the error message "Timeframe not available" is given

Scenario: addActivity test 4
    Given there is an employee with the initials "joh"
    And the employee is active user
    When when the active user creates an activity with the name "Activity 4", the start time 2021 04 04 0 0 and end time 2021 04 04 0 30
    Then the activity is contained in the employees activities

Scenario: addActivity test 5
    Given there is an employee with the initials "joh"
    And the employee is active user
    When when the active user creates an activity with the name "Activity 5", the start time 2021 04 04 0 0 and end time 2021 04 04 0 10
    Then the activity is not contained in the employees activities
    And the error message "Timeframe not available" is given

Scenario: addActivity test 6
    Given there is an employee with the initials "joh"
    And the employee is active user
    And the these activities is contained in the employees activities
        | Activity 6.1 | 2021 | 04 | 03 | 0 | 0 | 2021 | 04 | 05 | 0 | 0 |
    When when the active user creates an activity with the name "Activity 6", the start time 2021 04 04 8 0 and end time 2021 04 04 10 0
    Then the activity is not contained in the employees activities
    And the error message "Timeframe not available" is given

Scenario: addActivity test 7
    Given there is an employee with the initials "joh"
    And the employee is active user
    And the these activities is contained in the employees activities
        | Activity 7.1 | 2021 | 04 | 04 | 4 | 0 | 2021 | 04 | 04 | 7 | 0 |
        | Activity 7.2 | 2021 | 04 | 04 | 12 | 0 | 2021 | 04 | 04 | 14 | 0 |
    When when the active user creates an activity with the name "Activity 7", the start time 2021 04 04 8 0 and end time 2021 04 04 10 0
    Then the activity is contained in the employees activities

Scenario: addActivity test 8
    Given there is an employee with the initials "joh"
    And the employee is active user
    And the these activities is contained in the employees activities
        | Activity 8.1 | 2021 | 04 | 04 | 12 | 0 | 2021 | 04 | 04 | 14 | 0 |
    When when the active user creates an activity with the name "Activity 8", the start time 2021 04 04 8 0 and end time 2021 04 04 12 0
    Then the activity is not contained in the employees activities
    And the error message "Timeframe not available" is given

Scenario: addActivity test 9
    Given there is an employee with the initials "joh"
    And the employee is active user
    And the these activities is contained in the employees activities
        | Activity 9.1 | 2021 | 04 | 04 | 6 | 0 | 2021 | 04 | 04 | 8 | 0 |
    When when the active user creates an activity with the name "Activity 9", the start time 2021 04 04 8 0 and end time 2021 04 04 12 0
    Then the activity is not contained in the employees activities
    And the error message "Timeframe not available" is given





#Scenario: isAvailable test 1
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #When a isAvaiable request with the start time 2021 04 04 0 0 and end time 2021 04 04 0 0 is made
    #Then the error message "Invalid timeframe" is given
#
#Scenario: isAvailable test 2
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #When a isAvaiable request with the start time 2021 04 04 0 0 and end time 2021 04 05 0 0 is made
    #Then the result is true
#
#Scenario: isAvailable test 3
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #When a isAvaiable request with the start time 2021 04 04 0 0 and end time 2021 04 03 0 0 is made
    #Then the error message "Invalid timeframe" is given
#
#Scenario: isAvailable test 4
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #When a isAvaiable request with the start time 2021 04 04 0 0 and end time 2021 04 04 0 30 is made
    #Then the result is true
#
#Scenario: isAvailable test 5
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #When a isAvaiable request with the start time 2021 04 04 0 0 and end time 2021 04 04 0 10 is made
    #Then the error message "Invalid timeframe" is given
#
#
#Scenario: isAvailable test 6
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #And the these activities is contained in the employees activities
        #| Activity 6.1 | 2021 | 04 | 03 | 0 | 0 | 2021 | 04 | 05 | 0 | 0 |
    #When a isAvaiable request with the start time 2021 04 04 8 0 and end time 2021 04 04 10 0 is made
    #Then the result is false
#
#Scenario: isAvailable test 7
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #And the these activities is contained in the employees activities
        #| Activity 7.1 | 2021 | 04 | 04 | 4 | 0 | 2021 | 04 | 04 | 7 | 0 |
        #| Activity 7.2 | 2021 | 04 | 04 | 12 | 0 | 2021 | 04 | 04 | 14 | 0 |
    #When a isAvaiable request with the start time 2021 04 04 8 0 and end time 2021 04 04 10 0 is made
    #Then the result is true
#
#Scenario: isAvailable test 8
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #And the these activities is contained in the employees activities
        #| Activity 8.1 | 2021 | 04 | 04 | 12 | 0 | 2021 | 04 | 04 | 14 | 0 |
    #When a isAvaiable request with the start time 2021 04 04 8 0 and end time 2021 04 04 10 0 is made
    #Then the result is false
#
#Scenario: isAvailable test 9
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #And the these activities is contained in the employees activities
        #| Activity 9.1 | 2021 | 04 | 04 | 6 | 0 | 2021 | 04 | 04 | 8 | 0 |
    #When a isAvaiable request with the start time 2021 04 04 8 0 and end time 2021 04 04 10 0 is made
    #Then the result is false
#
#Scenario: isAvailable test 10
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #And the these activities is contained in the employees activities
        #| Activity 10.1 | 2021 | 04 | 04 | 6 | 0 | 2021 | 04 | 04 | 10 | 0 |
    #When a isAvaiable request with the start time 2021 04 04 8 0 and end time 2021 04 04 10 0 is made
    #Then the result is false
#
#Scenario: isAvailable test 10
#	Given there is an employee with the initials "joh"
    #And the employee is active user
    #And the these activities is contained in the employees activities
        #| Activity 10.1 | 2021 | 04 | 04 | 6 | 0 | 2021 | 04 | 04 | 10 | 0 |
    #When a isAvaiable request with the start time 2021 04 04 4 0 and end time 2021 04 04 8 0 is made
    #Then the result is false
    

    
    
Scenario: createTask test 1
		Given these employees are contained in the app 
        | John | joh | 
		And "joh" is active user 
		And there exists a project with the name "Project1"
		And "joh" is project manager of the project
		When employee tries to create a task with the name "Task 1" and an estimated time 1.2
		Then the task is not created
		And the error message "Estimated time has to be given 0.5 hours" is given
		 
Scenario: createTask test 2
		Given these employees are contained in the app 
        | John | joh | 
		And "joh" is active user 
		And there exists a project with the name "Project1"
		And "joh" is project manager of the project
		When employee tries to create a task with the name "Task 2" and an estimated time 0
		Then the task is not created
		And the error message "Estimated time has to be given 0.5 hours" is given
		
Scenario: createTask test 3
		Given these employees are contained in the app 
        | John | joh | 
		And "joh" is active user 
		And there exists a project with the name "Project1"
		And "joh" is project manager of the project
		When employee tries to create a task with the name "Task 3" and an estimated time -1
		Then the task is not created
		And the error message "Estimated time has to be given 0.5 hours" is given		
		
Scenario: createTask test 4
		Given these employees are contained in the app 
        | John | joh | 
		And "joh" is active user 
		And there exists a project with the name "Project1"
		And "joh" is project manager of the project
		When employee tries to create a task with the name "" and an estimated time 2
		Then the task is not created
		And the error message "A task has to have a name and estimated time" is given		
		
Scenario: createTask test 5
		Given these employees are contained in the app 
        | John | joh | 
		And "joh" is active user 
		And there exists a project with the name "Project1"
		And project does not have a project manager
		When employee tries to create a task with the name "Task 5" and an estimated time 2
		Then the task is not created
		And the error message "You have to be a project manager to change or create a task" is given
		
Scenario: createTask test 6
		Given these employees are contained in the app 
        | John | joh | 
        | Peter | pet |		
		And "pet" is active user
		And there exists a project with the name "Project1"  
		And "joh" is project manager of the project
		When employee tries to create a task with the name "Task 5" and an estimated time 2
		Then the task is not created
		And the error message "You have to be a project manager to change or create a task" is given		
		
Scenario: createTask test 7
		Given these employees are contained in the app 
        | John | joh | 
		And "joh" is active user 
		And there exists a project with the name "Project1"
		And "joh" is project manager of the project
		When employee tries to create a task with the name "Task 5" and an estimated time 2
		Then the task is in the task list 

		
		
		
		
		