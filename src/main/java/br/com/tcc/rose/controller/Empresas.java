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

import br.com.tcc.rose.dao.CurriculoDAO;
import br.com.tcc.rose.dao.DenunciaDAO;
import br.com.tcc.rose.dao.EmpresaDAO;
import br.com.tcc.rose.dao.MensagemDAO;
import br.com.tcc.rose.dao.UsuarioDAO;
import br.com.tcc.rose.dao.VagaDAO;
import br.com.tcc.rose.erro.NaoEncontrado;
import br.com.tcc.rose.erro.RequisicaoInvalida;
import br.com.tcc.rose.modelo.Candidato;
import br.com.tcc.rose.modelo.Curriculo;
import br.com.tcc.rose.modelo.Denuncia;
import br.com.tcc.rose.modelo.Empresa;
import br.com.tcc.rose.modelo.Mensagem;
import br.com.tcc.rose.modelo.Usuario;
import br.com.tcc.rose.modelo.Vaga;

@RestController
@RequestMapping("/api")
public class Empresas {
    
    @Autowired
    EmpresaDAO empresaDAO;
    
    @Autowired
    UsuarioDAO usuarioDAO;
    
    @Autowired
    MensagemDAO mensagemDAO;
    
    @Autowired
    DenunciaDAO denunciaDAO;
    
    @Autowired
    VagaDAO vagaDAO;
    
    @Autowired
    CurriculoDAO curriculoDAO;
   
   
    ////////////////////// PESQUISA DE EMPRESA POR NOME /////////////////////////
    
    @RequestMapping(path = "/empresas/pesquisar/nome", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Empresa> pesquisaNome(
            @RequestParam(required = false) String contem,
            @RequestParam(required = false) String comeca
            ) {
        if(contem!=null)
            return empresaDAO.findByNomeContaining(contem);
        else if(comeca!=null)
            return empresaDAO.findByNomeStartingWith(comeca);
        else 
            throw new RequisicaoInvalida("Indique se contém ou começa");
    }
    
    
    ////////////////////// LISTA EMPRESAS /////////////////////////
    
    @RequestMapping(path = "/empresas/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Empresa> listar() {
        return empresaDAO.findAll();    
    }
    
    ////////////////////// LISTA EMPRESA PELA ID /////////////////////////
    
    @RequestMapping(path = "/empresas/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Empresa buscar(@PathVariable int id) {
        final Optional<Empresa> findById = empresaDAO.findById(id);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
    }
    
    ////////////////////// APAGA EMPRESA PELA ID /////////////////////////
    
    @RequestMapping(path="/empresas/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        if(empresaDAO.existsById(id)) {
            empresaDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("ID não encontrada!");
        }
                        
    }
    
    ////////////////////// ATUALIZA EMPRESA PELA ID /////////////////////////
    
    @RequestMapping(path="/empresas/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Empresa empresa) {
        final Empresa empresaBanco = this.buscar(id);

        empresaBanco.setNome(empresa.getNome());
        empresaBanco.setTel1(empresa.getTel1());
        empresaBanco.setTel2(empresa.getTel2());
        empresaBanco.setEndereco(empresa.getEndereco());
        empresaBanco.setNumero(empresa.getNumero());
        empresaBanco.setComplemento(empresa.getComplemento());
        empresaBanco.setBairro(empresa.getBairro());
        empresaBanco.setCidade(empresa.getCidade());
        empresaBanco.setEstado(empresa.getEstado());
        empresaBanco.setCep(empresa.getCep());
        empresaBanco.setDescricao(empresa.getDescricao());
        empresaBanco.setCnpj(empresa.getCnpj());
        empresaBanco.setRedesSociais(empresa.getRedesSociais());
        empresaBanco.setSite(empresa.getSite());
        empresaBanco.setRepresentante(empresa.getRepresentante());
        
        empresaDAO.save(empresaBanco);
    }
    

    ////////////////////// INSERE EMPRESA /////////////////////////
    
    @RequestMapping(path = "/empresas/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa cadastrar(@RequestBody Empresa empresa) {
            Empresa empresaBanco = empresaDAO.save(empresa);
            return empresaBanco;
    }
    
    //////////////////// INSERE REPRESENTANTE ///////////////////////
    
    @RequestMapping(path = "/empresas/{idEmpresa}/representantes/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarRepresentante(@PathVariable int idEmpresa, @RequestBody Usuario usuario) {
        Empresa empresaBanco = this.buscar(idEmpresa);
        usuario.setId(0);
        Usuario usuarioBanco = usuarioDAO.save(usuario);
        empresaDAO.save(empresaBanco);
        return usuarioBanco;
    }
    
    ////////////////////LISTA REPRESENTANTE ///////////////////////
    
