package mulinari.api.controller;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import mulinari.api.model.entity.Ajudante;
import mulinari.api.model.record.AjudanteDados;

import mulinari.api.service.AjudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajudantes")
public class AjudanteController {

    @Autowired
    private AjudanteService service;

    @GetMapping
    public ResponseEntity<List<Ajudante>> getAjudantes() {
        return ResponseEntity.ok().body(service.listarAjudantes());
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAjudante(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarAjudante(id));
    }

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid AjudanteDados request) {
        service.cadastrarAjudante(request);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid AjudanteDados request) {
        Ajudante ajudante = new Ajudante(request);

        service.atualizarAjudante(id, ajudante);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        service.deletarAjudante(id);
        return ResponseEntity.noContent().build();
    }
}

