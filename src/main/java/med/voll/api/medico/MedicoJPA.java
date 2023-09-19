package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.EnderecoJPA;

@Table(name = "medicos")
@Entity(name = "medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
// antigo mapeamento de Entidades/tabelas JPA

public class MedicoJPA {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private EnumEspecialidade especialidade;
    @Embedded
    private EnderecoJPA endereco;
    private Boolean ativo;

    public MedicoJPA(CadastrarMedicoDTO cadastrarMedicoDTO) {
        this.nome = cadastrarMedicoDTO.nome();
        this.email = cadastrarMedicoDTO.email();
        this.telefone = cadastrarMedicoDTO.telefone();
        this.crm = cadastrarMedicoDTO.crm();
        this.especialidade = cadastrarMedicoDTO.especialidade();
        this.endereco = new EnderecoJPA(cadastrarMedicoDTO.endereco());
        this.ativo = true;

    }

    public void atualizarInformacoes(AtualizarMedicoDTO atualizarMedicoDTO) {
        if (atualizarMedicoDTO.nome() != null) {
            this.nome = atualizarMedicoDTO.nome();
        }
        if (atualizarMedicoDTO.telefone() != null) {
            this.telefone = atualizarMedicoDTO.telefone();
        }
        if(atualizarMedicoDTO.enderecoDTO() != null) {
            this.endereco.atualizarEndereco(atualizarMedicoDTO.enderecoDTO());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
