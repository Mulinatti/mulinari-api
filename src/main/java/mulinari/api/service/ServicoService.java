package mulinari.api.service;

import lombok.SneakyThrows;
import mulinari.api.model.entity.Ajudante;
import mulinari.api.model.entity.Servico;
import mulinari.api.model.entity.ServicoAjudante;
import mulinari.api.model.record.ServicoDados;
import mulinari.api.repository.ServicoAjudanteRepository;
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

    @Autowired
    private ServicoAjudanteRepository SARepository;

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

        ajudantes.forEach(ajudante -> {
            ServicoAjudante servicoAjudante = new ServicoAjudante();
            servicoAjudante.setServico(servico);
            servicoAjudante.setAjudante(ajudante);
            servicoAjudante.setPago(false);

            SARepository.save(servicoAjudante);
        });

        repository.save(servico);
    }

    @SneakyThrows
    public void atualizarServico(Long id, ServicoDados request) {
        repository.findById(id).map(servico -> {
            Servico servicoAtualizado = new Servico(request);
            servicoAtualizado.setId(servico.getId());
            servicoAtualizado.setAjudantes(servico.getAjudantes());

            List<Long> ajudantesIds = request.ajudantesIds();
            List<ServicoAjudante> servicoAjudantes = servicoAtualizado.getAjudantes();

            servicoAjudantes.removeIf(servicoAjudante -> !ajudantesIds.contains(servicoAjudante.getAjudante().getId()));

            ajudantesIds.forEach(ajudante -> {
                boolean exists = servicoAjudantes.stream().anyMatch(sa -> sa.getAjudante().getId().equals(ajudante));
                if (!exists) {
                    Ajudante ajudanteExiste;
                    try {
                        ajudanteExiste = ajudanteService.buscarAjudante(ajudante);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    if(ajudanteExiste != null) {
                        ServicoAjudante servicoAjudante = new ServicoAjudante();
                        servicoAjudante.setServico(servicoAtualizado);
                        servicoAjudante.setAjudante(ajudanteExiste);
                        servicoAjudante.setPago(false);
                        servicoAjudantes.add(servicoAjudante);
                    }
                }
            });

            return repository.save(servicoAtualizado);
        });
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
