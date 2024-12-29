package com.bona.entity;

public class Rider {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String pickupLocation;
    private String dropOffLocation;
    private String pickupDate;
    private String pickupTime;
    private int numPassengers;
    private boolean requireWheelchairVan;
    private String requireChildSeat;
    private String paymentType;
    private String confirmationRequest;
    private boolean bookReturn;
    private double price; // Added price field

    // Default constructor
    public Rider() {
    }

    // Parameterized constructor
    public Rider(int id, String firstName, String lastName, String email, String phoneNumber,
                 String pickupLocation, String dropOffLocation, String pickupDate, String pickupTime,
                 int numPassengers, boolean requireWheelchairVan, String requireChildSeat,
                 String paymentType, String confirmationRequest, boolean bookReturn, double price) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.numPassengers = numPassengers;
        this.requireWheelchairVan = requireWheelchairVan;
        this.requireChildSeat = requireChildSeat;
        this.paymentType = paymentType;
        this.confirmationRequest = confirmationRequest;
        this.bookReturn = bookReturn;
        this.price = price; // Initialize price in the constructor
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public void setNumPassengers(int numPassengers) {
        this.numPassengers = numPassengers;
    }

    public boolean isRequireWheelchairVan() {
        return requireWheelchairVan;
    }

    public void setRequireWheelchairVan(boolean requireWheelchairVan) {
        this.requireWheelchairVan = requireWheelchairVan;
    }

    public String getRequireChildSeat() {
        return requireChildSeat;
    }

    public void setRequireChildSeat(String requireChildSeat) {
        this.requireChildSeat = requireChildSeat;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getConfirmationRequest() {
        return confirmationRequest;
    }

    public void setConfirmationRequest(String confirmationRequest) {
        this.confirmationRequest = confirmationRequest;
    }

    public boolean isBookReturn() {
        return bookReturn;
    }

    public void setBookReturn(boolean bookReturn) {
        this.bookReturn = bookReturn;
    }

    public double getPrice() { // Getter for price
        return price;
    }

    public void setPrice(double price) { // Setter for price
        this.price = price;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pickupLocation='" + pickupLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", pickupDate='" + pickupDate + '\'' +
                ", pickupTime='" + pickupTime + '\'' +
                ", numPassengers=" + numPassengers +
                ", requireWheelchairVan=" + requireWheelchairVan +
                ", requireChildSeat='" + requireChildSeat + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", confirmationRequest='" + confirmationRequest + '\'' +
                ", bookReturn=" + bookReturn +
                ", price=" + price +
                '}';
    }
}

