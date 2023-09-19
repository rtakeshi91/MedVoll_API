package med.voll.api.paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.EnderecoJPA;
import med.voll.api.medico.AtualizarMedicoDTO;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "paciente")
@Table (name = "pacientes")
// antigo mapeamento de Entidades/tabelas JPA

public class PacienteJPA {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private EnderecoJPA endereco;
    private Boolean ativo;

    public PacienteJPA(CadastrarPacienteDTO cadastrarPacienteDTO) {
        this.nome = cadastrarPacienteDTO.nome();
        this.email = cadastrarPacienteDTO.email();
        this.cpf = cadastrarPacienteDTO.cpf();
        this.telefone = cadastrarPacienteDTO.telefone();
        this.endereco = new EnderecoJPA(cadastrarPacienteDTO.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(AtualizarPacienteDTO atualizarPacienteDTO) {
        if (atualizarPacienteDTO.nome() != null) {
            this.nome = atualizarPacienteDTO.nome();
        }
        if (atualizarPacienteDTO.email() != null) {
            this.email = atualizarPacienteDTO.email();
        }
        if (atualizarPacienteDTO.cpf() != null) {
            this.cpf = atualizarPacienteDTO.cpf();
        }
        if (atualizarPacienteDTO.telefone() != null) {
            this.telefone = atualizarPacienteDTO.telefone();
        }
        if(atualizarPacienteDTO.enderecoDTO() != null) {
            this.endereco.atualizarEndereco(atualizarPacienteDTO.enderecoDTO());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
