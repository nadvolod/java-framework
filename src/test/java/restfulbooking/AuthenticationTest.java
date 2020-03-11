package restfulbooking;

import kong.unirest.Headers;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationTest
{
	@Test
	public void shouldAuthenticateSuccessfully()
	{
		HttpResponse<String> response = Unirest.post("https://restful-booker.herokuapp.com/auth")
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.body("{ \"username\" : \"admin\", \"password\" : \"password123\" }")
				.asString();

		int status = response.getStatus();
		Headers headers = response.getHeaders();
		String body = response.getBody();

		System.out.println(status);
		System.out.println(headers);
		System.out.println(body);

		assertThat(status).isEqualTo(200);
		assertThat(headers.getFirst("Content-type")).startsWith("application/json");
		assertThat(body).isNotEmpty();
		assertThat(body).contains("token");
	}
}
