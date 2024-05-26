package mulinari.api.servico;

import jakarta.validation.constraints.NotBlank;
import mulinari.api.ajudante.Ajudante;

import java.util.List;

public record ServicoDados(

        List<Ajudante> ajudantes,

        @NotBlank
        List<Long> ajudantesIds,

        @NotBlank
        Long valor,

        @NotBlank
        String rua,

        @NotBlank
        String bairro,

        @NotBlank
        String data) {
}
