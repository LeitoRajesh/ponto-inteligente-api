package com.kasale.pontointeligente.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kasale.pontointeligente.api.enums.PerfilEnum;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Entidade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Column(name = "valor_hora", nullable = false)
	private BigDecimal valorHora;

	@Column(name = "qtd_horas_trabalhada_dia", nullable = false)
	private Float qtdHorasTrabalhadaDia;

	@Column(name = "qtd_horas_almoco", nullable = false)
	private Float qdtHorasAlmoco;

	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	private PerfilEnum perfil;

	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;

	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Lancamento> listaLancamentos;

	@Transient
	public Optional<Float> getQtdHorasTrabalhadaDiaOpt() {
		return Optional.ofNullable(this.qtdHorasTrabalhadaDia);
	}

	@Transient
	public Optional<Float> getQtdHorasAlmocoOpt() {
		return Optional.ofNullable(this.qdtHorasAlmoco);
	}

	@Transient
	public Optional<BigDecimal> getValorHoraOpt() {
		return Optional.ofNullable(this.valorHora);
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getValorHora() {
		return valorHora;
	}

	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	public Float getQtdHorasTrabalhadaDia() {
		return qtdHorasTrabalhadaDia;
	}

	public void setQtdHorasTrabalhadaDia(Float qtdHorasTrabalhadaDia) {
		this.qtdHorasTrabalhadaDia = qtdHorasTrabalhadaDia;
	}

	public Float getQdtHorasAlmoco() {
		return qdtHorasAlmoco;
	}

	public void setQdtHorasAlmoco(Float qdtHorasAlmoco) {
		this.qdtHorasAlmoco = qdtHorasAlmoco;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Lancamento> getListaLancamentos() {
		return listaLancamentos;
	}

	public void setListaLancamentos(List<Lancamento> listaLancamentos) {
		this.listaLancamentos = listaLancamentos;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", qtdHorasTrabalhadaDia=" + qtdHorasTrabalhadaDia + ", qdtHorasAlmoco="
				+ qdtHorasAlmoco + ", perfil=" + perfil + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
				+ dataAtualizacao + ", empresa=" + empresa + ", listaLancamentos=" + listaLancamentos + "]";
	}

}
