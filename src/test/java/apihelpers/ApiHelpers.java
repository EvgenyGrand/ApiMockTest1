package apihelpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelpers extends RequestsBody{

    @Step("Отправляем запрос на регистрацию qr по адресу {url}")
    public static RequestSpecification requestSpec(String URL){
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public static ResponseSpecification responceSpec() {
        return new ResponseSpecBuilder()
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification responce) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = responce;
    }

    public static void attacheRequestBodyToAllureReport(Object body){
        try {
            Allure.addAttachment("Тело запроса", "application/json", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body));
        } catch (JsonProcessingException e) {
            Allure.addAttachment("Тело запроса", "text/plain", body.toString(), "txt");
        }
    }

    public static void attacheResponseBodyToAllureReport(Response response){
        try {
            Allure.addAttachment("Тело ответа", "application/json", response.getBody().prettyPrint());
        } catch (Exception e) {
            Allure.addAttachment("Тело ответа", "text/plain", response.getBody().prettyPrint(), "txt");
        }
    }
@Step("Отправляем post запрос на адрес {url} ")
    public static Response postRequestWithBody(String url, Map body) {
        ApiHelpers.installSpecification(ApiHelpers.requestSpec(EndPoints.baseUrl), ApiHelpers.responceSpec());
        attacheRequestBodyToAllureReport(body);
        Response response = given()
                .log().all()
                .body(body)
                .when()
                .post(url)
                .then().log().all()
                .extract().response(); // Извлекаем ответ

        return response; // Возвращаем ответ
    }


}

