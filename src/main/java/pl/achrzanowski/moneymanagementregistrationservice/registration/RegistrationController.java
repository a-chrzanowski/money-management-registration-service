package pl.achrzanowski.moneymanagementregistrationservice.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.achrzanowski.moneymanagementregistrationservice.captcha.Captcha;
import pl.achrzanowski.moneymanagementregistrationservice.captcha.CaptchaService;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private Captcha captcha;

    @GetMapping("/captcha")
    public ResponseEntity<byte[]> getCaptcha(){
        captcha = captchaService.getNew();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(captcha.getImage());
    }

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationDTO registrationDTO){
        if(registrationDTO.getCaptchaVerificationCode().equals(captcha.getVerificationCode()))
            registrationService.registerUser(registrationDTO);
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Wrong verification code");
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Username is already taken")
    @ExceptionHandler(DuplicateKeyException.class)
    public void duplicateKeyExceptionHandler(){}

}
