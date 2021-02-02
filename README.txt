# Searching for a Specific User and Updating the User Information -- HCL ASSESSMENT

## Description
Create a Spring MVC web application that will retrieve users based on their user ID. The retrieved user data will then be edited in a form and updated in the database. The entire database processing has to be done using Hibernate. Front pages will be made in JSP.

## Steps
1. Run the SearchUserUpdateUserApplication.java file located in the src/main/java/com/hcl/SearchUserUpdateUser folder.
2. Open your browser and go to http://localhost:8083/allAccounts to view all the accounts **NOTE** you will need to already have account objects in your database. 
3. Go to http://localhost:8083/login and login into one of the accounts shown at the first /allAccounts link. You will be redirected to...
4. http://localhost:8083/updateUser where you can input a new username and password to update the object's information
5. After you click update, you will be redirected to http://localhost:8083/allAcounts where you can see your updated information
6. You can check the terminal for the console input to review log4j information

## Requirements
- Spring MVC with Hibernate, log4j, and MySQL Connector
- Create a JSP page to take in a user 
- Create a controller that will validate the user. If it is invalid, it will display a JSP page with an error message. If it is valid, it will retrieve user details from the database and show an edit form in JSP
- Once the edit form is submitted, the controller will update the details in the database. A confirmation JSP page will be shown
- Follow the standard methodology of creating controllers, services, dao, and entity classes
- Document the step-by-step process involved in completing this task