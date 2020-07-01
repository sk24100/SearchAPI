Feature: Validate get API on search page

@postgetapi @regression
Scenario: 1 -Verify if access token is getting generated from POST API request and able to hit the Get API

Given I create the payload
When I hit post API
Then I validate if API call is sucessful with status code "200"
And I fetch the Access token
And I use access token with authorization type as Bearer to make a Get call
Then I validate if API call is sucessful with status code "200"
And I validate the data returned contains "Health Insurance Portability and Accountability Act"

#
#Given I create payload
#And I validate Number of answers "Number of answers=15"
#And I validate Title  "Health Insurance Portability and Accountability Act"


#
#
#
#|Payload|Expected|Expected2|
#|client id=123&queryid=985|Number of answers=15 |
#|client id=123&queryid=999|Title = Health Insurance Portability and Accountability Act |  | 