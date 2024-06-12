package mulinari.api.model.entity;

import jakarta.persistence.*;
import lombok.*;
import mulinari.api.model.record.ServicoDados;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "servicos")
public class Servico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float valor;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String data;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ServicoAjudante> ajudantes;

    public Servico(ServicoDados body) {
        this.valor = body.valor();
        this.rua = body.rua();
        this.bairro = body.bairro();
        this.data = body.data();
        this.ajudantes = new ArrayList<>();
    }
}
