@test
Feature: login

@test1
Scenario: validate login error message
Given user launches the browser
Then User routes to Facebook Home page
Then user enters username
And user enters password
And user clicks on login button
Then validates the error message populated

@loginWithCredentialsfrominlineParameters
Scenario: validate login error message with inline parameters
Given User routes to Facebook Home page
Then user enters values using:
|UserName|Password|
|Test@123|Test@1234|
And user clicks on login button
And validates the error message populated


@loginWithCredentialsFromOutline
Scenario Outline: validate login error message with parameters from scenario outline
Given User routes to Facebook Home page
Then user enters username using "<UserName>"
And user enters password using "Password"
And user clicks on login button
And validates the error message populated

Examples:
|UserName|Password|
|Test@123|Test@1234|


@loginwithcredentialsusingDatatable
Scenario: validate login error message with inline parameters
Given User routes to Facebook Home page
Then user enters values using:
|UserName|Password|
|Test@123|Test@1234|
|Navya|test@123|
|NavyaRao|Test@123|
And user clicks on login button
And validates the error message populated
