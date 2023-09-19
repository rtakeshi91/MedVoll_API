package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid CadastrarPacienteDTO cadastrarPacienteDTO) {
        try {
            repository.save(new PacienteJPA(cadastrarPacienteDTO));
            System.out.println("Paciente: " + cadastrarPacienteDTO.nome() + " salvo com sucesso");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @GetMapping
    public Page<ListarPacienteDTO> listar(@PageableDefault(size = 5, sort={"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(ListarPacienteDTO::new);
        // lista somente registros que nao tenham tido delete fisico
        // return repository.findAll(paginacao).map(ListarPacienteDTO::new);
    }
    /*
    @GetMapping
    public List<ListagemPacienteDTO> listar() {
        return repository.findAll().stream().map(ListagemPacienteDTO::new).toList();
    }
    */
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarPacienteDTO atualizarPacienteDTO) {
        var paciente = repository.getReferenceById(atualizarPacienteDTO.id());
        paciente.atualizarInformacoes(atualizarPacienteDTO);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
    }
}
