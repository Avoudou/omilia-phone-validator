package com.voudouris.alexios.uiComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.voudouris.alexios.phoneValidationCore.AmbiguitiesResolver;
import com.voudouris.alexios.phoneValidationCore.PhoneNumberValidationStringUtils;

public class PhoneValidatorMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField inputField;
	private JButton calculateAmbiguitiesBtn;
	private JTextArea display;

	public PhoneValidatorMainPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.inputField = new JTextField();
		inputField.setAlignmentX(CENTER_ALIGNMENT);
		inputField.setMaximumSize(new Dimension(400, 70));
		inputField.setFont(new Font("SansSerif", Font.BOLD, 20));

		this.calculateAmbiguitiesBtn = new JButton("Calculate");
		calculateAmbiguitiesBtn.setAlignmentX(CENTER_ALIGNMENT);
		calculateAmbiguitiesBtn.setEnabled(false);

		Font labelFont = new Font("SansSerif", Font.BOLD, 22);
		JLabel inputLabel = new JLabel("Input:");
		JLabel outputLbl = new JLabel("Output:");
		inputLabel.setFont(labelFont);
		outputLbl.setFont(labelFont);

		this.display = new JTextArea();
		display.setFont(new Font("SansSerif", Font.BOLD, 16));
		display.setEditable(false);
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setMaximumSize(new Dimension(600, 400));
		scroll.setMinimumSize(new Dimension(600, 400));

		addCalculateButtonActionListener();
		addInputValidationListener();

		this.add(Box.createVerticalStrut(40));
		this.add(inputLabel);
		this.add(inputField);
		this.add(Box.createVerticalStrut(5));
		this.add(calculateAmbiguitiesBtn);
		this.add(Box.createVerticalStrut(60));
		this.add(outputLbl);
		this.add(scroll);
	}

	public void addCalculateButtonActionListener() {
		this.calculateAmbiguitiesBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				display.setText("");
				String input = inputField.getText();
				AmbiguitiesResolver resolver = new AmbiguitiesResolver(
						PhoneNumberValidationStringUtils.splitInput(input));
				resolver.calculateResults();
				ArrayList<LinkedList<String>> possiblePhoneNumbers = resolver.getPossiblePhoneNumbers();
				for (LinkedList<String> phoneNumber : possiblePhoneNumbers) {
					String phoneStringFormat = "";
					for (String phoneDigit : phoneNumber) {
						phoneStringFormat = phoneStringFormat + phoneDigit;
					}
					display.append(phoneStringFormat + "  "
							+ PhoneNumberValidationStringUtils.getGreekPhoneNumValidityReport(phoneStringFormat)
							+ "\n");
				}
				display.append("\n");
				display.append("Number of results : " + possiblePhoneNumbers.size());

			}
		});
	}

	public void addInputValidationListener() {
		this.inputField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				handleValidity();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				handleValidity();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				handleValidity();

			}

			public void handleValidity() {
				if (!PhoneNumberValidationStringUtils.isValidInput(inputField.getText())) {
					if (!inputField.getText().equals("")) {
						display.setText("\n" + "Invalid Input");
					}
					inputField.setBackground(Color.RED);
					calculateAmbiguitiesBtn.setEnabled(false);
				} else {
					inputField.setBackground(Color.WHITE);
					calculateAmbiguitiesBtn.setEnabled(true);
				}
			}
		});

	}

}
