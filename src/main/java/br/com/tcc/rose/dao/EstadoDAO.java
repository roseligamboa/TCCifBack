package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Estado;

@Repository
public interface EstadoDAO extends CrudRepository<Estado, Integer>  {
    List<Estado> findByNomeContaining(String nome);
    List<Estado> findByNomeStartingWith(String nome);
}