package be.souk.models;

import java.util.Comparator;

public class BookingComparator {

	static Comparator<Booking> borrowerCreditComp = new Comparator<Booking>() {

		@Override
		public int compare(Booking o1, Booking o2) {
			
			return o2.getBorrower().getCredit() - o1.getBorrower().getCredit();
		}
	};
	
	static Comparator<Booking> bookingDateComp = new Comparator<Booking>() {
		
		@Override
		public int compare(Booking o1, Booking o2) {
			
			return o1.getBookingDate().compareTo(o2.getBookingDate());
		}
	};
	
	static Comparator<Booking> borrowerSeniorityComp = new Comparator<Booking>() {
		
		@Override
		public int compare(Booking o1, Booking o2) {
			
			return o1.getBorrower().getRegistrationDate().compareTo(o2.getBorrower().getRegistrationDate());
		}
	};
	
	static Comparator<Booking> borrowerAgeComp = new Comparator<Booking>() {
		
		@Override
		public int compare(Booking o1, Booking o2) {
			
			return o2.getBorrower().getAge()-o1.getBorrower().getAge();
		}
	};

}