    @RequestMapping(path = "/empresas/{idEmpresa}/representante/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Usuario listarRepresentante(@PathVariable int idEmpresa) {
        return this.buscar(idEmpresa).getRepresentante();
    }
    
	//////////////////// EDITA REPRESENTANTE /////////////////////////
	
	@RequestMapping(path = "/empresas/{idEmpresa}/representante/{idRepresentante}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarRepresentante(@PathVariable int idEmpresa, @PathVariable int idRepresentante, @RequestBody Usuario representante){
        if(usuarioDAO.existsById(idRepresentante)){
            representante.setId(idRepresentante);
            usuarioDAO.save(representante);
        } else 
            throw new NaoEncontrado("Usuário não encontrado");
    }  
	
	////////////////////LISTA MENSAGENS //////////////////////
		
	@RequestMapping(path= "/empresas/{idEmpresa}/mensagens/", method = RequestMethod.GET)
	public List<Mensagem> recuperarMensagem(@PathVariable int idEmpresa) {
		return this.buscar(idEmpresa).getMensagens();
	}
    
	////////////////////INSERE MENSAGEM ///////////////////////
	    
	@RequestMapping(path = "/empresas/{idEmpresa}/mensagens/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
		public Mensagem criarMensagem(@PathVariable int idEmpresa, @RequestBody Mensagem mensagem) {
		Empresa empresaBanco = this.buscar(idEmpresa);
		mensagem.setId(0);
		Mensagem mensagemBanco = mensagemDAO.save(mensagem);
		empresaDAO.save(empresaBanco);
		return mensagemBanco;
	}

	////////////////////APAGA MENSAGEM ///////////////////////
	
	@RequestMapping(path= "/empresas/{idEmpresa}/mensagens/{idMensagem}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagarMensagem(@PathVariable int idEmpresa, @PathVariable int idMensagem) {
        
        Mensagem mensagemAchada=null;
        Empresa empresa= this.buscar(idEmpresa);
        List<Mensagem> mensagens = empresa.getMensagens();
        for (Mensagem mensagemLista : mensagens) {
            if(idMensagem == mensagemLista.getId())
                mensagemAchada = mensagemLista;
        }
        if(mensagemAchada != null) {
            empresa.getMensagens().remove(mensagemAchada);
            empresaDAO.save(empresa);
        } else 
            throw new NaoEncontrado("Mensagem não encontrada");
    }
	
	//////////////////// LISTA DENUNCIAS //////////////////////
	
	@RequestMapping(path= "/empresas/{idEmpresa}/denuncias/", method = RequestMethod.GET)
    public List<Denuncia> recuperarDenuncia(@PathVariable int idEmpresa) {
        return this.buscar(idEmpresa).getDenuncias();
    }
	
	////////////////////INSERE DENUNCIA ///////////////////////
    
	@RequestMapping(path = "/empresas/{idEmpresa}/denuncias/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
		public Denuncia criarDenuncia(@PathVariable int idEmpresa, @RequestBody Denuncia denuncia) {
		Empresa empresaBanco = this.buscar(idEmpresa);
		denuncia.setId(0);
		Denuncia denunciaBanco = denunciaDAO.save(denuncia);
		empresaDAO.save(empresaBanco);
		return denunciaBanco;
	}

	////////////////////APAGA DENUNCIA ///////////////////////
	
	@RequestMapping(path= "/empresas/{idEmpresa}/denuncias/{idDenuncia}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagarDenuncia(@PathVariable int idEmpresa, @PathVariable int idDenuncia) {
        
        Denuncia denunciaAchada=null;
        Empresa empresa= this.buscar(idEmpresa);
        List<Denuncia> denuncias= empresa.getDenuncias();
        for (Denuncia denunciaLista : denuncias) {
            if(idDenuncia == denunciaLista.getId())
                denunciaAchada = denunciaLista;
        }
        if(denunciaAchada != null) {
            empresa.getDenuncias().remove(denunciaAchada);
            empresaDAO.save(empresa);
        } else 
            throw new NaoEncontrado("Denúncia não encontrada");
    }
	
	//////////////////// EDITA DENUNCIA /////////////////////////
	
	@RequestMapping(path = "/empresas/{idEmpresa}/denuncias/{idDenuncia}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarDenuncia(@PathVariable int idEmpresa, @PathVariable int idDenuncia, @RequestBody Denuncia denuncia){
        if(denunciaDAO.existsById(idDenuncia)){
            denuncia.setId(idDenuncia);
            denunciaDAO.save(denuncia);
        } else 
            throw new NaoEncontrado("Denúncia não encontrada");
    }	
	
	//////////////////// LISTA VAGAS //////////////////////
	
	@RequestMapping(path= "/empresas/{idEmpresa}/vagas/", method = RequestMethod.GET)
    public Iterable<Vaga> recuperarVagas(@PathVariable int idEmpresa) {
        return this.buscar(idEmpresa).getVagas();
    }

	////////////////////INSERE VAGA ///////////////////////
    
	@RequestMapping(path = "/empresas/{idEmpresa}/vagas/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
		public Vaga criarVaga(@PathVariable int idEmpresa, @RequestBody Vaga vaga) {
		Empresa empresaBanco = this.buscar(idEmpresa);
		vaga.setId(0);
		Vaga vagaBanco = vagaDAO.save(vaga);
		empresaDAO.save(empresaBanco);
		return vagaBanco;
	}
	
	////////////////////APAGA VAGA ///////////////////////
	
    @RequestMapping(path= "/empresas/{idEmpresa}/vagas/{idVaga}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagarVaga(@PathVariable int idEmpresa, @PathVariable int idVaga) {
        
        Vaga vagaAchada=null;
        Empresa empresa= this.buscar(idEmpresa);
        List<Vaga> vagas= empresa.getVagas();
        for (Vaga vagaLista : vagas) {
            if(idVaga == vagaLista.getId())
                vagaAchada = vagaLista;
        }
        if(vagaAchada != null) {
            empresa.getVagas().remove(vagaAchada);
            empresaDAO.save(empresa);
        } else 
            throw new NaoEncontrado("Vaga não encontrada");
    }	
	
	//////////////////// EDITA VAGA /////////////////////////
	
	@RequestMapping(path = "/empresas/{idEmpresa}/vagas/{idVaga}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void atualizarVaga(@PathVariable int idEmpresa, @PathVariable int idVaga, @RequestBody Vaga vaga){
        if(vagaDAO.existsById(idVaga)){
            vaga.setId(idVaga);
            vagaDAO.save(vaga);
        } else 
            throw new NaoEncontrado("Vaga não encontrada");
    }
	
	////////////////////// PESQUISAR CURRICULO POR ESTADO ///////////////////////
	
	@RequestMapping(path = "/curriculos/pesquisar/estado", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Curriculo> pesquisaCurriculoPorEstado(
      @RequestParam(required = false) String contem,
      @RequestParam(required = false) String comeca
            ) {
        if(contem!=null)
            return curriculoDAO.findByEstadoContaining(contem) ;
        else if(comeca!=null)
            return curriculoDAO.findByEstadoStartingWith(comeca);
        else 
            throw new RequisicaoInvalida("Indicar contem ou comeca");
    }
	
	//////////////////////PESQUISAR CURRICULO POR CIDADE ///////////////////////
		
	@RequestMapping(path = "/curriculos/pesquisar/cidade", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Curriculo> pesquisaCurriculoPorCidade(
	  @RequestParam(required = false) String contem,
	  @RequestParam(required = false) String comeca
	  ) {
		if(contem!=null)
			return curriculoDAO.findByCidadeContaining(contem) ;
		else if(comeca!=null)
			return curriculoDAO.findByCidadeStartingWith(comeca);
		else 
			throw new RequisicaoInvalida("Indicar contem ou comeca");
	}
	
	//////////////////////PESQUISAR CURRICULO POR TEMPO DE ATUAÇÃO ///////////////////////
	
	@RequestMapping(path = "/curriculos/pesquisar/tempoAtuacao", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Curriculo> pesquisaCurriculoPorTempoAtuacao(
	  @RequestParam(required = false) String contem,
	  @RequestParam(required = false) String comeca
	  ) {
		if(contem!=null)
			return curriculoDAO.findByTempoAtuacaoContaining(contem) ;
		else if(comeca!=null)
			return curriculoDAO.findByTempoAtuacaoStartingWith(comeca);
		else 
			throw new RequisicaoInvalida("Indicar contem ou comeca");
	}
	
	//////////////////////PESQUISAR CURRICULO POR COMPETÊNCIAS ///////////////////////
		
	@RequestMapping(path = "/curriculos/pesquisar/competencias", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Curriculo> pesquisaCurriculoPorCompetencias(
	  @RequestParam(required = false) String contem,
	  @RequestParam(required = false) String comeca
	  ) {
		if(contem!=null)
			return curriculoDAO.findByCompetenciasContaining(contem) ;
		else if(comeca!=null)
			return curriculoDAO.findByCompetenciasStartingWith(comeca);
		else 
			throw new RequisicaoInvalida("Indicar contem ou comeca");
	}
	
	//////////////////////PESQUISAR CURRICULO POR VIAGENS ///////////////////////
	
	@RequestMapping(path = "/curriculos/pesquisar/viagem", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Curriculo> pesquisaCurriculoPorDisponibilidade(
	  @RequestParam(required = false) Boolean viagem) {
		if(viagem!=null)
			return curriculoDAO.findByViagemContaining(viagem) ;
		else 
			throw new RequisicaoInvalida("Nada encontrado na pesquisa");
	}


}
