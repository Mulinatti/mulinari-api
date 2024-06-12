package mulinari.api.controller;

import mulinari.api.model.entity.ServicoAjudante;
import mulinari.api.service.ServicoAjudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/servico-ajudante")
public class ServicoAjudanteController {

    @Autowired
    private ServicoAjudanteService service;

    @PutMapping("/{id}")
    public ResponseEntity<Object> pagarAjudante(@PathVariable Long id) {
        Optional<ServicoAjudante> servicoAjudante = service.pagarAjudante(id);
        if(servicoAjudante.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
