package br.com.tcc.rose.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Referencia;

@Repository
public interface ReferenciaDAO extends CrudRepository<Referencia, Integer>  {
}