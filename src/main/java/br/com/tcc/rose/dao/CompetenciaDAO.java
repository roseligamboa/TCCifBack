package br.com.tcc.rose.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Competencia;

@Repository
public interface CompetenciaDAO extends CrudRepository<Competencia, Integer>  {
}