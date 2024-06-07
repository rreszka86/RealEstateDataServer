package pl.pollub.integracja_projekt.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.integracja_projekt.Config.AuthenticationRequest;
import pl.pollub.integracja_projekt.Config.AuthenticationResponse;
import pl.pollub.integracja_projekt.Config.RegisterRequest;
import pl.pollub.integracja_projekt.Services.AuthenticationService;
import pl.pollub.integracja_projekt.Utils.Validator;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        if(!Validator.validRequired(request.getEmail())){
            return ResponseEntity.badRequest().body(new AuthenticationResponse("error1", 400));
        }
        if(!Validator.validEmail(request.getEmail())){
            return ResponseEntity.badRequest().body(new AuthenticationResponse("error2", 400));
        }
        if(!Validator.validRequired(request.getPassword())){
            return ResponseEntity.badRequest().body(new AuthenticationResponse("error3", 400));
        }
        if(!Validator.validPassword(request.getPassword())){
            return ResponseEntity.badRequest().body(new AuthenticationResponse("error4", 400));
        }

        var res = service.register(request);
        if(res.getStatus() == 200) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/validate")
    public boolean validate(){
        return true;
    }
}
