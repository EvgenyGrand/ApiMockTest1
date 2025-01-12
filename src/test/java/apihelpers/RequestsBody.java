package apihelpers;

import java.util.HashMap;
import java.util.Map;

public class RequestsBody {


    public static Map registrationQr() {
        Map<String, Object> registrationQr = new HashMap<>();
        registrationQr.put("amount", "100");
        return registrationQr;
    }

    public static Map activationQr(){
        Map<String, Object> activationCode = new HashMap<>();
        activationCode.put("code", "QWERTY123");
        return activationCode;
    }
}
