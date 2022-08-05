package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Curriculo;

@Repository
public interface CurriculoDAO extends CrudRepository<Curriculo, Integer>  {
    List<Curriculo> findByEstadoContaining(String estado);
    List<Curriculo> findByEstadoStartingWith(String estado);
    List<Curriculo> findByCidadeContaining(String cidade);
    List<Curriculo> findByCidadeStartingWith(String cidade);
    List<Curriculo> findByTempoAtuacaoContaining(String tempoAtuacao);
    List<Curriculo> findByTempoAtuacaoStartingWith(String tempoAtuacao);
    List<Curriculo> findByCompetenciasContaining(String competencia);
    List<Curriculo> findByCompetenciasStartingWith(String competencia);
    List<Curriculo> findByViagemContaining(Boolean viagem);
}