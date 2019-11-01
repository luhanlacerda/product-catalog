package lacerda.luhan.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Produto {

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String codigo;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String descricao;

	// TODO inserir atributo de imagem
	// private IMAGEM? imagem;

	@Column(nullable = false)
	private String codigoDeBarras;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "produto_caracteristica", joinColumns = @JoinColumn(name = "id_produto"), inverseJoinColumns = @JoinColumn(name = "id_caracteristica"))
	private List<Caracteristica> listCaracteristica;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "produto_unidade", joinColumns = @JoinColumn(name = "id_produto"), inverseJoinColumns = @JoinColumn(name = "id_unidade"))
	private List<Unidade> listUnidades;

	@Column(nullable = false)
	private boolean delecao;

	public Produto() {
		super();
		this.listCaracteristica = new ArrayList<Caracteristica>();
		this.listUnidades = new ArrayList<Unidade>();
	}

	public Produto(String codigo, String nome, String descricao, String codigoDeBarras,
			List<Caracteristica> listCaracteristica, List<Unidade> listUnidades, boolean delecao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.codigoDeBarras = codigoDeBarras;
		this.listCaracteristica = listCaracteristica;
		this.listUnidades = listUnidades;
		this.delecao = delecao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public List<Caracteristica> getListCaracteristica() {
		return listCaracteristica;
	}

	public void setListCaracteristica(List<Caracteristica> listCaracteristica) {
		this.listCaracteristica = listCaracteristica;
	}

	public boolean isDelecao() {
		return delecao;
	}

	public void setDelecao(boolean delecao) {
		this.delecao = delecao;
	}

	public List<Unidade> getListUnidades() {
		return listUnidades;
	}

	public void setListUnidades(List<Unidade> listUnidades) {
		this.listUnidades = listUnidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((codigoDeBarras == null) ? 0 : codigoDeBarras.hashCode());
		result = prime * result + (delecao ? 1231 : 1237);
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((listCaracteristica == null) ? 0 : listCaracteristica.hashCode());
		result = prime * result + ((listUnidades == null) ? 0 : listUnidades.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoDeBarras == null) {
			if (other.codigoDeBarras != null)
				return false;
		} else if (!codigoDeBarras.equals(other.codigoDeBarras))
			return false;
		if (delecao != other.delecao)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (listCaracteristica == null) {
			if (other.listCaracteristica != null)
				return false;
		} else if (!listCaracteristica.equals(other.listCaracteristica))
			return false;
		if (listUnidades == null) {
			if (other.listUnidades != null)
				return false;
		} else if (!listUnidades.equals(other.listUnidades))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", codigoDeBarras="
				+ codigoDeBarras + ", listCaracteristica=" + listCaracteristica + ", listUnidades=" + listUnidades
				+ ", delecao=" + delecao + "]";
	}

	public void removeUnidade(Unidade unidade) {
		this.listUnidades.remove(unidade);
	}

	public void removeCaracteristica(Caracteristica caracteristica) {
		this.listCaracteristica.remove(caracteristica);
	}

	public void adicionaUnidade(Unidade unidade) {
		this.listUnidades.add(unidade);
	}

	public void adicionaCaracteristica(Caracteristica caracteristica) {
		this.listCaracteristica.add(caracteristica);
	}

}
