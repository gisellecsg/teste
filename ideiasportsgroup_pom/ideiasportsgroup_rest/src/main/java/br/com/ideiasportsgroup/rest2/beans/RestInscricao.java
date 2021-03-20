package br.com.ideiasportsgroup.rest2.beans;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.ideiasportsgroup.ejb.TesteLeo2;
import br.com.ideiasportsgroup.rest2.application.MediaTypeConstantes;

@Path("inscricaoa")
public class RestInscricao implements IRestInscricao {

	@EJB
	TesteLeo2 teste;

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
		/*
		 * TesteLeo2 teste = null; try { InitialContext ctx = new InitialContext();
		 * Properties properties = new Properties();
		 * properties.load(this.getClass().getClassLoader().getResourceAsStream(
		 * "projeto_maven.properties")); teste = (TesteLeo2) ctx.lookup(
		 * "java:app/br.com.ideiasportsgroup-ideiasportsgroup_ejb-0.0.1-SNAPSHOT/TesteLeo2!br.com.ideiasportsgroup.ejb.TesteLeo2"
		 * ); } catch (NamingException | IOException e) {
		 * System.out.println("ERRO@@@@@@@@@@@@@@@ ");
		 * System.out.println(e.getMessage()); }
		 */
		return new String(teste.teste());
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
