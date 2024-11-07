package org.digitalcareerinstitute;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PhoneCompany {

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

        boolean isNewCustomer =
            newCustomerInput.equalsIgnoreCase("yes") ||
            newCustomerInput.equalsIgnoreCase("y");
        DecimalFormat format = new DecimalFormat("#.##");
        double costs = phoneCompany.calculateCost(plan, minutes, isNewCustomer);
        System.out.println("This plan will cost you an estimated " + format.format(costs) + "€ each month.");
    }

    /**
     * Calculate the cost for a given plan
     *
     * @param plan        the plan to calculate the cost for
     * @param minutes     the number of minutes the customer plans to use
     * @param newCustomer whether the customer is new
     * @return the cost of the plan
     * @throws IllegalArgumentException if the plan is invalid
     */
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

    /**
     * Calculate the cost for a commercial plan
     *
     * @param minutes     the number of minutes the customer plans to use
     * @param newCustomer whether the customer is new
     * @return the cost of the plan
     */
    private double calculateResidentialCost(int minutes, boolean newCustomer) {
        // base cost for residential plans
        double cost = RESIDENTIAL_BASE;

        // apply rate for exceeding minutes
        if (minutes > RESIDENTIAL_FREE) {
            cost += (minutes - RESIDENTIAL_FREE) * RESIDENTIAL_MINUTE;
        }

        // offer for new customers
        if (newCustomer) {
            System.out.println("New residential customers get free service for the first 2 months.");
        }

        return cost;
    }

    /**
     * Calculate the cost for a commercial plan
     *
     * @param minutes     the number of minutes the customer plans to use
     * @param newCustomer whether the customer is new
     * @return the cost of the plan
     */
    private double calculateCommercialCost(int minutes, boolean newCustomer) {
        double cost = 0.0;

        // calculate cost for minutes
        if (minutes <= COMMERCIAL_LIMIT) {
            cost += minutes * COMMERCIAL_RATE;
        }

        // apply cost for minutes & additional rate for exceeding minutes
        if (minutes > COMMERCIAL_LIMIT) {
            cost += COMMERCIAL_LIMIT * COMMERCIAL_RATE;
            cost += (minutes - COMMERCIAL_LIMIT) * COMMERCIAL_REDUCED;
        }

        // apply discount for new customers
        if (newCustomer) {
            System.out.println("New commercial customers get free service for the first 3 months.");
        }

        return cost;
    }
}
