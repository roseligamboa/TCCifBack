package br.com.tcc.rose.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Denuncia implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private Usuario denunciante;
	private Usuario denunciado;
	private String tipo;
	private String detalhe;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getDenunciante() {
		return denunciante;
	}
	public void setDenunciante(Usuario denunciante) {
		this.denunciante = denunciante;
	}
	public Usuario getDenunciado() {
		return denunciado;
	}
	public void setDenunciado(Usuario denunciado) {
		this.denunciado = denunciado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	
	

}
