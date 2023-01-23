package pl.achrzanowski.moneymanagementregistrationservice.registration;

import lombok.Data;

@Data
public class RegistrationDTO {

    private String requestId;
    private String username;
    private String firstName;
    private String password;
    private Boolean isAwareOfDataInsecurity;
    private String captchaVerificationCode;

}
