package apihelpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelpers {

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
    @Step("Отправляем запрос на регистрацию qr")
    public static Response postRequestWithBody(String url) {
        ApiHelpers.installSpecification(ApiHelpers.requestSpec(EndPoints.baseUrl), ApiHelpers.responceSpec());

        Response response = given()
                .body("{}")
                .when()
                .post(url)
                .then().log().all()
                .extract().response(); // Извлекаем ответ

        return response; // Возвращаем ответ
    }

}

