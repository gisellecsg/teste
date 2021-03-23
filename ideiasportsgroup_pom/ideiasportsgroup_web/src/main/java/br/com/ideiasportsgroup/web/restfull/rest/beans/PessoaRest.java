package br.com.ideiasportsgroup.web.restfull.rest.beans;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.ideiasportsgroup.ejb.domain.PessoaService;
import br.com.ideiasportsgroup.ejb.model.Pessoa;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.exceptions.ExceptionDaoJpaEjb;
import br.com.ideiasportsgroup.web.restfull.rest.constants.MediaTypeConstantes;

@Path("pessoa")
public class PessoaRest implements IPessoaRest {

	@EJB(lookup = "java:app/br.com.ideiasportsgroup-ideiasportsgroup_ejb-0.0.1-SNAPSHOT/PessoaService!br.com.ideiasportsgroup.ejb.domain.PessoaService")
	PessoaService pessoaService;

	@Path("/cadastrar")
	@GET
	@Consumes(MediaTypeConstantes.MEDIA_TYPE_JSON_CHARSETDEFAULT)
	@Produces(MediaTypeConstantes.MEDIA_TYPE_JSON_CHARSETDEFAULT)
	public Response cadastrar() throws ExceptionDaoJpaEjb {

		Pessoa pessoa = Pessoa.builder().comNome("Leonardo").comCpf("99999999999").comRg("999999999")
				.nasceuNaData(LocalDate.of(2021, 1, 1)).comEmail("pessoa@email.com.br").comTelefone("21999999999")
				.build();
		
		this.pessoaService.cadastrar(pessoa);

		return Response.ok().entity("Tudo OK").type(MediaType.TEXT_PLAIN_TYPE).build();

	}

}
