package club.application;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import club.collection.Club;
import club.dao.FacilityDAO;
import club.dao.MemberDAO;
import club.dao.SimpleDAOFactory;
import club.exception.BadBookingException;
import club.model.Booking;
import club.model.Facility;
import club.model.Member;

public class ClubApplication {

	private Club club = new Club();

	private DateFormat df = new SimpleDateFormat("d-MMM-yyyy H:mm");

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		ClubApplication capp = new ClubApplication();

        // Populate the Club with Members from Member File
        capp.populateMembers();

        // Populate the Club with Facilities from Facility File
        capp.populateFacilities();

        // Print the details of the Club to Club Details File
        capp.printClubDetails();

		// Call Testing of Booking Class
		capp.testBooking();

		// Call Testing of BadBookingException
		capp.testBadBookingException();

		// Call Testing of BookingRegister Class
		capp.testBookingRegister();
	}

    // Populate the Club with Members from Member Table
    private void populateMembers() throws ClassNotFoundException, SQLException {
    	MemberDAO mdao = SimpleDAOFactory.loadInstance().getMemberDAO();
    	ArrayList<Member> mlist = (ArrayList<Member>) mdao.loadAll();
    	club.addMembers(mlist);
    }

    // Populate the Club with Facilities from Facility Table
    private void populateFacilities() throws ClassNotFoundException, SQLException {	
    	FacilityDAO fdao = SimpleDAOFactory.loadInstance().getFacilityDAO();
    	ArrayList<Facility> flist = (ArrayList<Facility>) fdao.loadAll();
    	for (Facility facility : flist) {
    		club.addFacility(facility.getName(), facility.getDescription());
		}
    }

    // Print the details of the Club to Club Details File
    private void printClubDetails() {
    	
    	club.showFacilities();
    	club.showMembers();
       
    }

	// Test Booking Class
	private void testBooking() {
		System.out.println();
		try {
			Booking booking1 = new Booking(club.getMember(1), club
					.getFacility("Room1"), df.parse("1-Aug-2017 09:00"), df
					.parse("1-Aug-2017 12:00"));
			booking1.show();
		} catch (Exception e) {
			System.out.println("Booking class error: " + e.getMessage());
		}

	}

	// Test BadBookingException
	private void testBadBookingException() {
		try {
			Booking booking2 = new Booking(club.getMember(1), club
					.getFacility("Room1"), df.parse("1-Aug-2017 15:00"), df
					.parse("1-Aug-2017 14:00"));
			booking2.show();
		} catch (BadBookingException be) {
			System.out.println("Bad Booking Exception: " + be.getMessage());
		} catch (Exception e) {
			System.out.println("Booking class error: " + e.getMessage());
		}

	}

	// Testing Booking Register
	private void testBookingRegister() {
		try {
			BookingRegister register = new BookingRegister();
			System.out
					.println("Adding bookings for Room1 2-Aug-2017 9:00 to 12:00");
			register.addBooking(club.getMember(1), club.getFacility("Room1"),
					df.parse("2-Aug-2017 9:00"), df.parse("2-Aug-2017 12:00"));
			System.out
					.println("Attempting to add bookings for Room1 in same time as above");
			register.addBooking(club.getMember(2), club.getFacility("Room1"),
					df.parse("2-Aug-2017 9:00"), df.parse("2-Aug-2017 12:00"));
		} catch (BadBookingException be) {
			System.out.println("Bad Booking Exception: " + be.getMessage());
		} catch (Exception e) {
			System.out.println("Booking class error: " + e.getMessage());
		}

	}

}
