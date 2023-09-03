# LumenTraining_Project
This is a basic Food Menu usecase. The food item have the following fields : name, foodType, price and available.  There are currently 6 endpoints :

* Fetching all the available food items 
* Adding a new food item
* Finding a food item by name 
* Updating a food item (consumes payload in XML format)
* Getting an image of the food item (currently possible for 2 items)
* Description of the food item (currently possible for 3 items)


The Vulnerabilities that are addressed in the code for now :  
* SQL Injection -  using positional placeholders in the queries with JPA. Common validations on the entity fields also mitigate this attack
* Cross-Site Scripting - This is also overcome using the validations on the entity fields. There are response headers also in place for this.
* Directory Traversal Attack - This is prevented in case of both image and description APIs by sanitisation and validation of the food item name provided. This is possible in these APIs as we are currently making use of files to get the data for these APIs
* Command/Shell Injection Attack - This is possible in the description APIs, it is mitigated by the use of ProcessBuilder to run the Operating System command to fetch the requested data
* Vulnerable and Outdated Components - Used the latest and least vulnerable and compatible dependencies. 
