package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Idioma;

@Repository
public interface IdiomaDAO extends CrudRepository<Idioma, Integer>  {
    List<Idioma> findByNomeContaining(String nome);
    List<Idioma> findByNomeStartingWith(String nome);
}