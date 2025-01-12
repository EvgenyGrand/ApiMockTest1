package apihelpers;

import tests.BaseTest;

import java.util.HashMap;
import java.util.Map;

public class RequestsBody {


    public static Map registrationQr() {
        Map<String, Object> registrationQr = new HashMap<>();
        registrationQr.put("amount", "100");
        return registrationQr;
    }

    public static Map activationQr(String code){
        Map<String, Object> activationCode = new HashMap<>();
        activationCode.put("code",code );
        return activationCode;
    }
}
