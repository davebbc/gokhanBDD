package io.cucumber.stepDefs;

import io.cucumber.Requests.Requests;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private Response response;
    private RequestSpecification request;
    private ValidatableResponse validatableResponse;
    private String hostUri;
    private String objectID;

    public StepDefinitions(){
        hostUri="https://api.restful-api.dev";
        validatableResponse = null;
        request = RestAssured.given();
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = request.when().get(hostUri+endpoint);
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int statusCode) {
        validatableResponse = response.then().statusCode(statusCode);
    }

    @When("I send a POST request to {string} with the object data")
    public void iSendAPOSTRequestToWithTheObjectData(String endPoint) {
        response = request.post(hostUri+endPoint);
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endPoint) {
        response = request.delete(hostUri+endPoint);
    }

    @And("the object name is {string}")
    public void theObjectNameIs(String expectedObjectName) {
        String actualValue = response.jsonPath().get("name");
        assertThat(actualValue).isEqualTo(expectedObjectName);
    }

    @Given("I create a new object with random details")
    public void iCreateANewObjectWithRandomDetails() {
        String requestBody = Requests.createObjectRequestBody();
        request = RestAssured.given()
                .contentType("application/json")
                .body(requestBody);
    }

    @And("I get the object id")
    public void iGetTheObjectId() {
        objectID = response.jsonPath().get("id");
    }

    @When("I delete the object with the object id")
    public void iDeleteTheObject() {
        response = request.delete(hostUri+"/objects/"+objectID);
    }


    @Then("in the response a message is displayed the object has successfully been deleted")
    public void theObjectHasSuccessfullyBeenDeleted() {
        String message = response.jsonPath().get("message");
        assertThat(message).isEqualTo("Object with id = "+objectID+" has been deleted.");
    }

    @Given("I add a new object with the following details:")
    public void iAddANewObjectWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> objectDetails = dataTable.asMap(String.class, String.class);

        String requestBody = "{";
        for (Map.Entry<String, String> entry : objectDetails.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            requestBody += "\"" + key + "\": \"" + value + "\", ";
        }
        requestBody = requestBody.substring(0, requestBody.length() - 2);
        requestBody += "}";
        request = RestAssured.given()
                .contentType("application/json")
                .body(requestBody);
    }

    @And("I update the object with existing data")
    public void iUpdateTheObjectIdWithExistingData() {
        String requestBody = Requests.updateObjectRequestBody();
        request = RestAssured.given()
                .contentType("application/json")
                .body(requestBody);
    }

    @And("I update the object name with {string}")
    public void iUpdateTheObjectNameWith(String name) {
        String requestBody = Requests.updateObjectNameRequestBody(name);
        request = RestAssured.given()
                .contentType("application/json")
                .body(requestBody);
    }

    @And("I send a PUT request to update the object")
    public void iSendAPUTRequest() {
        response = request.put(hostUri + "/objects/" + objectID);
    }

    @When("I send a PUT request to {string} with the updated object data")
    public void iSendAPUTRequestToWithTheUpdatedObjectData(String objectId) {
        String requestBody = Requests.updateObjectRequestBody();
        request = RestAssured.given()
                .contentType("application/json")
                .body(requestBody);
        response = request.put(hostUri + objectId);
    }

    @Given("I have a empty body request")
    public void iHaveAEmptyRequest() {
        request = RestAssured.given()
                .contentType("application/json")
                .body("");
    }

    @Then("the response should contain the following values:")
    public void verifyResponseValues(DataTable dataTable) {
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

        dataTable.asMap(String.class, String.class).forEach((field, expectedValue) -> {
            String actualValue = jsonPath.getString(field);
            assertEquals(actualValue, expectedValue);
        });
    }
}
