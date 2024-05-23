package pl.pollub.integracja_projekt.Controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import pl.pollub.integracja_projekt.Config.AuthenticationRequest;
import pl.pollub.integracja_projekt.Config.AuthenticationResponse;
import pl.pollub.integracja_projekt.Config.RegisterRequest;
import pl.pollub.integracja_projekt.Services.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
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
