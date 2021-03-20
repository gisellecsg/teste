package br.com.ideiasportsgroup.rest2.beans;

import javax.ws.rs.core.Response;

public interface IRestInscricao {

	String getByNome(String nome);

	String getInscricao();

	Response criarInscricao(String nome);

	Response getInscricao2();

}