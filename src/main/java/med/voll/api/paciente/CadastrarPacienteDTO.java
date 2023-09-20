package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.EnderecoDTO;

// este seria um BEAN antigo
public record CadastrarPacienteDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String cpf,
        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,
        @NotNull
        @Valid
        EnderecoDTO endereco
) {}
