package mulinari.api.model.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mulinari.api.model.entity.Servico;

import java.util.List;

public record AjudanteDados(

        List<Servico> servicos,

        @NotBlank
        String nome,

        @NotNull
        Boolean motorista,

        @NotNull
        int idade) {

}
