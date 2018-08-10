package qaaccelerator;

import java.util.HashMap;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;

public class TestData
{
    /**
     * Load FunctionalTest Cases from QA Automation Accelerator for Execution
     */
    public TestData()
    {
        Response r = given()
                .contentType("application/json")
                .get();

        String body = r.getBody().asString();

    }

    /**
     *
     * @param testCaseName FunctionalTest Case name to fetch tests data
     * @return tests case details for execution
     */

    public HashMap<String,String> GetTestCase(String testCaseName)
    {
        return null;
    }
}
