import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Models.Usuario;

public class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        // Inicializa um novo objeto Usuario antes de cada teste
        usuario = new Usuario("João", "joao@example.com", "senha123");
    }

    @Test
    public void testGetNome() {
        assertEquals("João", usuario.getNome());
    }

    @Test
    public void testSetNome() {
        usuario.setNome("Maria");
        assertEquals("Maria", usuario.getNome());
    }

    @Test
    public void testGetEmail() {
        assertEquals("joao@example.com", usuario.getEmail());
    }

    @Test
    public void testSetEmail() {
        usuario.setEmail("maria@example.com");
        assertEquals("maria@example.com", usuario.getEmail());
    }

    @Test
    public void testGetSenha() {
        assertEquals("senha123", usuario.getSenha());
    }

    @Test
    public void testSetSenha() {
        usuario.setSenha("novaSenha");
        assertEquals("novaSenha", usuario.getSenha());
    }

    @Test
    public void testIsAdmInicialmenteFalso() {
        assertFalse(usuario.isAdm());
    }

    @Test
    public void testSetAdm() {
        usuario.setAdm(true);
        assertTrue(usuario.isAdm());
    }
}
