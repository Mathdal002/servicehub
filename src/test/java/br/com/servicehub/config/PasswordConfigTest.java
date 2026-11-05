package br.com.servicehub.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordConfigTest {

    private final PasswordEncoder passwordEncoder =
            new PasswordConfig().passwordEncoder();

    @Test
    void deveCodificarSenhaEValidarSenhaCorreta() {
        String senhaOriginal = "SenhaForte123!";

        String hash = passwordEncoder.encode(senhaOriginal);

        assertNotEquals(senhaOriginal, hash);
        assertTrue(passwordEncoder.matches(senhaOriginal, hash));
        assertFalse(passwordEncoder.matches("SenhaErrada", hash));
    }
}
