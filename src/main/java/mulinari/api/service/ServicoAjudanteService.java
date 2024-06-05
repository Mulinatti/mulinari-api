package mulinari.api.service;

import mulinari.api.model.entity.Servico;
import mulinari.api.model.entity.ServicoAjudante;
import mulinari.api.repository.ServicoAjudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicoAjudanteService {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ServicoAjudanteRepository ServicoAjudanteRepository;

    public List<Servico> buscarServicosNaoPagosPorAjudante(Long id) {
        List<ServicoAjudante> SAList = ServicoAjudanteRepository.findByAjudanteId(id);
        List<Servico> servicos = new ArrayList<>();

        SAList.forEach(servicoAjudante -> {
            try {
                if(servicoAjudante.getPago())
                    servicos.add(servicoService.buscarServico(servicoAjudante.getServicoId()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return servicos;
    }

    public List<Servico> buscarServicosPagosPorAjudante(Long id) {
        List<ServicoAjudante> SAList = ServicoAjudanteRepository.findByAjudanteId(id);
        List<Servico> servicos = new ArrayList<>();

        SAList.forEach(servicoAjudante -> {
            try {
                if(!servicoAjudante.getPago())
                    servicos.add(servicoService.buscarServico(servicoAjudante.getServicoId()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return servicos;
    }
}
