package br.com.tcc.rose.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.rose.dao.CandidatoDAO;
import br.com.tcc.rose.dao.CurriculoDAO;
import br.com.tcc.rose.dao.DenunciaDAO;
import br.com.tcc.rose.dao.EmpresaDAO;
import br.com.tcc.rose.dao.MensagemDAO;
import br.com.tcc.rose.dao.VagaDAO;
import br.com.tcc.rose.erro.NaoEncontrado;
import br.com.tcc.rose.erro.RequisicaoInvalida;
import br.com.tcc.rose.modelo.Candidato;
import br.com.tcc.rose.modelo.Curriculo;
import br.com.tcc.rose.modelo.Denuncia;
import br.com.tcc.rose.modelo.Empresa;
import br.com.tcc.rose.modelo.Mensagem;
import br.com.tcc.rose.modelo.Vaga;

@RestController
@RequestMapping("/api")
public class Candidatos {
    
    @Autowired
    CandidatoDAO candidatoDAO;
    
    @Autowired
    CurriculoDAO curriculoDAO;
    
    @Autowired
    DenunciaDAO denunciaDAO;
    
    @Autowired
    MensagemDAO mensagemDAO;
    
    @Autowired
    EmpresaDAO empresaDAO;
    
    @Autowired
    VagaDAO vagaDAO;
   
    ////////////////////// PESQUISA DE CANDIDATO POR NOME /////////////////////////
    
