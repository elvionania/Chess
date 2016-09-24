package org.elvio.chess.xboard;

import java.util.Scanner;

public class Listener implements Runnable {
	
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void run() {
		while(true){
			if(scanner.hasNextLine()){
				System.out.println("******************************"+scanner.nextLine());
			}else{
				
			}
		}
	}	
	
	
	
}
