package br.com.iago.main;

import javax.swing.JOptionPane;

import br.com.iago.controller.MessageController;

public class MainApp {

	public static void main(String[] args) {
		
		MessageController msgCont = new MessageController();
		String options = "1 - Gravar mensagem na fila \n"
				+ "2 - Atualizar fila \n"
				+ "3 - Sair";
		Integer option = 3;
		
		while(true) {
			
			option = Integer.parseInt(JOptionPane.showInputDialog(options).toString());
			
			switch(option) {
				case 1 : 
					msgCont.saveMessage(
							JOptionPane.showInputDialog(
									"Digite a mensagem que deseja gravar").toString()
					);
					break;
				case 2 : 
					msgCont.readDefaultFile();
					break;
				case 3 :
					System.exit(0);
					break;
				default:
					JOptionPane.showMessageDialog(
							null, 
							"Opção não reconhecida, tente novamente.", 
							"Opção não encontrada", 
							JOptionPane.ERROR_MESSAGE);
					break;
			}
			
		}
	}
}
