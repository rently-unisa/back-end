package it.unisa.c02.rently.rently_application.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.unisa.c02.rently.rently_application.data.dao.GestioneAutenticazioneDAO;
import it.unisa.c02.rently.rently_application.data.dto.UtenteDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final GestioneAutenticazioneDAO userRepository;
    private final ObjectMapper mapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager, GestioneAutenticazioneDAO userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        final String header = request.getHeader(JwtProvider.headerParam);
        if (header != null) {
            final DecodedJWT decoded = JwtProvider.verifyJwt(header);
            final ObjectNode userNode = this.mapper.readValue(decoded.getClaim("user").asString(), ObjectNode.class);
            final UtenteDTO user = this.mapper.convertValue(userNode, UtenteDTO.class);
            this.userRepository.findById(user.getId()).ifPresent(entity -> {

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user.getEmail(), new ArrayList<>(), new ArrayList<>()
                );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            });
        }
        else {
            ;
        }
        chain.doFilter(request, response);
    }
}