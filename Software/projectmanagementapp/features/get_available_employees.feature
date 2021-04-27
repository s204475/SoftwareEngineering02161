Feature: Get available employees
Description: An employee gets a list of all employees that are available during a duration
Actors: Employee

Scenario: Get available employees successfully
Given there is an employee with the initials "joh"
And there is an employee with the initials "bob"
And "joh" has an activty from year 2020 month 10 day 10 hour 10 to year 2020 month 11 day 11 hour 11
And "bob" has no activty from year 2020 month 10 day 11 hour 11 to year 2020 month 10 day 12 hour 11
When the active user searches for availble employees in the duration from year 2020 month 10 day 11 hour 11 to year 2020 month 10 day 12 hour 11
Then the employees "bob" is given

Scenario: There are no available employees
Given there is an employee with the initials "joh"
And there is an employee with the initials "bob"
And "joh" has an activty from year 2020 month 10 day 10 hour 10 to year 2020 month 11 day 11 hour 11
And "bob" has an activty from year 2020 month 10 day 10 hour 10 to year 2020 month 11 day 11 hour 11
When the active user searches for availble employees in the duration from year 2020 month 10 day 11 hour 11 to year 2020 month 10 day 12 hour 11
Then the error message "No available employees at the given time" is given

Scenario: No duration provided
Given there is an employee with the initials "joh"
When the active user searches for availble employees in the duration from year 2020 month 10 day 11 hour 11 to year 2020 month 10 day 11 hour 11
Then the error message "You have not selected a duration to find available employees" is given