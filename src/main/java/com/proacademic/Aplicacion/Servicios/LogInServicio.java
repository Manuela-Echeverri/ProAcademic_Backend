package com.proacademic.Aplicacion.Servicios;

import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import com.proacademic.Infraestructura.Configuracion.JwtServicio;
import com.proacademic.Infraestructura.Web.DTO.SesionRespuesta;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LogInServicio {

    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder passwordEncoder;
    private final JwtServicio jwtServicio;

    public LogInServicio(UsuarioRepositorio repositorio,
                         PasswordEncoder passwordEncoder,
                         JwtServicio jwtServicio) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
        this.jwtServicio = jwtServicio;
    }

    public SesionRespuesta autenticar(String correo, String contrasena) {

        Usuario usuario = repositorio.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtServicio.generarToken(
                usuario.getCorreo(),
                usuario.getRol()
        );

        return new SesionRespuesta(
                token,
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getRol()
        );
    }
}
