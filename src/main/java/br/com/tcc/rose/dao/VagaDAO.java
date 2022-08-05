package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Vaga;

@Repository
public interface VagaDAO extends CrudRepository<Vaga, Integer>  {
	
	List<Vaga> findByEstadosContaining(String estado);
    List<Vaga> findByEstadosStartingWith(String estado);
    List<Vaga> findByCidadesContaining(String cidade);
    List<Vaga> findByCidadesStartingWith(String cidade);
    List<Vaga> findByCargoContaining(String cargo);
    List<Vaga> findByCargoStartingWith(String cargo);
}