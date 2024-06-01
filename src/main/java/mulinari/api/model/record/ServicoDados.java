package mulinari.api.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import mulinari.api.model.entity.Ajudante;

import java.util.List;

public record ServicoDados(

        List<Ajudante> ajudantes,

        @NotEmpty
        List<Long> ajudantesIds,

        @NotNull
        Float valor,

        @NotBlank
        String rua,

        @NotBlank
        String bairro,

        @NotBlank
        String data) {
}
