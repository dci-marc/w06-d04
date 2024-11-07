[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/j1HvILtT)
# Telephone company

## Description

In this exercise, we will practice on more complex logical expressions including
various boolean expressions and operators.

## Tasks

### Base plans

Your company offers different phone contracts to customers. We will to build a program that allows a customer to calculate the monthly cost for their plan.

The program input / output should look like this:
````
Which billing plan are you using? Residential
How many call-minutes do you plan to use each month? 250 
This plan will cost you an estimated 23 € each month.  
````

We'll start with the first 2 plans:
* Residential
    * Charged a monthly flat rate of 10 €
    * Charged a rate of 10 ct / minute for each minute over 120.
* Commercial
    * Charged 15 ct / minute for the first 300 minutes.
    * Charged 7 ct / minute for each minute above 300.

Calculate the estimated monthly cost and return it back to the user.

> Only use if / else conditions and logical expressions for this task. Do not use a switch statement.

### New customers
Your company offers discounts to new customers. Ask the user if he is a new customer and add the following discount information:
* New residential customers get free service for the first 2 months.
* New commercial customers get free service for the first 3 months.

Accept the inputs "yes", "Yes", "YES", "Y" or  "y" for confirmation. You can interpret all other input as "no".

### Upselling
Existing residential customers are eligible for "loyalty limit" if their monthly minutes exceed 1000. If this is the case the customers (residential or commercial) will pay a flat rate of 75 €.