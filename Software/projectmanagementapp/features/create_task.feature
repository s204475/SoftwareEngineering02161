Feature: Create task
    Description: A project manager is able create a task. The task is also added to the list of tasks.
    Actors: Project manager


Scenario: A project manager creates a task successfully
    Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And the employee is project manager of the project
    When the employee creates a task with the name "Refactoring" and a estimated time of 20 hours
    Then the task is created

Scenario: An employee tries to create a task
    Given there is an employee with the initials "joh"
    And there is a project with the name "project1"
    And the employee is not project manager of the project
    When the employee creates a task with the name "Refactoring" and a estimated time of 20 hours
    Then the error message "You have to be a project manager to create a task" is given