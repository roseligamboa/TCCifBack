package br.com.tcc.rose.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Mensagem;

@Repository
public interface MensagemDAO extends CrudRepository<Mensagem, Integer>  {
}