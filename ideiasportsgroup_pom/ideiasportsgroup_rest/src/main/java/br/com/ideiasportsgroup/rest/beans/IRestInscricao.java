package br.com.ideiasportsgroup.rest.beans;

import javax.ws.rs.core.Response;

public interface IRestInscricao {

	public Response criarInscricao(String nome);
	public String getInscricao();
	public String getByNome(String nome);
	public Response getInscricao2();

}
