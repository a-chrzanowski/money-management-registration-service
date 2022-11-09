package pl.achrzanowski.moneymanagementregistrationservice.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationDTO registrationDTO){
        registrationService.registerUser(registrationDTO);
    }

}
