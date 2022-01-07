# Practicing TDD to implement a password generator.

##### Level 1 - Junior
Please make the tests pass following the sequence below :
1) ConstraintTest.java
2) RequirementTest.java
3) BuilderTest.java
4) ElectorTest.java
5) GeneratorTest.java

##### Level 2 - Junior to mid-level
When it's done, you got a working password generator, but it would be great to deploy it online.
Wrap the solution in a microservice (Spring Boot + OpenAPI) and deploy it onto Heroku.

##### Level 3 - Mid-level
To make sure the solution does not generate a weak or famous password, you can load a text file that contains forbidden
passwords (such as "password123") and use the appropriate data structure to query this password collection.

##### Level 4 - Mid-level to Senior
To make it better, you implement a solution to ensure the balance between each kind of character
so that the solution does not generate a password with a lot of figures for instance.
You are not looking for a perfect balance!

##### Level 5 - Senior
You bring a feature that will perform checks along the password generation, not at the end.
We want to make sure that the password (being generated) does not follow the path of a weak or famous password.
To do so, you may need to store the weak passwords in another kind of data structure, more convenient when it comes to
checking word prefixes (think about an auto-completion system, you will find the right data structure).

##### Level 6 - Expert
You use a particular data structure in the previous step and for some reasons, you choose to not implement the data
structure by yourself. Now it's time to implement this data structure by yourself and remove the dependency you used.