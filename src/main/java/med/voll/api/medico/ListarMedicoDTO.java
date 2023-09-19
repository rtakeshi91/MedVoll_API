package med.voll.api.medico;

public record ListarMedicoDTO(Long id, String nome, String email, String crm, EnumEspecialidade especialidade) {
    public ListarMedicoDTO(MedicoJPA medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());

    }
}
