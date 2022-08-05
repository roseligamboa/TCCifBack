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

import br.com.tcc.rose.dao.UsuarioDAO;
import br.com.tcc.rose.erro.NaoEncontrado;
import br.com.tcc.rose.erro.RequisicaoInvalida;
import br.com.tcc.rose.modelo.Usuario;

@RestController
@RequestMapping("/api")
public class Usuarios {
    
    @Autowired
    UsuarioDAO usuarioDAO;
   
    @RequestMapping(path = "/usuarios/pesquisar/nome", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> pesquisaNome(
            @RequestParam(required = false) String contem,
            @RequestParam(required = false) String comeca
            ) {
        if(contem!=null)
            return usuarioDAO.findByNomeContaining(contem);
        else if(comeca!=null)
            return usuarioDAO.findByNomeStartingWith(comeca);
        else 
            throw new RequisicaoInvalida("Indique se contém ou começa");
    }
    
    @RequestMapping(path = "/usuarios/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> listar() {
        return usuarioDAO.findAll();    
    }
    
    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscar(@PathVariable int id) {
        final Optional<Usuario> findById = usuarioDAO.findById(id);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
    }
    
    @RequestMapping(path="/usuarios/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        if(usuarioDAO.existsById(id)) {
            usuarioDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
                        
    }
    
    @RequestMapping(path="/usuarios/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        final Usuario usuarioBanco = this.buscar(id);

        usuarioBanco.setNome(usuario.getNome());
        usuarioBanco.setEmail(usuario.getEmail());
        usuarioBanco.setTel1(usuario.getTel1());
        usuarioBanco.setTel2(usuario.getTel2());
        usuarioBanco.setEndereco(usuario.getEndereco());
        usuarioBanco.setNumero(usuario.getNumero());
        usuarioBanco.setComplemento(usuario.getComplemento());
        usuarioBanco.setBairro(usuario.getBairro());
        usuarioBanco.setCidade(usuario.getCidade());
        usuarioBanco.setEstado(usuario.getEstado());
        usuarioBanco.setCep(usuario.getCep());

    	usuarioDAO.save(usuarioBanco);
    }
    

    @RequestMapping(path = "/usuarios/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar(@RequestBody Usuario usuario) {
            Usuario usuarioBanco = usuarioDAO.save(usuario);
            return usuarioBanco;
    }

}
