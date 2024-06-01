package mulinari.api.controller;

import jakarta.validation.Valid;
import mulinari.api.model.entity.Ajudante;
import mulinari.api.model.record.AjudanteDados;
import mulinari.api.repository.AjudanteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid AjudanteDados request) {
        if(request.idade() > 0) {
            repository.save(new Ajudante(request));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Corpo da requisição incorreto");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Ajudante request) {
        Optional<Ajudante> ajudante = repository.findById(id);

        if(ajudante.isPresent()) {
            request.setId(id);
            repository.save(request);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        Optional<Ajudante> ajudante = repository.findById(id);

        if(ajudante.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

