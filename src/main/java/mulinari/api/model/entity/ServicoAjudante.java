package mulinari.api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "servico_ajudante")
@NoArgsConstructor
@AllArgsConstructor
public class ServicoAjudante {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "servico_id")
    private Long servicoId;

    @Column(name = "ajudante_id")
    private Long ajudanteId;

    private Boolean pago;
}
