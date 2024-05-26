package mulinari.api.ajudante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mulinari.api.servico.Servico;

import java.util.List;

public record AjudanteDados(

        List<Servico> servicos,

        @NotBlank
        @NotNull
        String nome,

        @NotBlank
        @NotNull
        Boolean motorista,

        @NotBlank
        @NotNull
        int idade) {

}
