Feature: Create a report
    Description: A project manager is able create a report. The report is saved as a .txt file.
    Actors: Project manager


Scenario: A project manager creates a report to the default path
    Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And the employee is project manager of the project
    When the employee tries to create a report to default path
    Then the report is created
    
Scenario: A project manager gives a bad path when trying to create a report
    Given there is an employee with the initials "joh"
    And the employee is active user
    And there is a project with the name "project1"
    And the employee is project manager of the project
    When the employee tries creates a report to the bad path "thisisnotacorrectpath"
    Then the report is not created in "thisisnotacorrectpath" and an error is given