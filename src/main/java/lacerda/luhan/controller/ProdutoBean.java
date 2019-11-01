package lacerda.luhan.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lacerda.luhan.entity.Caracteristica;
import lacerda.luhan.entity.Produto;
import lacerda.luhan.entity.Unidade;
import lacerda.luhan.exception.ProductInvalidException;
import lacerda.luhan.service.CaracteristicaService;
import lacerda.luhan.service.ProdutoService;
import lacerda.luhan.service.UnidadeService;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private List<Produto> produtos;
	private Long caracteristicaId;
	private Long unidadeId;

	private Produto produto = new Produto();

	@Inject
	private ProdutoService produtoService;

	@Inject
	private UnidadeService unidadeService;

	@Inject
	private CaracteristicaService caracteristicaService;

	@PostConstruct
	public void init() {
		this.produtos = produtoService.listAll();
	}

	public void addProduto() throws InvalidKeySpecException, NoSuchAlgorithmException {
		try {
			produtoService.create(this.produto);
			this.produtos.add(this.produto);
			clearFormFields();
		} catch (ProductInvalidException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), null));
		}
	}

	private void clearFormFields() {
		this.produto = new Produto();
	}

	public void update() {
		try {
			produtoService.update(produtos);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update successful"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Produto produto) {
		produtoService.delete(produto);
		produtos.remove(produto);
	}

	public List<Unidade> getUnidades() {
		return unidadeService.listAll();
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicaService.listAll();
	}

	public String getNomeDasUnidades() {
		List<Unidade> unidades = this.produto.getListUnidades();
		String retorno = "";
		for (Unidade unidade : unidades) {
			retorno += unidade.getDescricao() + ", ";
		}
		return retorno;
	}

	public String getNomeDasCaracteristicas() {
		List<Caracteristica> caracteristicas = this.produto.getListCaracteristica();
		String retorno = "";
		for (Caracteristica caracteristica : caracteristicas) {
			retorno += caracteristica.getDescricao() + ", ";
		}
		return retorno;
	}

	public void carregarProdutoPeloId() {
		this.produto = produtoService.findById(this.produto.getCodigo());
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public Long getCaracteristicaId() {
		return caracteristicaId;
	}

	public void setCaracteristicaId(Long caracteristicaId) {
		this.caracteristicaId = caracteristicaId;
	}

	public Long getUnidadeId() {
		return unidadeId;
	}

	public void setUnidadeId(Long unidadeId) {
		this.unidadeId = unidadeId;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public UnidadeService getUnidadeService() {
		return unidadeService;
	}

	public void setUnidadeService(UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}

	public CaracteristicaService getCaracteristicaService() {
		return caracteristicaService;
	}

	public void setCaracteristicaService(CaracteristicaService caracteristicaService) {
		this.caracteristicaService = caracteristicaService;
	}

	public void removerUnidadeDoProduto(Unidade unidade) {
		this.produto.removeUnidade(unidade);
	}

	public void removerCaracteristicaDoProduto(Caracteristica caracteristica) {
		this.produto.removeCaracteristica(caracteristica);
	}

	public void gravarUnidade() {
		Unidade unidade = unidadeService.findById(this.unidadeId);
		this.produto.adicionaUnidade(unidade);
	}

	public List<Unidade> getUnidadesDoProduto() {
		return this.produto.getListUnidades();
	}

	public List<Caracteristica> getCaracteristicasDoProduto() {
		return this.produto.getListCaracteristica();
	}

}
