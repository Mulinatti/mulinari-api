package mulinari.api.model.record;

import jakarta.validation.constraints.NotBlank;
import mulinari.api.model.entity.Ajudante;

import java.util.List;

public record ServicoDados(

        List<Ajudante> ajudantes,

        List<Long> ajudantesIds,

        Float valor,

        @NotBlank
        String rua,

        @NotBlank
        String bairro,

        @NotBlank
        String data) {
}
