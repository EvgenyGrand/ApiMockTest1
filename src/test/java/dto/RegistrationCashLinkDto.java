package dto;

public class RegistrationCashLinkDto {
    private String code;
    private String message;
    private Data data;


    public RegistrationCashLinkDto(String code, String message, Data data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String qrcId;

        public Data(String qrcId, String payload, String status) {
            this.qrcId = qrcId;
            this.payload = payload;
            this.status = status;
        }

        private String payload;
        private String status;

        public String getQrcId() {
            return qrcId;
        }

        public void setQrcId(String qrcId) {
            this.qrcId = qrcId;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}