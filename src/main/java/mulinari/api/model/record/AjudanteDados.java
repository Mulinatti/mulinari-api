package mulinari.api.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import mulinari.api.model.entity.ServicoAjudante;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record AjudanteDados(

        List<ServicoAjudante> servicos,

        @NotBlank
        String nome,

        @NotBlank
        String apelido,

        @NotNull
        Boolean motorista,

        @NotBlank
        @Length(max = 11)
        String telefone,

        @NotBlank
        @Pattern(
                regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})$",
                message = "Data deve estar no formato dd/MM/yyyy"
        )
        String dataNascimento

) {

}
