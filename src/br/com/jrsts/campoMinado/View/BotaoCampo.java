package br.com.jrsts.campoMinado.View;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.jrsts.campoMinado.Model.Campo;
import br.com.jrsts.campoMinado.Model.CampoEvento;
import br.com.jrsts.campoMinado.Model.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {
	
	private Campo campo;
	
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLODIDO = new Color(189, 66, 68);
	private final Color TXT_VERDE = new Color(0, 100, 0);
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(this);
		campo.registrarObservador(this);
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch (evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		
		case MARCAR: 
			aplicarEstiloMarcar();
			break;
		
		case EXPLODIR: 
			aplicarEstiloExplodir();
			break;
		
		default:
			aplicarEstiloDefault();
			
		} 
	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIDO);
		setForeground(Color.WHITE);
		setText("X");
		
	}

	private void aplicarEstiloDefault() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");		
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void aplicarEstiloAbrir() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if (campo.isMinado()) {
			setBackground(BG_EXPLODIDO);
			return;
		}
		
		setBackground(BG_PADRAO);
		
		switch (campo.minasNaVizinhanca()) {
		case 1: 
			setForeground(TXT_VERDE);
			break;
		case 2: 
			setForeground(Color.BLUE);
			break;
		case 3: 
			setForeground(Color.YELLOW);
			break;
		case 4: 
		case 5: 
		case 6: 
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);			
		}
		
		String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "" ;
		setText(valor);
		
	}
	
	// eventos do mouse 
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			campo.abrir();
		} else {
			campo.alternarMarcacao();
			
		}
	}
}
