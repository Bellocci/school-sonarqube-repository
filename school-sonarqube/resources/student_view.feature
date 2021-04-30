# I valori | 1 | first student | ... vengono utilizzati per la funzionalità data tables
# che permette di parameterizzare. Questo è possibile se lo scenario è lo stesso e ciò
# che variano sono solo i valori dei parametri.

# Una rappresentazione identica a questa ma senza l'utilizzo delle data tables è:
#
# Given The database contains a student with id "1" and name "first student"
# And The database contains a student with id "2" and name "second student"
# When The Student View is shown
# Then The list contains an element with id "1" and name "first student"
# And The list contains an element with id "2" and name "second student"

Feature: Student Application Frame Specifications of the behavior of the Student Application Frame

	Scenario: The initial state of the view
		Given The database contains the students with the following values
			| 1 | first student |
			| 2 | second student |
		When The Student View is shown
		Then The list contains elements with the following values
			| 1 | first student |
			| 2 | second student |		
	
	Scenario: Add a new student
		Given The Student View is shown
		When The user enters the following values in the text fields
			| id | name |
			| 1 | a new student |
		And The user clicks the "Add" button
		Then The list contains elements with the following values
			| 1 | a new student |
			
	Scenario: Add a new student with an existing id
		Given The database contains the students with the following values
			| 1 | first student |
		And The Student View is shown
		When The user enters the following values in the text fields
			| id | name |
			| 1 | a new student |
		And The user clicks the "Add" button
		Then An error is shown containing the following values
			| 1 | first student |
			
	Scenario: Delete a student
		Given The database contains the students with the following values
			| 1 | first student |
		And The Student View is shown
		When The user selects the unique student from the list
			| 1 | first student |
		And The user clicks the "Delete Selected" button
		Then The unique student is removed from the list
			| 1 | first student |
		And The list of student is empty
		