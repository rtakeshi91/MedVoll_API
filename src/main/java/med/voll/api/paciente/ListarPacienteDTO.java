package med.voll.api.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ListarPacienteDTO(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        String telefone,
        @NotBlank
        String email
) {
    public ListarPacienteDTO(PacienteJPA paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail());
    }
}
