## Prerequisite
1. JAVA 17
2. springboot framework 2.7
3. maven
4. spring data jpa 
5. spring web
6. postgresql 

## database
1. allocate three ports for three data agents
2. create database node1/node2/node3 on corresponding ports

## test case
By sending the restful request to the agents, 
the client can control and imitate different situations
that concurrent transactions might have 

### a normal test case
GET localhost:8080/addnode1?message=hi

GET localhost:8080/addnode1?message=hi

GET localhost:8080/addnode1?message=hi

GET localhost:8080/deletenode1?id=2

GET localhost:8080/read

GET localhost:8080/commit

## explanation
This test case imitate a situation that:\
A transaction including operations (adding three new node and deleting one of it)\
After that, the client send read and commit request.
The final result is: the client read the most recent committed data object\
All three nodes have the same data and logs in the end.

