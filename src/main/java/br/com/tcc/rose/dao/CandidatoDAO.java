package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Candidato;

@Repository
public interface CandidatoDAO extends CrudRepository<Candidato, Integer>  {
    List<Candidato> findByNomeContaining(String nome);
    List<Candidato> findByNomeStartingWith(String nome);
}