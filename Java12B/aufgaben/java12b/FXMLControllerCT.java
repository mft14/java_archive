package java12b;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLControllerCT {
	
	@FXML private TextArea taInput;
	@FXML private TextArea taOutput;
	@FXML private ComboBox<String> auswahl;
	private boolean makeDouble = false;
	
	@FXML void initialize() {
		System.out.println();
		auswahl.getItems().addAll("Upper-Lower --> ExAmPlE", "Double-Upper-Lower --> eExXaAmMpPlLeE",
				"Upper Case --> EXAMPLE", "Lower Case --> example", "Gaps between letters --> E x a m p l e ");
		auswahl.getSelectionModel().selectFirst();
	}
	
	@FXML protected void btnConvert() {

		String tempAuswahl = auswahl.getSelectionModel().getSelectedItem();
		if (tempAuswahl.equals("Upper-Lower --> ExAmPlE")) {
			createUpperLowerCase(false);
			System.out.println(auswahl.getId());
		}
		if (tempAuswahl.equals("Double-Upper-Lower --> eExXaAmMpPlLeE")) {
			createUpperLowerCase(true);
			System.out.println(auswahl.getId());
		}
		if (tempAuswahl.equals("Upper Case --> EXAMPLE")) {
			createUpperCaseOnly();
		}
		if (tempAuswahl.equals("Lower Case --> example")) {
			createLowerCaseOnly();
		}
		if (tempAuswahl.equals("Gaps between letters --> E x a m p l e ")) {
			createUpperCaseWithGaps();			
		}
		
	}
	
	@FXML private void createUpperLowerCase(boolean makeDouble) {
		String txtInput = taInput.getText(); //save Text to a string
		
		if(txtInput.trim().isEmpty()) { //if Textarea is empty, do warning
			JOptionPane.showMessageDialog(null, "Please enter a text!");
		} else { //if not empty, GO ON
			String lastLetter = ""; //Capturing the last letter cuz needed
			lastLetter = lastLetter + txtInput.charAt(txtInput.length()-1); //-1 otherwise outofbounce
			String txtOutput = ""; //storing the result here

				for (int i = 0; i < txtInput.length()-1; i++) {
					txtOutput = txtOutput + txtInput.toUpperCase().charAt(i);
					txtOutput = txtOutput + txtInput.toLowerCase().charAt(i+1);
					if(!makeDouble) {
						i++; //THIS is the key line for doubling!
					}
				} //for end
				//Grade zahlen nimmt er nicht an, iwie so
				if(txtInput.length() % 2 == 0) {
				} else {
					txtOutput = txtOutput + lastLetter.toUpperCase();
				}
				taOutput.setText(txtOutput);
		}
	} // UpperLowerCase void ends here
	
	@FXML private void createUpperCaseOnly() {
		taOutput.setText(taInput.getText().toUpperCase());
	}
	
	@FXML private void createLowerCaseOnly() {
		taOutput.setText(taInput.getText().toLowerCase());
	}
	
	@FXML private void createUpperCaseWithGaps() {
		String txtInput = taInput.getText(); //save Text to a string
		String txtOutput = "";
		//txtInput.toUpperCase();
		
		for (int i = 0; i < txtInput.length(); i++) {
			txtOutput = txtOutput + txtInput.charAt(i);
			txtOutput = txtOutput + " ";
		} //for end
				
		taOutput.setText(txtOutput);
	}
	
	@FXML protected void btnReset() {
		taInput.setText("");
		taOutput.setText("");
	}
	
	@FXML protected void btnClose() {
		Platform.exit();
	}
	
	

}
