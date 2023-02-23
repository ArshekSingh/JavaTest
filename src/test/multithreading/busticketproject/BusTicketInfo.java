package test.multithreading.busticketproject;

public class BusTicketInfo {

    public static void main(String[] args) {

        TicketCounter ticketCounter = new TicketCounter();
        TicketBookingThread t1 = new TicketBookingThread(ticketCounter, "Sham", 2);
        TicketBookingThread t2 = new TicketBookingThread(ticketCounter, "Ram", 2);

        t1.start();
        t2.start();
    }
}
