package mulinari.api.service;

import lombok.SneakyThrows;
import mulinari.api.model.entity.Ajudante;
import mulinari.api.model.entity.Servico;
import mulinari.api.model.record.ServicoDados;
import mulinari.api.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    @Autowired
    private AjudanteService ajudanteService;

    public List<Servico> listarServicos() {
        return repository.findAll();
    }

    public Servico buscarServico(Long id) throws Exception {
        Optional<Servico> servico = repository.findById(id);

        if(servico.isEmpty()) {
            throw new Exception("Servico não existe");
        }

        return servico.get();
    }

    public void cadastrarServico(@RequestBody ServicoDados request) {
        List<Ajudante> ajudantes = verificaAjudantes(request.ajudantesIds());
        Servico servico = new Servico(request);
        servico.setAjudantes(ajudantes);

        ajudantes.forEach(ajudante -> {
            ajudante.addServico(servico);
        });

        repository.save(servico);
    }

    @SneakyThrows
    public void atualizarServico(Long id, @RequestBody ServicoDados request) {
        Servico servico = buscarServico(id);
        List<Ajudante> ajudantes = verificaAjudantes(request.ajudantesIds());
        servico.setAjudantes(ajudantes);
        repository.save(servico);
    }

    public void deletarServico(Long id) {
        servicoExiste(id);
        repository.deleteById(id);
    }

    public List<Ajudante> verificaAjudantes(List<Long> ajudantesIds) {
        List<Ajudante> ajudantes = new ArrayList<>();
        ajudantesIds.forEach(ajudante -> {
            try {
                Ajudante ajudanteEncontrado = ajudanteService.buscarAjudante(ajudante);
                ajudantes.add(ajudanteEncontrado);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return ajudantes;
    }

    @SneakyThrows
    public void servicoExiste(Long id) {
        buscarServico(id);
    }
}
