import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Models.Gostos;

public class GostosTest {
    private Gostos gostos;

    @BeforeEach
    public void setUp() {
        // Inicializa um novo objeto Gostos antes de cada teste
        gostos = new Gostos();
    }

    @Test
    public void testAdicionarPreferencia() {
        // Adiciona uma preferência e verifica se foi adicionada
        gostos.adicionarPreferencia("tag1");
        assertTrue(gostos.getGostos().contains("tag1"));
    }

    @Test
    public void testAdicionarPreferenciaDuplicada() {
        // Adiciona a mesma preferência duas vezes
        gostos.adicionarPreferencia("tag1");
        gostos.adicionarPreferencia("tag1"); // Deve ser ignorada

        // Verifica que a lista contém apenas uma instância da tag
        assertEquals(1, gostos.getGostos().size());
    }

    @Test
    public void testRemoverPreferencia() {
        // Adiciona e depois remove uma preferência
        gostos.adicionarPreferencia("tag1");
        gostos.removerPreferencia("tag1");

        // Verifica que a lista está vazia
        assertTrue(gostos.getGostos().isEmpty());
    }

    @Test
    public void testRemoverPreferenciaInexistente() {
        // Tenta remover uma preferência que não existe
        gostos.removerPreferencia("tag1");

        // Verifica que a lista ainda está vazia
        assertEquals(0, gostos.getGostos().size());
    }

    @Test
    public void testGetGostos() {
        // Verifica se a lista de gostos está vazia inicialmente
        assertEquals(0, gostos.getGostos().size());

        // Adiciona uma preferência e verifica se a lista é atualizada
        gostos.adicionarPreferencia("tag1");
        assertEquals(1, gostos.getGostos().size());
        assertTrue(gostos.getGostos().contains("tag1"));
    }
}
