package mulinari.api.ajudante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AjudanteRepository extends JpaRepository<Ajudante, Long> {
}
