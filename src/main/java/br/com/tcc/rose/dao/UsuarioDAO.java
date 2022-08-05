package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>  {
    List<Usuario> findByNomeContaining(String nome);
    List<Usuario> findByNomeStartingWith(String nome);
//    List<Usuario> findByFornecedor(boolean fornecedor);
//    List<Usuario> findByVagas(Vaga vaga);
}