package mulinari.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mulinari.api.model.record.AjudanteDados;

import java.util.Collections;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ajudantes")
public class Ajudante {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean motorista;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;

    @ManyToMany(mappedBy = "ajudantes")
    @JsonIgnoreProperties("ajudantes")
    List<Servico> servicos;

    public void addServico(Servico servico) {
        this.servicos.add(servico);
    }

    public Ajudante(AjudanteDados body) {
        this.nome = body.nome();
        this.motorista = body.motorista();
        this.idade = body.idade();
        this.servicos = Collections.emptyList();
    }
}
