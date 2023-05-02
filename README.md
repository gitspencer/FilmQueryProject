# FilmQueryProject

# Description
Command-line application that makes queries to a MySQL database based on a search by either id# or keyword.

# Technologies Used
 - Java
 - Eclipse
 - SQL
 - Maven
 - Git

 
# Lessons Learned
 - Initial setup included constructing classes based on the tables to be accessed within MySQL (Film, Actor, Language). The classes contained private fields matched to each row of data in the SQL tables.  Each class also consisted of: a no-arg constructor, constructor with matching parameters to the SQL table, getters and setters for each field, a toString() as well as hashCode() and equals() methods.
 
 - The DatbaseAccessor is an interface implemented by the DatabaseAccessorObject("DAO") class, methods for the DAO are declared in the interface.
 
 - DAO is the only class that accesses the database. URL and MySQL login criteria are assigned as private fields. Methods in this class contain all logic within try catch blocks to handle SQLExceptions.
 - findFilmById() & findActorById() both take an int value, connect to MySQL via a prepared statement and receive a result set from the query which builds an object (Film or Actor) based on the search which is ultimately the return value.  
 - findActorsByFilmId() is similar to the earlier two methods but builds an ArrayList of actors that were in a specific film.
 - findFilmByKeyword() takes a String value searches the database for any film where either the title or description contains a keyword, ultimately returning a list of films that match the search.
 - findLanguage() takes a film id and returns a language object based on the query.
 
 - FilmQueryApp contains the main as well as a test() and launch() method for troubleshooting and running the project, respectively. The launch() method calls for startUserInterface() which deploys a Scanner, prints a simple menu and logic for the menu is contained in a 'do while' loop which will run until 3 is selected to Exit or an invalid input type is entered (i.e. String in a Scanner that takes an int).
 - printFilmFromId() asks the user for a film id, takes in an int from user input and accesses the DAO to retrieve a Film, List of Actors and Language if the film id is in the database. If so, details of the film will print to the screen, including all actors in the film, language, description, release year, title, and its rating. Actors are returned as an array list so the list is iterated through with a 'for each' loop to print out each actor. If no film is found an error message is printed and after every search the original menu is displayed accepting options 1(id search), 2(keyword search), and 3(exit) again. 
 - printFilmFromKeyword() has the same logic as printFilmFromId() above but accepts a string input as a keyword for its search of the database. The output is identical.
 