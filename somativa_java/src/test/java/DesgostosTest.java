import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Models.Desgostos;

public class DesgostosTest {
    private Desgostos desgostos;

    @BeforeEach
    public void setUp() {
        // Inicializa um novo objeto Desgostos antes de cada teste
        desgostos = new Desgostos();
    }

    @Test
    public void testAdicionarPreferencia() {
        // Adiciona uma desgosto e verifica se foi adicionada
        desgostos.adicionarPreferencia("tag1");
        assertTrue(desgostos.getDesgostos().contains("tag1"));
    }

    @Test
    public void testAdicionarPreferenciaDuplicada() {
        // Adiciona a mesma desgosto duas vezes
        desgostos.adicionarPreferencia("tag1");
        desgostos.adicionarPreferencia("tag1"); // Deve ser ignorada

        // Verifica que a lista contém apenas uma instância da tag
        assertEquals(1, desgostos.getDesgostos().size());
    }

    @Test
    public void testRemoverPreferencia() {
        // Adiciona e depois remove uma desgosto
        desgostos.adicionarPreferencia("tag1");
        desgostos.removerPreferencia("tag1");

        // Verifica que a lista está vazia
        assertTrue(desgostos.getDesgostos().isEmpty());
    }

    @Test
    public void testRemoverPreferenciaInexistente() {
        // Tenta remover uma desgosto que não existe
        desgostos.removerPreferencia("tag1");

        // Verifica que a lista ainda está vazia
        assertEquals(0, desgostos.getDesgostos().size());
    }

    @Test
    public void testGetDesgostos() {
        // Verifica se a lista de desgostos está vazia inicialmente
        assertEquals(0, desgostos.getDesgostos().size());

        // Adiciona uma desgosto e verifica se a lista é atualizada
        desgostos.adicionarPreferencia("tag1");
        assertEquals(1, desgostos.getDesgostos().size());
        assertTrue(desgostos.getDesgostos().contains("tag1"));
    }
}
