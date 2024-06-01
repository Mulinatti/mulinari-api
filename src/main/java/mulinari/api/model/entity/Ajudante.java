package mulinari.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mulinari.api.model.record.AjudanteDados;

import java.util.ArrayList;
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

    @Column(unique = true)
    private String apelido;

    @Column(nullable = false)
    private String dataNascimento;

    @Column(nullable = false)
    private String telefone;

    @ManyToMany(mappedBy = "ajudantes")
    @JsonIgnoreProperties("ajudantes")
    List<Servico> servicos;

    public void addServico(Servico servico) {
        this.servicos.add(servico);
    }

    public Ajudante(AjudanteDados body) {
        this.nome = body.nome();
        this.apelido = body.apelido();
        this.motorista = body.motorista();
        this.dataNascimento = body.dataNascimento();
        this.telefone = body.telefone();
        this.servicos = new ArrayList<>();
    }
}
