package package1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class main {

	public static void main(String[] args) throws IOException {
		
		
		String fp = new String(getFileName());
		File file = new File(fp);	
		Scanner scan1 = new Scanner(file);
		Scanner scan = new Scanner(file);
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--Input File--------------------------------------------------------");
		while(scan1.hasNextLine()) {
			System.out.println(scan1.nextLine());
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------\r");
		
		int stateNum = Integer.parseInt(scan.nextLine());
		int variableNum = Integer.parseInt(scan.nextLine());
		int finalStateNum = Integer.parseInt(scan.nextLine());

		
		String statesStr = scan.nextLine();
		String[] states = new String[stateNum];
		states = statesStr.split(" ",stateNum);

		
		String finalStatesStr = scan.nextLine();
		String[] finalStatesAry = new String[finalStateNum];
		ArrayList<String> finalStates = new ArrayList<String>();
		finalStatesAry = finalStatesStr.split(" ",finalStateNum);
		for(int i =0;i<finalStateNum;i++) {
			finalStates.add(finalStatesAry[i]);
			finalStates.get(i);
		}
		
		String varStr = scan.nextLine();
		String[] variables = new String[variableNum];
		variables = varStr.split(" ",variableNum);

		
		int pathCount = stateNum * variableNum;
		
		String[] paths1d = new String[stateNum*variableNum];
		for(int i=0;i<stateNum*variableNum;i++) {
			paths1d[i] = scan.nextLine();
		}
		
		String[][] paths2d = new String[stateNum*variableNum][3];
		for(int i=0;i<stateNum*variableNum;i++) {
			for(int j=0;j<3;j++) {
				String[] asd = paths1d[i].split(" ");
				paths2d[i][j] = asd[j];
			}
		}

		ArrayList<String> inputsList = new ArrayList<String>();
		while(scan.hasNextLine()) {
			inputsList.add(scan.nextLine());
		}
		

		String[] output = new String[inputsList.size()];


		
		//DETERMINISTIC FINITE AUTOMATA
		for(int i =0;i<inputsList.size();i++) {
			String[] current = new String[inputsList.get(i).length()];
			current = inputsList.get(i).split("",current.length);
			String startState = states[0];
			String currentState =  startState;
			String pathsToOutput = "";

			
			for(int j=0;j<current.length;j++) {


				String token = current[j];
				
			
					
				for(int k= 0;k<pathCount;k++) {
					if(paths2d[k][0].equals(currentState)) {
						if(paths2d[k][1].equals(token)) {
							currentState = paths2d[k][2];
							pathsToOutput = pathsToOutput + currentState + " ";
							break;
						}
					}
				}
				

			}
			
			if(finalStates.contains(currentState)) {
				output[i] = "String: "+inputsList.get(i)+" ------->    Accepted\rRoute Taken: "+pathsToOutput;
				System.out.println(output[i]+"\r");
				
			}
			else {
				output[i] = "String: "+inputsList.get(i)+" ------->    Rejected\rRoute Taken: "+pathsToOutput;
				System.out.println(output[i]+"\r");
			}
	
		}
		
		File outFile = new File("DFA_Output.txt");
		FileWriter fw = new FileWriter(outFile);
		PrintWriter pw = new PrintWriter(fw);
		
		for(int i=0;i<output.length;i++) {
			pw.println(output[i]);
			pw.println();
		}
		
		pw.close();
		scan.close();
		scan1.close();
	   
	}
	
	
	
	
	
	
	
	public static String getFileName(){
		 JFileChooser chooser = new JFileChooser();
		 FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt file", "txt");
		 chooser.setFileFilter(filter);
		 int returnVal = chooser.showOpenDialog(null);
		 if(returnVal != JFileChooser.APPROVE_OPTION) {
		 throw new RuntimeException("Failed to choose file");
		 }
		 return chooser.getSelectedFile().getAbsolutePath();
		}
	
	
}
