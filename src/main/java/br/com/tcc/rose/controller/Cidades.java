package br.com.tcc.rose.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.rose.dao.CidadeDAO;
import br.com.tcc.rose.erro.NaoEncontrado;
import br.com.tcc.rose.erro.RequisicaoInvalida;
import br.com.tcc.rose.modelo.Cidade;

@RestController
@RequestMapping("/api")
public class Cidades {
    
    @Autowired
    CidadeDAO cidadeDAO;
    
    
    /////////////// PESQUISAR CIDADE POR NOME ////////////////////////
   
    @RequestMapping(path = "/cidades/pesquisar/nome", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Cidade> pesquisaNome(
            @RequestParam(required = false) String contem,
            @RequestParam(required = false) String comeca
            ) {
        if(contem!=null)
            return cidadeDAO.findByNomeContaining(contem);
        else if(comeca!=null)
            return cidadeDAO.findByNomeStartingWith(comeca);
        else 
            throw new RequisicaoInvalida("Indique se contém ou começa");
    }
    
    //////////// LISTAR CIDADES /////////////////////
    
    @RequestMapping(path = "/cidades/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Cidade> listar() {
        return cidadeDAO.findAll();    
    }
    
    //////////////// MOSTRAR UMA CIDADE /////////////////
    
    @RequestMapping(path = "/cidades/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Cidade buscar(@PathVariable int id) {
        final Optional<Cidade> findById = cidadeDAO.findById(id);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
    }
    
    /////////////// APAGAR CIDADE ///////////////////////
    
    @RequestMapping(path="/cidades/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        if(cidadeDAO.existsById(id)) {
            cidadeDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
                        
    }
    
    /////////////// ATUALIZAR CIDADE //////////////////////
    
    @RequestMapping(path="/cidades/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Cidade cidade) {
        final Cidade cidadeBanco = this.buscar(id);
        cidadeBanco.setNome(cidade.getNome());
    	cidadeDAO.save(cidadeBanco);
    }
    
    
    /////////////////// INSERIR CIDADE ///////////////////

    @RequestMapping(path = "/cidades/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade cadastrar(@RequestBody Cidade cidade) {
            Cidade cidadeBanco = cidadeDAO.save(cidade);
            return cidadeBanco;
    }

}