    @RequestMapping(path = "/candidatos/pesquisar/nome", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Candidato> pesquisaNome(
            @RequestParam(required = false) String contem,
            @RequestParam(required = false) String comeca
            ) {
        if(contem!=null)
            return candidatoDAO.findByNomeContaining(contem);
        else if(comeca!=null)
            return candidatoDAO.findByNomeStartingWith(comeca);
        else 
            throw new RequisicaoInvalida("Indique se contém ou começa");
    }
    
    
    ////////////////////// LISTA CANDIDATOS /////////////////////////
    
    @RequestMapping(path = "/candidatos/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Candidato> listar() {
        return candidatoDAO.findAll();    
    }
    
    ////////////////////// LISTA CANDIDATO PELA ID /////////////////////////
    
    @RequestMapping(path = "/candidatos/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Candidato buscar(@PathVariable int id) {
        final Optional<Candidato> findById = candidatoDAO.findById(id);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
    }
    
    ////////////////////// APAGA CANDIDATO PELA ID /////////////////////////
    
    @RequestMapping(path="/candidatos/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        if(candidatoDAO.existsById(id)) {
            candidatoDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
                        
    }
    
    ////////////////////// ATUALIZA CANDIDATO PELA ID /////////////////////////
    
    @RequestMapping(path="/candidatos/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Candidato candidato) {
        final Candidato candidatoBanco = this.buscar(id);

        candidatoBanco.setNome(candidato.getNome());
        candidatoBanco.setTel1(candidato.getTel1());
        candidatoBanco.setTel2(candidato.getTel2());
        candidatoBanco.setEndereco(candidato.getEndereco());
        candidatoBanco.setNumero(candidato.getNumero());
        candidatoBanco.setComplemento(candidato.getComplemento());
        candidatoBanco.setBairro(candidato.getBairro());
        candidatoBanco.setCidade(candidato.getCidade());
        candidatoBanco.setEstado(candidato.getEstado());
        candidatoBanco.setCep(candidato.getCep());
        candidatoBanco.setDescricao(candidato.getDescricao());
        candidatoBanco.setCpf(candidato.getCpf());
        candidatoBanco.setDataNasc(candidato.getDataNasc());
        candidatoBanco.setEscolaridade(candidato.getEscolaridade());
        candidatoBanco.setInstituicao(candidato.getInstituicao());
        candidatoBanco.setTempoAtuacao(candidato.getTempoAtuacao());
        candidatoBanco.setCompetencias(candidato.getCompetencias());
        candidatoBanco.setIdiomas(candidato.getIdiomas());
        candidatoBanco.setTecnologias(candidato.getTecnologias());
        candidatoBanco.setReferencias(candidato.getReferencias());
        candidatoBanco.setRedesSociais(candidato.getRedesSociais());
        candidatoBanco.setViagem(candidato.isViagem());
        candidatoBanco.setFornecedor(candidato.isFornecedor());
        
        candidatoDAO.save(candidatoBanco);
    }
    

    ////////////////////// INSERE CANDIDATO /////////////////////////
    
    @RequestMapping(path = "/candidatos/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Candidato cadastrar(@RequestBody Candidato candidato) {
            Candidato candidatoBanco = candidatoDAO.save(candidato);
            return candidatoBanco;
    }
    
    ////////////////////// GERA CURRICULO DO CANDIDATO /////////////////////////
    
    @RequestMapping(path="/candidatos/{idCandidato}/curriculos/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Curriculo geraCurriculo(@PathVariable int idCandidato, @RequestBody Curriculo curriculo) {
        
    	final Candidato candidatoBanco = this.buscar(idCandidato);
        
        curriculo.setNome(candidatoBanco.getNome());
        curriculo.setTel1(candidatoBanco.getTel1());
        curriculo.setTel2(candidatoBanco.getTel2());
        curriculo.setEndereco(candidatoBanco.getEndereco());
        curriculo.setNumero(String.valueOf(candidatoBanco.getNumero()));
        curriculo.setComplemento(candidatoBanco.getComplemento());
        curriculo.setBairro(candidatoBanco.getBairro());
        curriculo.setCidade(candidatoBanco.getCidade().toString());
        curriculo.setEstado(candidatoBanco.getEstado().toString());
        curriculo.setCep(candidatoBanco.getCep());
        curriculo.setDescricao(candidatoBanco.getDescricao());
        curriculo.setCpf(candidatoBanco.getCpf());
        curriculo.setDataNasc(candidatoBanco.getDataNasc());
        curriculo.setEscolaridade(candidatoBanco.getEscolaridade());
        curriculo.setInstituicao(candidatoBanco.getInstituicao());
        curriculo.setTempoAtuacao(candidatoBanco.getTempoAtuacao());
        curriculo.setCompetencias(candidatoBanco.getCompetencias().toString());
        curriculo.setIdiomas(candidatoBanco.getIdiomas().toString());
        curriculo.setTecnologias(candidatoBanco.getTecnologias().toString());
        curriculo.setReferencias(candidatoBanco.getReferencias().toString());
        curriculo.setRedesSociais(candidatoBanco.getRedesSociais().toString());
        curriculo.setViagem(candidatoBanco.isViagem());
        
        curriculoDAO.save(curriculo);
        
        return curriculo;
    }
    
	//////////////////////ATUALIZA CURRICULO DO CANDIDATO /////////////////////////
		    
		@RequestMapping(path="/candidatos/{idCandidato}/curriculos/{idCurriculo}", method = RequestMethod.PUT)
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void atualizarCurriculo(@PathVariable int id, @RequestBody Curriculo curriculo) {
		final Curriculo curriculoBanco = this.buscarCurriculo(id);
		
		curriculoBanco.setTel1(curriculo.getTel1());
		curriculoBanco.setTel2(curriculo.getTel2());
		curriculoBanco.setEndereco(curriculo.getEndereco());
		curriculoBanco.setNumero(curriculo.getNumero());
		curriculoBanco.setComplemento(curriculo.getComplemento());
		curriculoBanco.setBairro(curriculo.getBairro());
		curriculoBanco.setCidade(curriculo.getCidade());
		curriculoBanco.setEstado(curriculo.getEstado());
		curriculoBanco.setCep(curriculo.getCep());
		curriculoBanco.setDescricao(curriculo.getDescricao());
		curriculoBanco.setEscolaridade(curriculo.getEscolaridade());
		curriculoBanco.setInstituicao(curriculo.getInstituicao());
		curriculoBanco.setTempoAtuacao(curriculo.getTempoAtuacao());
		curriculoBanco.setCompetencias(curriculo.getCompetencias());
		curriculoBanco.setIdiomas(curriculo.getIdiomas().toString());
		curriculoBanco.setTecnologias(curriculo.getTecnologias());
		curriculoBanco.setReferencias(curriculo.getReferencias());
		curriculoBanco.setRedesSociais(curriculo.getRedesSociais());
		curriculoBanco.setViagem(curriculo.isViagem());
		
		curriculoDAO.save(curriculoBanco);
		}
		
	//////////////////////BUSCA CURRICULO PELA ID /////////////////////////
			    
	@RequestMapping(path = "/candidatos/{idCandidato}/curriculos/{idCurriculo}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Curriculo buscarCurriculo(@PathVariable int id) {
		final Optional<Curriculo> findById = curriculoDAO.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		} else {
			throw new NaoEncontrado("Currículo inexistente!");
		}
	}
	
