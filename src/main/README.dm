# The methodology of programming I. EC - C1 group, assignment

## Car  mechanic registry program

This is a registry program, that can be used at a car mechanic.
The main goal of this project is to help to store and arrange the customers of the
repair shop. With the menu you can easly list all the current customers, add a new one, modify them,
remove them, and enumerate all the possible problems of a car. IMPORTANT!!! >>> This program works with cars!
If you try to add a customer that has problem with bycicle, motorcycle, boat, plane or any other means of transport,
the effectiveness of the program is not granted, because of the specification of the source of the problem!


## Instructions

After you start the program, you should see the menu and the choosable numbers with the task of what they are doing.
* 1 - List customers
* 2 - Add new customer
* 3 - Modify a customer
* 4 - Delete a customer
* 5 - Enumerate all selectable problem
* 0 - Save and Exit

Under them you see "Enter the number here: ". If you have choosen the task you want to execute,
click next to the bottom text and enter it's number.

The number and the tasks for it:

* 1 - List customers >>> The program will enumerate all of the current customers.
* 2 - Add new customer >>> You can add a new customer to the database.
* 3 - Modify a customer >>> You can modify a current existing customer.
* 4 - Delete a customer >>> You can delete a current existing customer.
* 5 - Enumerate all selectable problem >>> The program will enumerate all the problems you can add to a customer.
* 0 - Save and Exit >>> The program will save the current status than exit.

There is 7 information that a customer has:
* Customer name >>> The name of the customer.
* Phone number >>> The customer's phone number. IMPORTANT!!! If you have to add a phone number to a cusstomer, it must be hungarian and
as shown, you shall not write the "+36" part, just for example "12 1234 567".
* Brand >>> The customer's car's brand.
* License plate >>> The customer's car's license plate. IMPORTANT!!! The license number's scheme must be like this "AAA-111" (hungarian).
* Year of manufacture >>> The customer's car's year of manufacture.
* Cost >>> The price of the service.
* Problem >>> The car's problem that needs to be fixed.

##Technical data
The program saves the customers to an xml file named Customers. It uses it to 
read from it too, meaning that what once is saved in that file, is accessable anytime from the program,
or even from the xml itself. The program uses the console and not a GUI.
* The format of the data used:
   * Customer name >>> String
   * Phone number >>> Integer
   * Brand >>> String
   * License plate >>> String
   * Year of manufacture >>> Integer
   * Cost >>> Integer
   * Problem >>> Enum


* The program's strucure:
   * Problems:
     * It is an enum that includes all the possible problems of a car.
   * Customer:
     * It is a scheme for all the customers. It has the infromations that a customer needs. This includes all the getters and setters.
   
   * Customers.xml:
     * This includes all the customers that have been added (and not deleted yet) to the system. You can manually edit them, but you can do that in the program as well.
  
   * MechanicMain:
     * In this part of the program is the majortity of the important things. First the imports that needed. There's a scanner for the input so that the user 
     can communicate with the application. There is a switch case to help to decide, what the user wants to do next.
     The program includes plenty of try and catch part, because of the troubleshooting. The readcustomersfromxml is obviously for reading the customers from the file,
     that has been created with the start of the program. In modifycustomers by the name of the customer (with comparison) the user can modify anyone. At the removecustomer part the user can easly remove the customers one by one.
     In addcustomer the user can add a new customer to the system. Because of the troubleshooting the yearofmanufacture part is necessary, because
     there is a try catch scheme in there. Without it, the code would be more complicated, and same with the inputproblem.
     Listofcustomers is a simple print out. Savecustomerstoxml is a little more complicated. This part of the program generate an xml as "Customers.xml" and when the first customer is ready to be add to
     the system, it creates a scheme and arranges the informations in order. Createchildelement is necessary for savecustomerstoxml

## Author

* Author's name: Lukács Bence
* Author's neptun code: E34BWN