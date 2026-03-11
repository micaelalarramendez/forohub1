package forohub.controller;

import forohub.domain.Usuario;
import forohub.dto.DatosAuth;
import forohub.dto.DatosToken;
import forohub.repository.UsuarioRepository;
import forohub.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    public AuthController(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @PostMapping
    public DatosToken login(@RequestBody DatosAuth datos) {
        Usuario usuario = usuarioRepository.findByLogin(datos.login());

        if (usuario == null || !datos.clave().equals(usuario.getClave())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        String token = tokenService.generarToken(usuario);
        return new DatosToken(token);
    }
}