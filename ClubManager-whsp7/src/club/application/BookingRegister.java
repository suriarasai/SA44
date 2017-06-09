package club.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import club.exception.BadBookingException;
import club.model.Booking;
import club.model.Facility;
import club.model.Member;


public class BookingRegister {
private HashMap<Facility, ArrayList<Booking>> bookings;


    public BookingRegister() {
        bookings = new HashMap<Facility, ArrayList<Booking>> ();
    }

    public void addBooking (Member member, Facility facility, Date startDate, Date endDate)
				throws BadBookingException {
        Booking b = new Booking (member, facility, startDate, endDate);
        ArrayList<Booking> bookingList = bookings.get (facility);
        if (bookingList == null) {
        	bookingList = new ArrayList<Booking> ();
            bookings.put (facility, bookingList);
        } else {
            Iterator<Booking> i = bookingList.iterator ();
            while (i.hasNext ()) {
                Booking other = i.next();
                if (b.overlaps(other)) {
                    throw new BadBookingException ("New booking overlaps with existing one");
                }
            }
        }
        bookingList.add (b);
    }

    public void removeBooking (Booking booking) {
    	ArrayList<Booking> bookingList = bookings.get (booking.getFacility());
        if (bookingList != null) {
        	bookingList.remove (booking);
        }
    }

    public ArrayList<Booking> getBookings (Facility facility,
				Date startDate, Date endDate) {
    	ArrayList<Booking> selected = new ArrayList<Booking> ();
        ArrayList<Booking> bookingList = bookings.get (facility);
        if (bookingList != null) {
            Iterator<Booking> i = bookingList.iterator ();
            while (i.hasNext ()) {
                Booking b = i.next();
                boolean earlier = startDate.after (b.getEndDate());
                boolean later = endDate.before (b.getStartDate());
                if (!(earlier || later)) {
                	selected.add (b);
                }
            }
        }
        return selected;
    }

}
