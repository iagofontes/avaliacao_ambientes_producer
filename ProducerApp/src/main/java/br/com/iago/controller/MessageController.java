package br.com.iago.controller;

import java.io.File;
import java.io.FileWriter;

import javax.jms.ConnectionFactory;
import javax.swing.JOptionPane;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import br.com.iago.route.SimpleRouteBuilder;

public class MessageController {

	
	public void saveMessage(String message) {
		
		if(!message.isEmpty()) {
			
			try {
				File file = new File("/home/iago/Documents/projetos/FIAP/ambientes_componentes_desenvolvimento/avaliação/file/fileToQueue.txt");
				file.setWritable(true);
				file.setReadable(true);
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(message);
				fileWriter.close();
				
				if(this.sendToQueue()) {
					JOptionPane.showMessageDialog(
							null, 
							"Mensagem gravada com sucesso.", 
							"Mensagem gravada", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		} else {
			JOptionPane.showMessageDialog(
					null, 
					"A mensagem deve conter um texto válido.", 
					"Mensagem inválida", 
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void readDefaultFile() {
		
		if(this.sendToQueue()) {
			JOptionPane.showMessageDialog(
					null, 
					"Fila atualizada com sucesso.", 
					"Fila atualizada", 
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(
					null, 
					"Problemas ao atualizar fila, tente novamente.", 
					"Erro ao atualizar fila", 
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private boolean sendToQueue() {
		SimpleRouteBuilder routeBuilder = new SimpleRouteBuilder();
		CamelContext ctx = new DefaultCamelContext();
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
		ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		try {
			ctx.addRoutes(routeBuilder);
			ctx.start();
			Thread.sleep(30*1000);
			ctx.stop();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}

}
