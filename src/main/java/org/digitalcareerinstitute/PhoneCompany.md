This program calculates the monthly cost of a phone billing plan based on the user's chosen plan, expected usage, and customer status (new or existing).

### 1. Package and Imports
At the top, we have:
```java
package org.digitalcareerinstitute;
import java.text.DecimalFormat;
import java.util.Scanner;
```

- `package org.digitalcareerinstitute;`: This organizes classes into packages. Think of packages as folders that group similar classes together. This class is part of the `org.digitalcareerinstitute` package.
- `import java.text.DecimalFormat;`: This allows us to use `DecimalFormat`, which formats numbers to specific decimal places, like `10.50` instead of `10.5`.
- `import java.util.Scanner;`: The `Scanner` class allows us to read user input.

### 2. Class and Constants Definition
Next, we define the class and some constants:
```java
public class PhoneCompany {
    public static final String QUERY_PLAN = "Which billing plan are you using?";
    public static final String QUERY_MINUTES = "How many call-minutes do you plan to use each month?";
    public static final String QUERY_NEW_CUSTOMER = "Are you a new customer?";
```

- **Class Definition**: `public class PhoneCompany` defines the class. Classes in Java are like blueprints for objects, containing methods and properties.
- **Constants**: Constants are values that don’t change. They are defined with `public static final`. Here, we have constants like `QUERY_PLAN` for asking which plan the user has, `QUERY_MINUTES` for asking about call minutes, and `QUERY_NEW_CUSTOMER` for asking if the user is new.

### 3. Plan Details Constants
```java
public static final String RESIDENTIAL = "Residential";
public static final double RESIDENTIAL_BASE = 10.0;
public static final double RESIDENTIAL_FREE = 120;
public static final double RESIDENTIAL_MINUTE = 0.1;
public static final double RESIDENTIAL_OFFER_LIMIT = 20.0;

public static final String COMMERCIAL = "Commercial";
public static final int COMMERCIAL_LIMIT = 300;
public static final double COMMERCIAL_RATE = 0.15;
public static final double COMMERCIAL_REDUCED = 0.07;

public static final double LOYALTY_COST= 75.0;
public static final double LOYALTY_LIMIT = 1000;
```

- **Plan Constants**: These constants specify different billing details for "Residential" and "Commercial" plans:
    - `RESIDENTIAL_BASE`, `RESIDENTIAL_FREE`, and `RESIDENTIAL_MINUTE` define the base cost, free minutes, and cost per minute over the free minutes for a Residential plan.
    - `COMMERCIAL_LIMIT`, `COMMERCIAL_RATE`, and `COMMERCIAL_REDUCED` specify the cost structure for a Commercial plan.
    - `LOYALTY_COST` and `LOYALTY_LIMIT` define a special rate for loyal (existing) customers using more than 1000 minutes.

### 4. Main Method
This is the main entry point for the program:
```java
public static void main(String[] args) {
    PhoneCompany phoneCompany = new PhoneCompany();
```

- **Instance Creation**: `PhoneCompany phoneCompany = new PhoneCompany();` creates an instance of the `PhoneCompany` class so we can call its methods.

### 5. Taking User Input
```java
    Scanner scanner = new Scanner(System.in);
    System.out.println(QUERY_PLAN);
    String plan = scanner.nextLine();
    System.out.println(QUERY_MINUTES);
    int minutes = scanner.nextInt();
    scanner.nextLine();
    System.out.println(QUERY_NEW_CUSTOMER);
    String newCustomerInput = scanner.nextLine();
```

- **Scanner**: `Scanner scanner = new Scanner(System.in);` creates a `Scanner` object to read inputs from the user.
- The user is asked for their `plan`, `minutes`, and `newCustomer` status.

### 6. Calculating Costs and Outputting the Result
```java
    boolean isNewCustomer = newCustomerInput.equalsIgnoreCase("yes") || newCustomerInput.equalsIgnoreCase("y");
    DecimalFormat format = new DecimalFormat("#.##");
    double costs = phoneCompany.calculateCost(plan, minutes, isNewCustomer);
    System.out.println("This plan will cost you an estimated " + format.format(costs) + "€ each month.");
```

- **Customer Check**: `isNewCustomer` is `true` if the user said "yes" or "y" and `false` otherwise.
- **Decimal Format**: `DecimalFormat format = new DecimalFormat("#.##");` formats the calculated cost to two decimal places.
- **Calling calculateCost**: `phoneCompany.calculateCost(plan, minutes, isNewCustomer)` calculates the monthly cost based on user input, and `System.out.println()` prints the result.

### 7. calculateCost Method
```java
private double calculateCost(String plan, int minutes, boolean newCustomer) {
    if (!newCustomer && minutes > LOYALTY_LIMIT) {
        return LOYALTY_COST;
    }
    if (plan.equalsIgnoreCase(RESIDENTIAL)) {
        return calculateResidentialCost(minutes, newCustomer);
    }
    if (plan.equalsIgnoreCase(COMMERCIAL)) {
        return calculateCommercialCost(minutes, newCustomer);
    }
    throw new IllegalArgumentException("Invalid plan: " + plan);
}
```

- This method decides which plan's cost calculation to use based on `plan`.
- **Loyalty Discount**: If the user isn’t new and has more than 1000 minutes, they get a special cost (`LOYALTY_COST`).
- If `plan` is `Residential`, it calls `calculateResidentialCost()`, and for `Commercial`, it calls `calculateCommercialCost()`.
- **Invalid Plan**: If `plan` is neither Residential nor Commercial, an exception is thrown.

### 8. calculateResidentialCost Method
```java
private double calculateResidentialCost(int minutes, boolean newCustomer) {
    double cost = RESIDENTIAL_BASE;
    if (minutes > RESIDENTIAL_FREE) {
        cost += (minutes - RESIDENTIAL_FREE) * RESIDENTIAL_MINUTE;
    }
    if (newCustomer) {
        System.out.println("New residential customers get free service for the first 2 months.");
    }
    return cost;
}
```

- **Base Cost**: Starts with a base cost of 10.0 for Residential.
- **Extra Minutes Cost**: If minutes exceed the free limit (120), each extra minute costs `RESIDENTIAL_MINUTE`.
- **New Customer Offer**: New customers get a message about a free two-month service.

### 9. calculateCommercialCost Method
```java
private double calculateCommercialCost(int minutes, boolean newCustomer) {
    double cost = 0.0;
    if (minutes <= COMMERCIAL_LIMIT) {
        cost += minutes * COMMERCIAL_RATE;
    }
    if (minutes > COMMERCIAL_LIMIT) {
        cost += COMMERCIAL_LIMIT * COMMERCIAL_RATE;
        cost += (minutes - COMMERCIAL_LIMIT) * COMMERCIAL_REDUCED;
    }
    if (newCustomer) {
        System.out.println("New commercial customers get free service for the first 3 months.");
    }
    return cost;
}
```

- **Base Cost Calculation**: For Commercial plans, the first 300 minutes cost `0.15` per minute. Any extra minutes are charged at `0.07`.
- **New Customer Offer**: New customers get a message about a free three-month service.

### Summary
This program uses the Java concepts of constants, user input, conditions, and methods to estimate monthly phone bill costs based on plan type, minutes used, and customer status.