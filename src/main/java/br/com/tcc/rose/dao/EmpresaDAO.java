package br.com.tcc.rose.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.rose.modelo.Empresa;

@Repository
public interface EmpresaDAO extends CrudRepository<Empresa, Integer>  {
    List<Empresa> findByNomeContaining(String nome);
    List<Empresa> findByNomeStartingWith(String nome);
    List<Empresa> findByEstadoContaining(String estado);
    List<Empresa> findByEstadoStartingWith(String estado);
    List<Empresa> findByCidadeContaining(String cidade);
    List<Empresa> findByCidadeStartingWith(String cidade);
}