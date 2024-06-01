package mulinari.api.controller;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import mulinari.api.model.entity.Servico;
import mulinari.api.model.record.ServicoDados;
import mulinari.api.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping
    public ResponseEntity<List<Servico>> getServicos() {
        return ResponseEntity.ok().body(service.listarServicos());
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Object> getServico(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarServico(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid ServicoDados request) {
        service.cadastrarServico(request);
        return ResponseEntity.status(201).build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody ServicoDados request) {
        service.atualizarServico(id, request);
        return ResponseEntity.accepted().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarServico(@PathVariable Long id) {
        service.deletarServico(id);
        return ResponseEntity.ok().build();
    }
}
