package com.hotel.service;

import com.hotel.model.Customer;
import java.util.*;

public class CustomerService {
    // Singleton instance
    private static final CustomerService INSTANCE = new CustomerService();

    // Store customers by email (unique key)
    private final Map<String, Customer> customers;

    private CustomerService() {
        customers = new HashMap<>();
    }

    public static CustomerService getInstance() {
        return INSTANCE;
    }

    // Add a new customer
    public void addCustomer(String email, String firstName, String lastName) {
        // Check if customer already exists
        if (customers.containsKey(email.toLowerCase())) {
            throw new IllegalArgumentException("Customer with email " + email + " already exists");
        }

        // Create and store new customer
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email.toLowerCase(), customer);
        System.out.println("Account created for: " + firstName + " " + lastName);
    }

    // Get customer by email
    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail.toLowerCase());
    }

    // Get all customers
    public Collection<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    // Check if customer exists
    public boolean customerExists(String email) {
        return customers.containsKey(email.toLowerCase());
    }

    // Get total number of customers
    public int getTotalCustomers() {
        return customers.size();
    }

    // Print all customers (for admin)
    public void printAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("\n=== ALL CUSTOMERS ===");
        System.out.println("Total customers: " + customers.size());
        System.out.println("---------------------");

        int count = 1;
        for (Customer customer : customers.values()) {
            System.out.println(count + ". " + customer.getFirstName() + " " +
                    customer.getLastName() + " (" + customer.getEmail() + ")");
            count++;
        }
    }
}