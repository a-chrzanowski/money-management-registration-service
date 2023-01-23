package pl.achrzanowski.moneymanagementregistrationservice.captcha;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Data
public class Captcha {

    private String verificationCode;
    private byte[] image;

}
