Scenario: when a user checks a non-existent user on github, github would respond 'not found'

Given github user profile api
And a random non-existent username
When I look for the random user via the api
Then github respond: 404 not found

When I look for eugenp1 via the api
Then github respond: 404 not found

When I look for eugenp2 via the api
Then github respond: 404 not found