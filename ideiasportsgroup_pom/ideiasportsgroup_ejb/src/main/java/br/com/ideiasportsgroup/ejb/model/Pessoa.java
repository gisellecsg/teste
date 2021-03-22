package br.com.ideiasportsgroup.ejb.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PESSOA", uniqueConstraints = { @UniqueConstraint(name = "UK_rg", columnNames = { "rg" }),
		@UniqueConstraint(name = "UK_cpf", columnNames = { "cpf" }),
		@UniqueConstraint(name = "UK_email", columnNames = { "email" }) })
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String rg;
	@Column(nullable = false)
	private String cpf;
	@Column(nullable = false)
	private LocalDate dataNascimento;
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = false)
	private String email;

	public Pessoa() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static class Builder {
		private String nome;
		private String rg;
		private String cpf;
		private LocalDate dataNascimento;
		private String telefone;
		private String email;

		public Builder nome(String nome) {
			this.nome(nome);
			return this;
		}

		public Builder rg(String rg) {
			this.rg(rg);
			return this;
		}

		public Builder cpf(String cpf) {
			this.cpf(cpf);
			return this;
		}

		public Builder dataNascimento(LocalDate dataNascimento) {
			this.dataNascimento(dataNascimento);
			return this;
		}

		public Builder telefone(String telefone) {
			this.telefone(telefone);
			return this;
		}

		public Builder email(String email) {
			this.email(email);
			return this;
		}

		public Pessoa build() {
			return new Pessoa(this);
		}
	}

	private Pessoa(Builder builder) {
		this.nome = builder.nome;
		this.rg = builder.rg;
		this.cpf = builder.cpf;
		this.dataNascimento = builder.dataNascimento;
		this.telefone = builder.telefone;
		this.email = builder.email;
	}

}