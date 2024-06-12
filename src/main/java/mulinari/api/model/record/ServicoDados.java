package mulinari.api.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import mulinari.api.model.entity.ServicoAjudante;

import java.util.List;

public record ServicoDados(

        List<ServicoAjudante> ajudantes,

        @NotEmpty
        List<Long> ajudantesIds,

        @NotNull
        Float valor,

        @NotBlank
        String rua,

        @NotBlank
        String bairro,

        @NotBlank
        @Pattern(
                regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$",
                message = "Data deve estar no formato dd/MM/yyyy"
        )
        String data) {
}
