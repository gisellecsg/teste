package br.com.ideiasportsgroup.web.restfull.application.exceptions;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.exceptions.ExceptionDaoJpaEjb;

@Provider
public class EjbExceptionMapper implements ExceptionMapper<ExceptionDaoJpaEjb> {

	public Response toResponse(ExceptionDaoJpaEjb exceptionDaoJpaEjb) {

		String[] erro = exceptionDaoJpaEjb.getMessage().split(";");
		int codigoErro = Integer.parseInt(erro[0]);
		String msgErro = erro[1];

		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		jsonObjectBuilder.add("ERRO", Json.createObjectBuilder().add("codigo", codigoErro).add("descricao", msgErro));

		return Response.serverError().entity(jsonObjectBuilder.build()).build();
	}

}