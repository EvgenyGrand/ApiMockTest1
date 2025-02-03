package apihelpers;

import utils.PropertyLoader;

public class EndPoints {

    public static final String baseUrl = PropertyLoader.getProperty("BaseUrl");

    public static String postRegistrationCode = "/code/registration";

    public static String postActivationationCode = "/code/activation";

    public static String getCodeInfo = "/code/info";
}
