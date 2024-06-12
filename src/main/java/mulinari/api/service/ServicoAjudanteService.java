package mulinari.api.service;

import mulinari.api.model.entity.ServicoAjudante;
import mulinari.api.repository.ServicoAjudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ServicoAjudanteService {
    @Autowired
    private ServicoAjudanteRepository repository;

    public Optional<ServicoAjudante> pagarAjudante(Long id) {
        Optional<ServicoAjudante> servicoAjudante = repository.findById(id);
        if(servicoAjudante.isPresent()) {
            servicoAjudante.get().setId(id);
            servicoAjudante.get().setPago(true);
            repository.save(servicoAjudante.get());
            return servicoAjudante;
        }
        return servicoAjudante;
    }
}
