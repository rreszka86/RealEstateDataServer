package pl.pollub.integracja_projekt.Services;

import lombok.RequiredArgsConstructor;

import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pollub.integracja_projekt.Config.AuthenticationRequest;
import pl.pollub.integracja_projekt.Config.AuthenticationResponse;
import pl.pollub.integracja_projekt.Config.RegisterRequest;
import pl.pollub.integracja_projekt.Models.User;
import pl.pollub.integracja_projekt.Repositories.UserRepository;
import pl.pollub.integracja_projekt.Utils.Role;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var foundUser = repository.findByEmail(user.getEmail());
        if(foundUser.isEmpty()) {
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .status(200)
                    .build();
        }
        return AuthenticationResponse.builder()
                .status(409)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
