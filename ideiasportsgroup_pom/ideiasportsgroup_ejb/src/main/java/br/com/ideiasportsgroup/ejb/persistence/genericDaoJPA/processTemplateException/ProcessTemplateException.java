package br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.processTemplateException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;

import br.com.ideiasportsgroup.ejb.domain.PessoaService;
import br.com.ideiasportsgroup.ejb.model.Pessoa;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.exceptions.ExceptionDaoJpaEjb;

public abstract class ProcessTemplateException {

	public void process(PessoaService pessoaService, Pessoa pessoa) {
		try {
			doProcess(pessoaService, pessoa);
		} catch (ExceptionDaoJpaEjb exceptionDaoJpaEjb) {

			String[] erro = exceptionDaoJpaEjb.getMessage().split(";");
			int codigoErro = Integer.parseInt(erro[0]);
			String msgErro = erro[1];

			JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
			jsonObjectBuilder.add("ERRO",
					Json.createObjectBuilder().add("codigo", codigoErro).add("descricao", msgErro));

			Response.serverError().entity(jsonObjectBuilder.build());

		}
	}

	// override this method in a subclass, to process the stream.
	public abstract void doProcess(PessoaService pessoaService, Pessoa pessoa) throws ExceptionDaoJpaEjb;
}