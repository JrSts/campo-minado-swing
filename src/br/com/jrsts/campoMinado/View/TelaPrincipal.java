package br.com.jrsts.campoMinado.View;

import javax.swing.JFrame;

import br.com.jrsts.campoMinado.Model.Tabuleiro;


@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame{

	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
		PainelTabuleiro painel = new PainelTabuleiro(tabuleiro);
		
		add(painel);
		
		setVisible(true);
		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Campo Minado");
	}
	
	public static void main(String[] args) {
		new TelaPrincipal();
	}
}
