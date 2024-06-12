package mulinari.api.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JoinColumn(name = "servico_id")
    @JsonIgnoreProperties("ajudantes")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "ajudante_id")
    @JsonIgnoreProperties("servicos")
    private Ajudante ajudante;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean pago;
}
