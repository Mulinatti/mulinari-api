package mulinari.api.service;

import lombok.SneakyThrows;
import mulinari.api.model.entity.Ajudante;
import mulinari.api.model.record.AjudanteDados;
import mulinari.api.repository.AjudanteRepository;
import mulinari.api.repository.ServicoAjudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AjudanteService {

    @Autowired
    private AjudanteRepository repository;

    @Autowired
    private ServicoAjudanteRepository SARepository;

    public List<Ajudante> listarAjudantes() {
        return repository.findAll();
    }

    public Ajudante buscarAjudante(Long id) throws Exception {
        Optional<Ajudante> ajudante = repository.findById(id);

        if(ajudante.isEmpty()) {
            throw new Exception("Ajudante não existe");
        }

        return ajudante.get();
    }

    public void cadastrarAjudante(@RequestBody AjudanteDados request) {
        Ajudante ajudante = new Ajudante(request);
        repository.save(ajudante);
    }

    public void atualizarAjudante(Long id, @RequestBody Ajudante request) {
        ajudanteExiste(id);
        request.setId(id);
        repository.save(request);
    }

    public void deletarAjudante(Long id) {
        ajudanteExiste(id);
        repository.deleteById(id);
    }

    @SneakyThrows
    public void ajudanteExiste(Long id) {
        buscarAjudante(id);
    }
}
