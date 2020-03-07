package restfulbooking;

import com.google.gson.*;

import java.time.LocalDate;

public class Booking
{
	static Gson gson = new GsonBuilder()
			.setPrettyPrinting().setDateFormat("yyyy-MM-Dd")
			.registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter())
			.create();

	public String firstname;
	public String lastname;
	public Number totalprice;
	public Boolean depositpaid;
	public String additionalneeds;
	public BookingDates bookingdates;

	public static Booking fromJsonObject(JsonObject jsonObject)
	{
		return gson.fromJson(jsonObject, Booking.class);
	}

	public static Booking fromJsonString(String jsonString)
	{
		return gson.fromJson(jsonString, Booking.class);
	}

	public JsonObject toJson()
	{
		return gson.toJsonTree(this).getAsJsonObject();
	}

	public String toString()
	{
		return toJson().toString();
	}

}
