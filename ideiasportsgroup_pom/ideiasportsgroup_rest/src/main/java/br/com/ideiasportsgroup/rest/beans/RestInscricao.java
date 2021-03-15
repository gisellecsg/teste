package br.com.ideiasportsgroup.rest.beans;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import br.com.ideiasportsgroup.rest.application.MediaTypeConstantes;

@Path("inscricao")
public class RestInscricao implements IRestInscricao {

	@GET
	@Path("/inscrever/{nome}")
	@Produces(MediaTypeConstantes.MEDIA_TYPE_TEXT_PLAIN_CHARSETDEFAULT)
	public String getByNome(@PathParam("nome") String nome) {
		return new String("getByNome");
	}

	@GET
	@Path("/inscrever")
	@Produces(MediaTypeConstantes.MEDIA_TYPE_TEXT_PLAIN_CHARSETDEFAULT)
	public String getInscricao() {
		return new String("getInscricao");
	}

	@POST
	@Consumes(MediaTypeConstantes.MEDIA_TYPE_JSON_CHARSETDEFAULT)
	@Produces(MediaTypeConstantes.MEDIA_TYPE_JSON_CHARSETDEFAULT)
	public Response criarInscricao(@FormParam("nome") String nome) {
		return Response.ok("POST").build();
	}
	
	@GET
	@Path("/inscrever2")
	@Produces(MediaTypeConstantes.MEDIA_TYPE_TEXT_PLAIN_CHARSETDEFAULT)
	public Response getInscricao2() {
		return Response.ok("Inserted! Go back and check the list.").build();
	}

}