	////////////////////LISTA DENUNCIAS //////////////////////
		
	@RequestMapping(path= "/candidatos/{idCandidato}/denuncias/", method = RequestMethod.GET)
	public List<Denuncia> recuperarDenuncia(@PathVariable int idCandidato) {
		return this.buscar(idCandidato).getDenuncias();
	}
	
	////////////////////INSERE DENUNCIA ///////////////////////
	
	@RequestMapping(path = "/candidatos/{idCandidato}/denuncias/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Denuncia criarDenuncia(@PathVariable int idCandidato, @RequestBody Denuncia denuncia) {
		Candidato candidatoBanco = this.buscar(idCandidato);
		denuncia.setId(0);
		Denuncia denunciaBanco = denunciaDAO.save(denuncia);
		candidatoDAO.save(candidatoBanco);
		return denunciaBanco;
	}
	
	////////////////////APAGA DENUNCIA ///////////////////////
	
	@RequestMapping(path= "/candidatos/{idCandidato}/denuncias/{idDenuncia}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void apagarDenuncia(@PathVariable int idCandidato, @PathVariable int idDenuncia) {
	
		Denuncia denunciaAchada=null;
		Candidato candidato = this.buscar(idCandidato);
		List<Denuncia> denuncias= candidato.getDenuncias();
		for (Denuncia denunciaLista : denuncias) {
			if(idDenuncia == denunciaLista.getId())
				denunciaAchada = denunciaLista;
		}
		if(denunciaAchada != null) {
			candidato.getDenuncias().remove(denunciaAchada);
			candidatoDAO.save(candidato);
		} else 
			throw new NaoEncontrado("Denúncia não encontrada");
		}
	
	//////////////////// EDITA DENUNCIA /////////////////////////
	
