package br.com.tcc.rose.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Candidato extends Usuario {
	
	private String cpf;
	private String dataNasc;
	private String escolaridade;
	private String instituicao;
	private String tempoAtuacao;
	@OneToMany
	private List<Competencia> competencias;
	@OneToMany
	private List<Idioma> idiomas;
	@OneToMany
	private List<Tecnologia> tecnologias;
	@OneToMany
	private List<Referencia> referencias;
	@ManyToMany
	private List<Vaga> vagas;
	@OneToMany
    private List<RedeSocial> redesSociais;
    @OneToMany
    private List<Denuncia> denuncias;
    @OneToMany
    private List<Mensagem> mensagens;
    @OneToOne
	private Curriculo curriculo;
	private String cnh;
	private boolean viagem;
	private boolean fornecedor;
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	public String getTempoAtuacao() {
		return tempoAtuacao;
	}
	public void setTempoAtuacao(String tempoAtuacao) {
		this.tempoAtuacao = tempoAtuacao;
	}
	public List<Competencia> getCompetencias() {
		return competencias;
	}
	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}
	public List<Idioma> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}
	public List<Tecnologia> getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(List<Tecnologia> tecnologias) {
		this.tecnologias = tecnologias;
	}
	public List<Referencia> getReferencias() {
		return referencias;
	}
	public void setReferencias(List<Referencia> referencias) {
		this.referencias = referencias;
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
	public Curriculo getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public boolean isViagem() {
		return viagem;
	}
	public void setViagem(boolean viagem) {
		this.viagem = viagem;
	}
	public boolean isFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(boolean fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	
}