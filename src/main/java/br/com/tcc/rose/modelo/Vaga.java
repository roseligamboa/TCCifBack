package br.com.tcc.rose.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Vaga implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String cargo;
	@ManyToMany
	private List<Cidade> cidades;
	@ManyToMany
	private List<Estado> estados;
	private Date dataInclusao;
	private int prazoCampo;
	private String prazoPagto;
	private Long valor;
	private String experienciaDesejada;
	private String descricao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public int getPrazoCampo() {
		return prazoCampo;
	}
	public void setPrazoCampo(int prazoCampo) {
		this.prazoCampo = prazoCampo;
	}
	public String getPrazoPagto() {
		return prazoPagto;
	}
	public void setPrazoPagto(String prazoPagto) {
		this.prazoPagto = prazoPagto;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
	public String getExperienciaDesejada() {
		return experienciaDesejada;
	}
	public void setExperienciaDesejada(String experienciaDesejada) {
		this.experienciaDesejada = experienciaDesejada;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}