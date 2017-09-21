Feature: testing google calculator

	Scenario: addition of two numbers
		Given go to google
		When type addition of two numbers, 3 and 4
		Then the result should be 6