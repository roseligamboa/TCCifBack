package br.com.tcc.rose.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empresa extends Usuario {

	private String cnpj;
	private String site;
	@OneToOne
	private Usuario representante;
	@OneToMany
	private List<Vaga> vagas;
	@OneToMany
    private List<RedeSocial> redesSociais;
    @OneToMany
    private List<Denuncia> denuncias;
    @OneToMany
    private List<Mensagem> mensagens;
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Usuario getRepresentante() {
		return representante;
	}
	public void setRepresentante(Usuario representante) {
		this.representante = representante;
	}
	public List<Vaga> getVagas() {
		return vagas;
	}
	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}
	public List<RedeSocial> getRedesSociais() {
		return redesSociais;
	}
	public void setRedesSociais(List<RedeSocial> redesSociais) {
		this.redesSociais = redesSociais;
	}
	public List<Denuncia> getDenuncias() {
		return denuncias;
	}
	public void setDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}
	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
    
    
    
}