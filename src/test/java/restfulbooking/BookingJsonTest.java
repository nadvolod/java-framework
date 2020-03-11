package restfulbooking;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingJsonTest
{
	DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	public void createBookingJsonFromObject()
	{
		Booking booking = new Booking();
		booking.firstname = "Aaron";
		booking.lastname = "Evans";
		booking.totalprice = 100;
		booking.depositpaid = true;
		booking.additionalneeds = "none";
		booking.bookingdates = new BookingDates();
		booking.bookingdates.checkin = LocalDate.parse("2020-03-06", format);
		booking.bookingdates.checkout = LocalDate.parse("2020-03-07", format);

		System.out.println(booking);
	}

	@Test
	public void createBookingObjectFromJson()
	{
		String json1 = "{\"firstname\":\"Aaron\",\"lastname\":\"Evans\",\"totalprice\":100,\"depositpaid\":true,\"additionalneeds\":\"none\",\"bookingdates\":{\"checkin\":{\"year\":2020,\"month\":3,\"day\":6},\"checkout\":{\"year\":2020,\"month\":3,\"day\":6}}}";
		String json2 = "{\"firstname\":\"James\",\"lastname\":\"Watso\",\"totalprice\":123,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2017-12-31\",\"checkout\":\"2018-12-31\"},\"additionalneeds\":\"Breakfast\"}";
		Booking booking = Booking.fromJsonString(json2);
		System.out.println(booking.toString());
		System.out.println(booking.firstname);
		System.out.println(booking.lastname);
		System.out.println(booking.totalprice);
		System.out.println(booking.depositpaid);
		System.out.println(booking.additionalneeds);
		System.out.println(booking.bookingdates.checkin);
		System.out.println(booking.bookingdates.checkout);
	}
}
