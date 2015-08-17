package mt;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import mt.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MultiTenancyApplication.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class MultiTenancyApplicationTests
{
    @Value("${local.server.port}")
    private int port;

    @Before
    public void before()
    {
        RestAssured.port = port;
    }

    @Test
    public void test()
    {
        createUser("at");
        createUser("hu");

        // @formatter:off
        when().
            get("/at/users").
        then().
            body("username", hasItem("name-at")).
            body("username", not(hasItem("name-hu")));

        when().
            get("/hu/users").
        then().
            body("username", hasItem("name-hu")).
            body("username", not(hasItem("name-at")));
        // @formatter:on
    }

    private static void createUser(String tenant)
    {
        // @formatter:off
        given().
            contentType(ContentType.JSON).
            body(new User("name-" + tenant, "password", true)).
        when().
            post("/" + tenant + "/users")
        .then().
            statusCode(200).
            body("username", is("name-" + tenant));
        // @formatter:on
    }
}
