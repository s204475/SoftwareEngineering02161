Feature: Create employee
    Description: Creating different employees in the app
    Actors: Employee

Scenario: Create an employee with a long name
    When An employee with the name "Victor Gustav Harboe Rasmussen Sørensen" is created
    Then the employee exist

Scenario: An employee who isn't in the system is set to be the active user
    Given There is exist an employee with the initials "joh" who is not in the system
    When the employee is assigned to active user
    Then the error message "the employee doesn't exist" is given

Scenario: A project which isn't in the system is set to be the active project
    Given There is exist an project with the title "project1" which is not in the system
    When the project is assigned to active project
    Then the error message "the project does not exist" is given    