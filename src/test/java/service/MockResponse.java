package service;

public class MockResponse {

    public static final String registrationCodeResponse = "{\n" +
            "    \"message\": \"Запрос обработан успешно\",\n" +
            "    \"data\": {\n" +
            "        \"code\": \"QWERTY123\",\n" +
            "    }\n" +
            "}";

    public static final String activationCodeResponse = "{\n" +
            "    \"message\": \"Запрос обработан успешно\",\n" +
            "    \"data\": {\n" +
            "        \"parameter\": \"UUID123\",\n" +
            "    }\n" +
            "}";

}
