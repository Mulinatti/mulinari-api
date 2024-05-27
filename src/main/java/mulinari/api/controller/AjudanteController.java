package mulinari.api.controller;

import mulinari.api.ajudante.Ajudante;
import mulinari.api.ajudante.AjudanteDados;
import mulinari.api.ajudante.AjudanteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ajudantes")
public class AjudanteController {

    @Autowired
    private AjudanteRepository repository;

    @GetMapping
    public List<Ajudante> getAjudantes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ajudante>> getAjudante(@PathVariable Long id) {
        Optional<Ajudante> ajudante = repository.findById(id);

        if(ajudante.isPresent())
            return ResponseEntity.ok().body(ajudante);

        return ResponseEntity.notFound().build();
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

