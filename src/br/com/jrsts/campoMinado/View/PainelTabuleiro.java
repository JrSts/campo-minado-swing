package br.com.jrsts.campoMinado.View;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.jrsts.campoMinado.Model.Tabuleiro;


@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel{
	
	
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		
		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));
		
		tabuleiro.paraCada(c -> add(new BotaoCampo(c)));
		
		tabuleiro.registrarObservador(e -> {
			
			SwingUtilities.invokeLater(() -> {
				if (e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Voce Ganhou! :)");
				} else {
					JOptionPane.showMessageDialog(this, "Voce Perdeu! :(");
				}	
				tabuleiro.reiniciar();
			});

		});
		
		
	}
}