	@RequestMapping(path = "/candidatos/{idCandidato}/denuncias/{idDenuncia}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
		public void atualizarDenuncia(@PathVariable int idCandidato, @PathVariable int idDenuncia, @RequestBody Denuncia denuncia){
		if(denunciaDAO.existsById(idDenuncia)){
			denuncia.setId(idDenuncia);
			denunciaDAO.save(denuncia);
		} else 
			throw new NaoEncontrado("Denúncia não encontrada");
		}	
	
	////////////////////LISTA MENSAGENS //////////////////////
		
	@RequestMapping(path= "/candidatos/{idCandidato}/mensagens/", method = RequestMethod.GET)
	public List<Mensagem> recuperarMensagem(@PathVariable int idCandidato) {
		return this.buscar(idCandidato).getMensagens();
	}
	
	////////////////////INSERE MENSAGEM ///////////////////////
	
	@RequestMapping(path = "/candidatos/{idCandidato}/mensagens/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Mensagem criarMensagem(@PathVariable int idCandidato, @RequestBody Mensagem mensagem) {
		Candidato candidatoBanco = this.buscar(idCandidato);
		mensagem.setId(0);
		Mensagem mensagemBanco = mensagemDAO.save(mensagem);
		candidatoDAO.save(candidatoBanco);
		return mensagemBanco;
	}
	
	////////////////////APAGA MENSAGEM ///////////////////////
	
	@RequestMapping(path= "/candidatos/{idEmpresa}/mensagens/{idMensagem}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void apagarMensagem(@PathVariable int idCandidato, @PathVariable int idMensagem) {
	
		Mensagem mensagemAchada=null;
		Candidato candidato = this.buscar(idCandidato);
		List<Mensagem> mensagens = candidato.getMensagens();
		for (Mensagem mensagemLista : mensagens) {
			if(idMensagem == mensagemLista.getId())
				mensagemAchada = mensagemLista;
		}
		if(mensagemAchada != null) {
			candidato.getMensagens().remove(mensagemAchada);
			candidatoDAO.save(candidato);
		} else 
			throw new NaoEncontrado("Mensagem não encontrada");
	}
	
	
	////////////////////// PESQUISAR EMPRESA POR NOME ///////////////////////
	
	@RequestMapping(path = "/empresas/pesquisar/nomeEmpr", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Empresa> pesquisaEmpresaPorNome(
      @RequestParam(required = false) String contem,
      @RequestParam(required = false) String comeca
            ) {
        if(contem!=null)
            return empresaDAO.findByNomeContaining(contem) ;
        else if(comeca!=null)
            return empresaDAO.findByNomeStartingWith(comeca);
        else 
            throw new RequisicaoInvalida("Indicar contem ou comeca");
    }
	
	//////////////////////PESQUISAR EMPRESA POR ESTADO ///////////////////////
		
	@RequestMapping(path = "/empresas/pesquisar/estado", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Empresa> pesquisaEmpresaPorEstado(
	  @RequestParam(required = false) String contem,
	  @RequestParam(required = false) String comeca
	  ) {
		if(contem!=null)
			return empresaDAO.findByEstadoContaining(contem) ;
		else if(comeca!=null)
			return empresaDAO.findByEstadoStartingWith(comeca);
		else 
			throw new RequisicaoInvalida("Indicar contem ou comeca");
	}
	
	//////////////////////PESQUISAR EMPRESA POR CIDADE ///////////////////////
		
	@RequestMapping(path = "/empresas/pesquisar/cidade", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Empresa> pesquisaEmpresaPorCidade(
	  @RequestParam(required = false) String contem,
	  @RequestParam(required = false) String comeca
	  ) {
		if(contem!=null)
			return empresaDAO.findByCidadeContaining(contem) ;
		else if(comeca!=null)
			return empresaDAO.findByCidadeStartingWith(comeca);
		else 
			throw new RequisicaoInvalida("Indicar contem ou comeca");
	}
	
	////////////////////LISTA VAGAS DE DETERMINADA EMPRESA //////////////////////
	
	public Iterable<Vaga> recuperarVagas(@PathVariable String contem, @PathVariable String comeca) {
		Empresa empresa = (Empresa) this.pesquisaEmpresaPorNome(contem, comeca);

		if (!empresa.getVagas().isEmpty()) {
			return empresa.getVagas();
		}
		else 
            throw new RequisicaoInvalida("Empresa sem vagas no momento");
	}
	
	////////////////////LISTA VAGAS GERAL //////////////////////
		
	@RequestMapping(value="/vagas", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Vaga> listarVagas() {
        return vagaDAO.findAll();    
    }
	
	//////////////////////PESQUISAR VAGA POR CIDADE ///////////////////////
		
	@RequestMapping(path = "/vagas/pesquisar/cidade", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Vaga> pesquisaVagaPorCidade(
	@RequestParam(required = false) String contem,
	@RequestParam(required = false) String comeca
	) {
	if(contem!=null)
		return vagaDAO.findByCidadesContaining(contem) ;
	else if(comeca!=null)
		return vagaDAO.findByCidadesStartingWith(comeca);
	else 
		throw new RequisicaoInvalida("Indicar contem ou comeca");
	}
	
	//////////////////////PESQUISAR VAGA POR ESTADO ///////////////////////
	
	@RequestMapping(path = "/vagas/pesquisar/estado", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Vaga> pesquisaVagaPorEstado(
	  @RequestParam(required = false) String contem,
	  @RequestParam(required = false) String comeca
	  ) {
		if(contem!=null)
			return vagaDAO.findByEstadosContaining(contem) ;
		else if(comeca!=null)
			return vagaDAO.findByEstadosStartingWith(comeca);
		else 
			throw new RequisicaoInvalida("Indicar contem ou comeca");
	}
	
	//////////////////////PESQUISAR VAGA POR CARGO ///////////////////////
	
	@RequestMapping(path = "/vagas/pesquisar/cargo", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Vaga> pesquisaVagaPorCargo(
	  @RequestParam(required = false) String contem,
	  @RequestParam(required = false) String comeca
	  ) {
		if(contem!=null)
			return vagaDAO.findByCargoContaining(contem) ;
		else if(comeca!=null)
			return vagaDAO.findByCargoStartingWith(comeca);
		else 
			throw new RequisicaoInvalida("Indicar contem ou comeca");
	}

}
