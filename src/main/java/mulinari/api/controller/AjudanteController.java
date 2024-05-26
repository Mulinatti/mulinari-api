package mulinari.api.controller;

import mulinari.api.ajudante.Ajudante;
import mulinari.api.ajudante.AjudanteDados;
import mulinari.api.ajudante.AjudanteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajudantes")
public class AjudanteController {

    @Autowired
    private AjudanteRepository repository;

    @GetMapping
    public List<Ajudante> getAjudantes() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody AjudanteDados body) {
        if(body.idade() > 0) {
            repository.save(new Ajudante(body));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Corpo da requisição incorreto");
    }
}

