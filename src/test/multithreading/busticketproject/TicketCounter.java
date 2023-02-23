package test.multithreading.busticketproject;

public class TicketCounter {

    int availableSeats = 3;

    public synchronized void bookSeats(String name, int seats) {
        if(availableSeats >= seats && seats > 0) {
            System.out.println(name + " " + seats + " are booked");
            availableSeats = availableSeats - seats;
        }
        else {
            System.out.println(name + " no seats are available");
        }
    }

}
