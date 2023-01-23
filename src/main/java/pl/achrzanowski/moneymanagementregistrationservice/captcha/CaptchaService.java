package pl.achrzanowski.moneymanagementregistrationservice.captcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CaptchaService {


    private final WebClient webClient = WebClient.create();

    @Value("${captchaServiceUrl}")
    private String captchaServiceUrl;

    public Captcha getNew(){
        return webClient.get().uri(captchaServiceUrl + "/generateCaptcha")
                .retrieve()
                .bodyToMono(Captcha.class)
                .block();
    }


}
