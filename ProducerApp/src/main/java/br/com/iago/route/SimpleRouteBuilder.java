package br.com.iago.route;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:/home/iago/Documents/projetos/FIAP/ambientes_componentes_desenvolvimento/avaliação/file")
			.split()
			.tokenize("\n")
			.to("jms:queue:avaliacao");
	}
	
}
