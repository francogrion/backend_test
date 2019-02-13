# backend_test
API to transfer money


Design and implement a RESTful API (including data model and the backing implementation) for money transfers between accounts.

Explicit requirements:
1. You can use Java, Scala or Kotlin.
2. Keep it simple and to the point (e.g. no need to implement any authentication).
3. Assume the API is invoked by multiple systems and services on behalf of end users.
4. You can use frameworks/libraries if you like (except Spring), but don't forget about requirement #2 – keep it simple and avoid heavy frameworks.
5. The datastore should run in-memory for the sake of this test.
6. The final result should be executable as a standalone program (should not require a pre-installed container/server).
7. Demonstrate with tests that the API works as expected.

Implicit requirements:
1. The code produced by you is expected to be of high quality.
2. There are no detailed requirements, use common sense.


# Steps to run the server

1. Check-out the code
2. Go to the downloads path
3. Execute from console:
```
	mvn clean install
```
4. Execut from console:
```
	mvn exec:java
```

# Request to add new BankAccount

POST
endpoint:
http://localhost:8080/account_service

body:
```JSON
{
      "bankCity":"Mendoza",
      "bankName":"Rio",
      "customer":{
      	    "infix":"Sr",
    		"firstName":"Alan",
		    "lastName":"Grion",
    		"gender": 0,
    		"passportNo":"5554567"
      },
      "balance":{
    		"value":200.00,
    		"currency":"USD"
      }
}
```
# Request to add a second BankAccount

POST
endpoint:
http://localhost:8080/account_service

body:
```JSON
{
      "bankCity":"Cordoba",
      "bankName":"HSBC",
      "customer":{
      	    "infix":"Sr",
    		"firstName":"Franco",
		    "lastName":"Grion",
    		"gender": 0,
    		"passportNo":"1234567"
      },
      "balance":{
    		"value":33231.00,
    		"currency":"EUR"
      }
   }
   ```

# Request to retrieve a BankAccount
GET
endpoint:
http://localhost:8080/account_service/{account_number}

example: http://localhost:8080/account_service/1


# Request to transfer value between accounts
POST
endpoint:
http://localhost:8080/transfer

body:
```JSON
{
      "originAccountNumber": "2",
      "destinationAccountNumber": "1",
      "amount":{
    		"value":100.00,
    		"currency":"USD"
      }
}
```
