import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Models.Anuncio;

public class AnuncioTest {
    private Anuncio anuncio;

    @BeforeEach
    public void setUp() {
        // Inicializa um novo objeto Anuncio antes de cada teste
        anuncio = new Anuncio("Título", "Descrição");
    }

    @Test
    public void testGetTitulo() {
        assertEquals("Título", anuncio.getTitulo());
    }

    @Test
    public void testSetTitulo() {
        anuncio.setTitulo("Novo Título");
        assertEquals("Novo Título", anuncio.getTitulo());
    }

    @Test
    public void testGetDescricao() {
        assertEquals("Descrição", anuncio.getDescricao());
    }

    @Test
    public void testSetDescricao() {
        anuncio.setDescricao("Nova Descrição");
        assertEquals("Nova Descrição", anuncio.getDescricao());
    }

    @Test
    public void testGetTagsAsString() {
        anuncio.setTags(List.of("tag1", "tag2"));
        assertEquals("tag1|tag2", anuncio.getTagsAsString());
    }

    @Test
    public void testSetTags() {
        anuncio.setTags(List.of("tag1", "tag2"));
        assertTrue(anuncio.getTags().contains("tag1"));
        assertTrue(anuncio.getTags().contains("tag2"));
    }
}
