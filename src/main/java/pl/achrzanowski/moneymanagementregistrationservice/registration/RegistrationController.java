package pl.achrzanowski.moneymanagementregistrationservice.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/registration")
    public void registration(@RequestBody RegistrationDTO registrationDTO){
            registrationService.registerUser(registrationDTO);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Username is already taken")
    @ExceptionHandler(DuplicateKeyException.class)
    public void duplicateKeyExceptionHandler(){}

}
