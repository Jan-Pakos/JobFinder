## Functional Requirements
1. User can make GET request to /offers endpoint to get all job offers when authoritized
2. User can make GET request to /offers/{id} endpoint to get a specific job offer from an id when authorized
3. User can make POST request to /register endpoint with username and password to register and get a JWT token.
4. If user makes more than one request within 60 minutes, data should be fetched from cache
5. System should fetch job offers from external HTTP server every 3 hours and store them in the database without duplicates
6. User can make POST request to /offers endpoint to add a new job offer when authorized
7. Each job offer should have a link, job title, company name, and salary (can be a range)
8. If user tries to access /offers or /offers/{id} without a valid JWT token, system should return UNAUTHORIZED 401
9. If user tries to get a job offer with an id that does not exist, system should return NOT_FOUND 404 with appropriate message
10. System should ensure that job offers in the database are unique based on the offer URL
11. User should be able to manually add a job offer via POST /offers endpoint


## Happy Path For JobFinder
1. There are no job offers in the external HTTP server
2. Scheduler ran 1st time and made GET request to external server and the system added 0 offers to the database
3. User tried to get a JWT token by making POST request to /token and the system returned UNAUTHORIZED 401
4. User made a GET request to /offers with no JWT token and the system returned UNAUTHORIZED 401
5. The user made a POST request to /register with username=user1 and password=password1 and the system returned OK 200 and JWTtoken=AAAA.BBBB.CCC
6. User made a GET to /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and the system returned OK 200 with 2 offers with ids: 1000 and 2000
7. There are 2 new offers on the external HTTP server
8. the scheduler ran a 2nd time and made a GET request to the external server and the system added 2 new offers with ids: 1000 and 2000 to the database
9. User made GET request to /offers and 2 job offers are returned with ids: 1000 and 2000
10. User made a GET request to /offers/9999 and the system returned NOT_FOUND 404 with message “Offer with id 9999 not found”
11. User made GET request to /offers/1000 and the system returned OK 200 with offer with id 1000
12. scheduler ran a 3rd time and made a GET request to the external server and the system added 2 new offers with ids: 3000 and 4000 to the database
13. The user made a GET request to /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and the system returned OK 200 with 4 offers with ids: 1000, 2000, 3000, 4000 