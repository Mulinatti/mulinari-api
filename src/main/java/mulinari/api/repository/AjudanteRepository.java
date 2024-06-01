package mulinari.api.repository;

import mulinari.api.model.entity.Ajudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AjudanteRepository extends JpaRepository<Ajudante, Long> {
}
