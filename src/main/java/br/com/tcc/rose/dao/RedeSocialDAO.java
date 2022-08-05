package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.RedeSocial;

@Repository
public interface RedeSocialDAO extends CrudRepository<RedeSocial, Integer>  {
    List<RedeSocial> findByNomeContaining(String nome);
    List<RedeSocial> findByNomeStartingWith(String nome);
}