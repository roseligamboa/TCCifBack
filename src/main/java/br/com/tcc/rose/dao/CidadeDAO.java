package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Cidade;

@Repository
public interface CidadeDAO extends CrudRepository<Cidade, Integer>  {
    List<Cidade> findByNomeContaining(String nome);
    List<Cidade> findByNomeStartingWith(String nome);
}