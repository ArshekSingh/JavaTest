package test.multithreading.busticketproject;

public class TicketBookingThread extends Thread{

    TicketCounter ticketCounter;
    String name;
    int seats;

    public TicketBookingThread(TicketCounter ticketCounter, String name, int seats) {
        this.ticketCounter = ticketCounter;
        this.name = name;
        this.seats = seats;
    }

    public void run() {
        ticketCounter.bookSeats(name, seats);
    }
}
