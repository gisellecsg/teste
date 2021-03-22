package br.com.ideiasportsgroup.ejb.persistence.enumeration;

import java.util.Arrays;

public enum ErroEnumPersistence {

	VIOLACAO_UNIQUEKEY(0, "Violation of UNIQUE KEY constraint"), DUPLICATE_ENTRY(1, "Duplicate entry"),
	COLUMN_DOES_NOT_ALLOW_NULL(2, "Cannot insert the value NULL into column"),
	DELETE_CONFLICTED_REFERENCE_CONSTRAINT(3, "The DELETE statement conflicted with the REFERENCE constraint"),
	NO_RESULT(4, "Nenhum resultado encontrado"), MORE_THAN_ONE_RESULT(5, "Mais que um resultado encontrado"),
	ENTITY_NOT_FOUND(6, "Entidade ou objeto não encontrado"),
	INSERT_CONFLICTED_REFERENCE_CONSTRAINT(7, "The INSERT statement conflicted with the FOREIGN KEY constraint"),
	NAO_CONHECIDO_PERSISTENCIA(8, "Nao conhecido pela aplicacao por parte da persistencia");

	private final int codigo;
	private final String descricao;

	private ErroEnumPersistence(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static String names() {
		return Arrays.toString(ErroEnumPersistence.values()).replaceAll("^.|.$", "");
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static ErroEnumPersistence getByCodigo(int codigo) {
		switch (codigo) {
		case 0:
			return ErroEnumPersistence.VIOLACAO_UNIQUEKEY;
		case 1:
			return ErroEnumPersistence.DUPLICATE_ENTRY;
		case 2:
			return ErroEnumPersistence.COLUMN_DOES_NOT_ALLOW_NULL;
		case 3:
			return ErroEnumPersistence.DELETE_CONFLICTED_REFERENCE_CONSTRAINT;
		case 4:
			return ErroEnumPersistence.NO_RESULT;
		case 5:
			return ErroEnumPersistence.MORE_THAN_ONE_RESULT;
		case 6:
			return ErroEnumPersistence.ENTITY_NOT_FOUND;
		case 7:
			return ErroEnumPersistence.INSERT_CONFLICTED_REFERENCE_CONSTRAINT;
		case 8:
			return ErroEnumPersistence.NAO_CONHECIDO_PERSISTENCIA;
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return "Nome:" + this.name() + " Código:" + this.codigo + " Descrição:" + this.descricao;
	}

}
