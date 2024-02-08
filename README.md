SETUP: Open the project in IntelIJ and run: mvn clean install from the terminal
Start the spring boot application: CustomerServiceApplication

In the browser open the link http://localhost:8080/h2-console/login.jsp?jsessionid=de8aa4ea0a3ae8c6468f8d1fe65bbce0
and set the url=jdbc:h2:mem: with the value from ** that is present in console at startup:
example: HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:**c60775fd-a3b8-48ca-a013-05a2c4ccb91d** user=SA

Connect to the database to check the record already inserted.

CALLS:
http://localhost:8080/customers
The service will return all the customers when calling the API 

http://localhost:8080/customersTemplate
The customers can also be viewed directly in the browser with the url via a template


########################
GET
http://localhost:8080/customer/1
DESCRIPTION: the service will find the customer by id when calling the API with a GET request that contains 
the id of the customer

#######################
GET
http://localhost:8080/customer?lastName=Evans
OR
http://localhost:8080/customer?firstName=Eric
DESCRIPTION: The service will find the customer by lastName or by firstName of the customer

#######################
POST
http://localhost:8080/customer
DESCRIPTION: The service will create new customer

example body: {"id":3,"firstName":"Vila","lastName":"Me","age":19,"email":"fdfs@asd.com","address":{"id":3,"city":"Nowhere","streetName":"Street x","streetNo":"111" ,"postalCode":"232322", "country" : "XXxx"}}

########################
PUT
http://localhost:8080/customer
DESCRIPTION: The service will update an existing customer

example body: {"id":3,"firstName":"Vila","lastName":"Me","age":19,"email":"fdfs@asd.com","address":{"id":3,"city":"Nowhere","streetName":"Street x","streetNo":"222" ,"postalCode":"232322", "country" : "XXxxYYYYY"}}

