package dto;


public class RegistrationCashLinkDto {
    private String code;
    private String message;
    private DataRegistration dataRegistration;


    public RegistrationCashLinkDto(String code, String message, DataRegistration dataRegistration) {
        this.code = code;
        this.message = message;
        this.dataRegistration = dataRegistration;
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

    public DataRegistration getData() {
        return dataRegistration;
    }

    public void setData(DataRegistration dataRegistration) {
        this.dataRegistration = dataRegistration;
    }

    public static class DataRegistration {

        private String qrcId;
        private String payload;
        private String status;

        public DataRegistration(String qrcId, String payload, String status) {
            this.qrcId = qrcId;
            this.payload = payload;
            this.status = status;
        }

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