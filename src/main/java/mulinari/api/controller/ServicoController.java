package mulinari.api.controller;

import jakarta.validation.Valid;
import mulinari.api.model.entity.Ajudante;
import mulinari.api.repository.AjudanteRepository;
import mulinari.api.model.entity.Servico;
import mulinari.api.model.record.ServicoDados;
import mulinari.api.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private AjudanteRepository ajudanteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid ServicoDados request) {

        List<Ajudante> ajudantes = ajudanteRepository.findAllById(request.ajudantesIds());

        if(ajudantes.size() == request.ajudantesIds().size() && !request.ajudantesIds().isEmpty()) {
            Servico servico = new Servico(request);
            servico.setAjudantes(ajudantes);

            ajudantes.forEach(ajudante -> {
                ajudante.addServico(servico);
            });

            servicoRepository.save(servico);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().body("Informações incorretas enviadas");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Servico>> getServico(@PathVariable Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);

        if(servico.isPresent())
            return ResponseEntity.ok().body(servico);

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Servico> getServicos() {
        return servicoRepository.findAll();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody ServicoDados request) {
        Optional<Servico> servicoExiste = servicoRepository.findById(id);
        List<Ajudante> ajudantes = ajudanteRepository.findAllById(request.ajudantesIds());


        if(servicoExiste.isPresent()) {
            Servico servico = servicoExiste.get();
            servico.setAjudantes(ajudantes);
            servicoRepository.save(servico);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarServico(@PathVariable Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);

        if(servico.isPresent()) {
            servicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
