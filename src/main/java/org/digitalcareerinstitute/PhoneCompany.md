Here is an explanation of each part of the code in detail.

---

### 1. Package and Imports
The package and import statements at the beginning of the code set up the environment for the program.

```java
package org.digitalcareerinstitute;

import java.text.DecimalFormat;
import java.util.Scanner;
```

- **`package org.digitalcareerinstitute;`**: This line defines a package, a way to organize related classes. Here, `PhoneCompany` is inside the `org.digitalcareerinstitute` package.
- **`import java.text.DecimalFormat;`**: This import lets us use `DecimalFormat` to format decimal numbers with two decimal places.
- **`import java.util.Scanner;`**: This import allows us to use the `Scanner` class to read input from the user.

---

### 2. Class Declaration
The class `PhoneCompany` contains all the code related to calculating and displaying phone plan costs.

```java
public class PhoneCompany {
```

- **`public class PhoneCompany`**: Defines a public class named `PhoneCompany` that can be accessed by other classes.

---

### 3. Constants
Constants are defined for each type of phone plan and rates. Constants are marked by the `final` keyword, meaning their values can't be changed after assignment.

```java
public static final String QUERY_PLAN = "Which billing plan are you using?";
public static final String QUERY_MINUTES = "How many call-minutes do you plan to use each month?";
public static final String QUERY_NEW_CUSTOMER = "Are you a new customer?";

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

- **Query Constants** (`QUERY_PLAN`, `QUERY_MINUTES`, `QUERY_NEW_CUSTOMER`): These store the questions asked of the user.
- **Residential Plan Constants** (`RESIDENTIAL`, `RESIDENTIAL_BASE`, etc.): Define values specific to a residential plan.
  - `RESIDENTIAL_BASE`: The fixed base cost for the plan (€10).
  - `RESIDENTIAL_FREE`: Free minutes available (120).
  - `RESIDENTIAL_MINUTE`: Cost per minute after exceeding free minutes (€0.1).
- **Commercial Plan Constants** (`COMMERCIAL`, `COMMERCIAL_LIMIT`, etc.): Define values specific to a commercial plan.
  - `COMMERCIAL_LIMIT`: The limit for regular rate billing (300 minutes).
  - `COMMERCIAL_RATE`: Cost per minute within the limit (€0.15).
  - `COMMERCIAL_REDUCED`: Cost per minute after exceeding the limit (€0.07).
- **Loyalty Constants** (`LOYALTY_COST`, `LOYALTY_LIMIT`): For loyal customers who use more than 1000 minutes, they get a fixed cost (€75).

---

### 4. Main Method
The `main` method runs the program and gathers input from the user.

```java
public static void main(String[] args) {
    PhoneCompany phoneCompany = new PhoneCompany();

    // query user input
    Scanner scanner = new Scanner(System.in);
    System.out.println(QUERY_PLAN);
    String plan = scanner.nextLine();
    System.out.println(QUERY_MINUTES);
    int minutes = scanner.nextInt();
    scanner.nextLine();
    System.out.println(QUERY_NEW_CUSTOMER);
    String newCustomerInput = scanner.nextLine();

    DecimalFormat format = new DecimalFormat("#.##");
    double costs = phoneCompany.calculateCost(plan, minutes, phoneCompany.isNewCustomer(newCustomerInput));
    System.out.println("This plan will cost you an estimated " + format.format(costs) + "€ each month.");
}
```

- **Creating a `PhoneCompany` object**: The object `phoneCompany` is used to call methods within this class.
- **Using `Scanner`**: `Scanner` reads user inputs for:
  - `plan` - Type of plan (Residential or Commercial).
  - `minutes` - Estimated call minutes the user expects.
  - `newCustomerInput` - Checks if the user is new.
- **`DecimalFormat`**: Used to format the cost value to 2 decimal places.
- **`calculateCost` Call**: The `calculateCost` method calculates the plan cost based on the user’s inputs.

---

### 5. The `calculateCost` Method
Calculates the cost based on the plan, minutes, and whether the user is new.

```java
private double calculateCost(String plan, int minutes, boolean newCustomer) {
    // loyalty discount for long-term customers
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

- **Loyalty Discount**: If the user is not a new customer and uses more than 1000 minutes, a fixed cost (€75) is applied.
- **Plan Check**: Calls either `calculateResidentialCost` or `calculateCommercialCost` based on the plan type.
- **Error Handling**: If an invalid plan is entered, throws an `IllegalArgumentException`.

---

### 6. The `calculateResidentialCost` Method
Calculates the cost for a residential plan based on the minutes and new customer status.

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

- **Base Cost**: Starts with a base cost (€10).
- **Extra Minutes**: If the user exceeds 120 minutes, they are charged €0.1 for each extra minute.
- **New Customer Offer**: Prints a message if the user is a new customer, offering 2 months free.

---

### 7. The `calculateCommercialCost` Method
Calculates the cost for a commercial plan based on the minutes and new customer status.

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

- **Within Limit**: Charges €0.15 per minute if usage is within 300 minutes.
- **Exceeds Limit**: If usage exceeds 300 minutes, charges €0.15 for the first 300 and €0.07 for each additional minute.
- **New Customer Offer**: Prints a message if the user is new, offering 3 months free.

---

### 8. The `isNewCustomer` Method
Checks if the customer is new.

```java
private boolean isNewCustomer(String newCustomerInput) {
    return newCustomerInput.equalsIgnoreCase("yes") || newCustomerInput.equalsIgnoreCase("y");
}
```

- **New Customer Check**: Returns `true` if the user input is "yes" or "y", otherwise `false`.

---

Overall, this program guides the user through selecting a plan, estimating usage, and determining if they're eligible for any discounts or offers based on the selected plan.