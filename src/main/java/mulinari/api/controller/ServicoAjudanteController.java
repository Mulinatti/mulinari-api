package mulinari.api.controller;

import mulinari.api.model.entity.Servico;
import mulinari.api.service.ServicoAjudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servico-ajudante")
public class ServicoAjudanteController {

    @Autowired
    private ServicoAjudanteService servicoAjudanteService;

    @GetMapping("/ajudante/nao-pago/{id}")
    public List<Servico> buscarNaoPagos(@PathVariable Long id) {
        return servicoAjudanteService.buscarServicosNaoPagosPorAjudante(id);
    }

    @GetMapping("/ajudante/pago/{id}")
    public List<Servico> buscarPagos(@PathVariable Long id) {
        return servicoAjudanteService.buscarServicosPagosPorAjudante(id);
    }
}
