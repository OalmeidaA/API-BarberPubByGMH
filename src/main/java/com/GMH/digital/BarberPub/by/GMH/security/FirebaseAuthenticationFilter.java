package com.GMH.digital.BarberPub.by.GMH.security;

import com.GMH.digital.BarberPub.by.GMH.entities.User;
import com.GMH.digital.BarberPub.by.GMH.repositories.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {
    final private Logger log = LoggerFactory.getLogger(FirebaseAuthenticationFilter.class);
    final private UserRepository userRepository;

    public FirebaseAuthenticationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                String firebaseUid = decodedToken.getUid();

                Optional<User> optionalUser = userRepository.findByFirebaseUid(firebaseUid);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(user, null, List.of());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                    log.info("Authenticated user: {}", user.getEmail());
                } else {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(null, null, List.of());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                    log.warn("User not found for firebaseUid: {}", firebaseUid);
                }
            } catch (FirebaseAuthException e) {
                log.error("FirebaseAuthException: ", e);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,  "Invalid or expired token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}