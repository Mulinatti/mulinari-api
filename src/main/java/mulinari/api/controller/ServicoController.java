package mulinari.api.controller;

import mulinari.api.ajudante.Ajudante;
import mulinari.api.ajudante.AjudanteRepository;
import mulinari.api.servico.Servico;
import mulinari.api.servico.ServicoDados;
import mulinari.api.servico.ServicoRepository;
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
    public ResponseEntity<Object> cadastrar(@RequestBody ServicoDados body) {

        List<Ajudante> ajudantes = ajudanteRepository.findAllById(body.ajudantesIds());

        if(ajudantes.size() == body.ajudantesIds().size() && !body.ajudantesIds().isEmpty()) {
            Servico servico = new Servico(body);
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
    public ResponseEntity<Optional<Servico>> getAjudante(@PathVariable Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);

        if(servico.isPresent())
            return ResponseEntity.ok().body(servico);

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Servico> getServicos() {
        return servicoRepository.findAll();
    }
}
