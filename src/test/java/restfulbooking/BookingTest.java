package restfulbooking;

import kong.unirest.Headers;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BookingTest
{
//	String restfulBookerEndpointBaseURL = "https://restful-booker.herokuapp.com";
	String restfulBookerEndpointBaseURL = "http://localhost:3001";
	String authEndpoint =  restfulBookerEndpointBaseURL + "/auth";
	String bookingEndpoint = restfulBookerEndpointBaseURL + "/booking";

	@Test
	public void shouldReturnListOfBookings()
	{
		JSONArray bookingsList = getBookingsList();

		// assertions are in getBookingsList() helper method
	}

	public JSONArray getBookingsList()
	{
		HttpResponse<JsonNode> response = Unirest.get(bookingEndpoint)
				.header("Accept", "application/json")
				.asJson();

		int status = response.getStatus();
		Headers headers = response.getHeaders();
		JsonNode body = response.getBody();

//		System.out.println(status);
//		System.out.println(headers);
//		System.out.println(body);

		assertThat(status).isEqualTo(200);
		assertThat(headers.getFirst("Content-type")).startsWith("application/json");
		assertThat(body).isNotNull();

		assertThat(body.isArray());

		JSONArray bookings = body.getArray();
		System.out.println("number of bookings: " + bookings.length());

		assertThat(bookings.length()).isNotNegative();

		return bookings;
	}

	@Test
	public void shouldHaveBookingDetailsForEachBooking()
	{
		JSONArray bookings = getBookingsList();

		List<JSONObject> bookingsList = bookings.toList();

		bookingsList.forEach(bookingId ->
		{
			System.out.println(bookingId);

			int id = bookingId.getInt("bookingid");
			System.out.println(id);

			JSONObject bookingDetails = getBookingDetails(id);

			System.out.println(bookingDetails);

			String firstname = bookingDetails.getString("firstname");
			String lastname = bookingDetails.getString("lastname");
			Number totalprice = bookingDetails.getNumber("totalprice");
			boolean depositpaid = bookingDetails.getBoolean("depositpaid");
			JSONObject bookingdates = bookingDetails.getJSONObject("bookingdates");
			String checkin = bookingdates.getString("checkin");
			String checkout = bookingdates.getString("checkout");
//			String additionalneeds = bookingDetails.getString("additionalneeds");

			assertThat(firstname).isNotEmpty();
			assertThat(lastname).isNotEmpty();
			assertThat(totalprice.doubleValue()).isNotNegative();
			assertThat(depositpaid).isNotNull();
			assertThat(bookingdates).isNotNull();

			try
			{
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				LocalDate checkinDate = LocalDate.parse(checkin, format);
				LocalDate checkoutDate = LocalDate.parse(checkout, format);

				LocalDate firstAllowedCheckin = LocalDate.parse("2000-01-01", format);
				LocalDate lastAllowedCheckin = LocalDate.now().plusYears(1);

				assertThat(checkinDate).isBetween(firstAllowedCheckin, lastAllowedCheckin);
			}
			catch (DateTimeParseException e)
			{
				e.printStackTrace();
				fail("unable to parse booking dates");
				throw e;
			}

		});
	}

	public JSONObject getBookingDetails(int bookingId)
	{
		HttpResponse<JsonNode> response = Unirest.get(bookingEndpoint + "/" + bookingId)
				.header("Accept", "application/json")
				.asJson();

		int status = response.getStatus();
		Headers headers = response.getHeaders();
		JsonNode body = response.getBody();

//		System.out.println(status);
//		System.out.println(headers);
//		System.out.println(body);

		assertThat(status).isEqualTo(200);
		assertThat(headers.getFirst("Content-type")).startsWith("application/json");
		assertThat(body).isNotNull();

		assertThat(body.isArray()).isFalse();

		return body.getObject();
	}


	@Test
	public void testDetailsWithObject()
	{
		HttpResponse<String> response = Unirest.get(bookingEndpoint).asString();

		assertThat(response.getStatus()).isEqualTo(200);

		Headers headers = response.getHeaders();
		assertThat(headers.getFirst("Content-type")).startsWith("application/json");

		JSONArray bookingsList = getBookingsList();
		System.out.println(bookingsList);

		List<JSONObject> list = bookingsList.toList();
		list.forEach(booking->{
			System.out.println(booking);

			int id = booking.getInt("bookingid");
			System.out.println(id);

			Booking bookingDetails = getBooking(id);
			System.out.println(bookingDetails);
		});

	}

	@Test
	public void testGettingSingleBookingById()
	{
		Booking booking = getBooking(1);
		System.out.println(booking);
		System.out.println(booking.firstname + " "
				+ booking.lastname + " "
				+ booking.totalprice + " "
				+ booking.depositpaid + " "
				+ booking.additionalneeds + " "
				+ booking.bookingdates.checkin + " "
				+ booking.bookingdates.checkout);

		assertThat(booking.firstname).isNotEmpty();
		assertThat(booking.lastname).isNotEmpty();
		assertThat(booking.totalprice.doubleValue()).isNotNegative();
		assertThat(booking.depositpaid).isNotNull();
		assertThat(booking.bookingdates).isNotNull();
		assertThat(booking.bookingdates.checkin).isNotNull();
		assertThat(booking.bookingdates.checkout).isNotNull();
	}

	public Booking getBooking(int id)
	{
		HttpResponse<String> response = Unirest.get(bookingEndpoint + "/" + id)
				.header("Accept", "application/json")
				.asString();

		int status = response.getStatus();
		Headers headers = response.getHeaders();
		String body = response.getBody();

//		System.out.println(status);
//		System.out.println(headers);
//		System.out.println(body);

		return Booking.fromJsonString(body);
	}


}
