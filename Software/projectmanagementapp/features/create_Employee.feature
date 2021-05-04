Feature: Create employee
    Description: Creating different employees in the app
    Actors: Employee

Scenario: Create an employee with a long name
    When An employee with the name "Hans Gård Mand Peter Chirstian Jensen" is created
    Then Then the employee exist