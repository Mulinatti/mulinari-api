package mulinari.api.repository;

import mulinari.api.model.entity.ServicoAjudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoAjudanteRepository extends JpaRepository<ServicoAjudante, Long> {
    List<ServicoAjudante> findByAjudanteId(Long ajudanteId);
}
