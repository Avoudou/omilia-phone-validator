package com.voudouris.alexios.phoneValidatorLauncher;

import javax.swing.JFrame;
import com.voudouris.alexios.uiComponents.PhoneValidatorMainPanel;

public class PVLauncher extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		PVLauncher launcher = new PVLauncher();
		launcher.createAndShowGUI();
	}

	public void createAndShowGUI() {
		this.setSize(800, 800);
		this.setTitle("Phone Number Validation (Omilia test project)");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new PhoneValidatorMainPanel());
		this.setVisible(true);
	}

}
