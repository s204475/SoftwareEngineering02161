Feature: Create task
    Description: A project manager is able create a task. The task is also added to the list of tasks.
    Actors: Project manager

Background: The app has a project "project1" and two employess. One of the employees is project manager
    of "project1" and the other employee is not 

    Given these employees are contained in the app
        | John | joh |
        | Hannibal | han |
    And there is a project "project1"
    And "joh" is project manager of "project1"

Scenario: A project manager creates a task successfully
    Given there is a project with the name "project1"
    And an employee named "John" is project manager of "project1" 
    When "John" creates a task with the name "Refactoring"
    Then the task "Refactoring" is created
    And the task is added to the list of tasks

#Scenario: An employee tries to create a task
#    Given there is a project with the name "project1"
#    And there is an employee with the name "Hannibal", who is not the project manager of "project1" 
#    When "Hannibal" tries to create a task
#    Then the error message "You have to be a project manager to create a task" is given