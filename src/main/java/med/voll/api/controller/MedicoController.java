package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastrarMedicoDTO cadastrarMedicoDTO) {
        repository.save(new MedicoJPA(cadastrarMedicoDTO));
        System.out.println("Medico: " + cadastrarMedicoDTO.nome() + " salvo com sucesso!");
    }

    @GetMapping
    //Nao precisa do @Transactional pois nao ira realizar transacoes no banco, somente consulta
    public Page<ListarMedicoDTO> listar (@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(ListarMedicoDTO::new);
        //return repository.findAll(paginacao).map(ListarMedicoDTO::new);
    }
    /*
    @GetMapping
    //Nao precisa do @Transactional pois nao ira realizar transacoes no banco, somente consulta
    public List<ListagemMedicoDTO> listar () {
        return repository.findAll().stream().map(ListagemMedicoDTO::new).toList();
    }
    * */
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarMedicoDTO cadastrarMedicoDTO) {
        var medico = repository.getReferenceById(cadastrarMedicoDTO.id());
        medico.atualizarInformacoes(cadastrarMedicoDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable  Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        //delete fisico no banco de dados:
        //repository.deleteById(id);
    }
}
