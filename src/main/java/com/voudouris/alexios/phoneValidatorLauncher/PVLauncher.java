package com.voudouris.alexios.phoneValidatorLauncher;

import java.util.ArrayList;
import java.util.LinkedList;

import com.voudouris.alexios.phoneValidationCore.AmbiguitiesResolver;

import javafx.application.Application;
import javafx.stage.Stage;


public class PVLauncher   extends Application{

	public static void main(String[] args) {
		//String[] test = { "0","0", "30", "69", "700", "24", "1", "2","50", "2" };
		//String[] test = { "2","10", "69", "30", "6", "6", "4"};
		String[] phoneInput1 = { "2", "10", "690", "33", "665", "66", "4" };
		AmbiguitiesResolver resolver = new AmbiguitiesResolver(phoneInput1);
		resolver.calculateResults();

		ArrayList<LinkedList<String>> results = resolver.getPossiblePhoneNumbers();

		for (LinkedList<String> res : results) {
			System.out.println(String.join(" ", res));
		}
		System.out.println(results.size());
		
		launch(args);
	
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.show();	
	}

}
