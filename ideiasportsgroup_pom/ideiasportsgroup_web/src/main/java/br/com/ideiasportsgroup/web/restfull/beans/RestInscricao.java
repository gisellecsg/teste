package br.com.ideiasportsgroup.web.restfull.beans;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.ideiasportsgroup.ejb.domain.PessoaService;
import br.com.ideiasportsgroup.ejb.model.Pessoa;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.ExceptionDaoJpaEjb;
import br.com.ideiasportsgroup.web.restfull.application.MediaTypeConstantes;

@Path("inscricaoa")
public class RestInscricao implements IRestInscricao {

	@EJB(lookup = "java:app/br.com.ideiasportsgroup-ideiasportsgroup_ejb-0.0.1-SNAPSHOT/TesteLeo2!br.com.ideiasportsgroup.ejb.domain.TesteLeo2")
	PessoaService pessoaService;

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
		Pessoa pessoa = new Pessoa.Builder().nome("Leonardo").cpf("99999999999").rg("999999999")
				.dataNascimento(LocalDate.of(2021, 1, 1)).email("pessoa@email.com.br").telefone("21999999999").build();
		try {
			this.pessoaService.cadastrar(pessoa);
		} catch (ExceptionDaoJpaEjb e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(pessoa.toString());
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
