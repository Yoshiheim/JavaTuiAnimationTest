import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class Main extends JFrame implements Runnable{
	public static int time = 0;
	public static int stat = 0;

	@Override
	public void run(){
		while(this != null){
			time++;
			
			String word = ";:',. ";
			String text = "";
			for(int x = 1; x < 20; x++){
				for(int y = 1; y < 20; y++){
					if (0.1 >= Math.sin(time*20) && x == 1){
						text += "\033[44m \033[0m";	
					}else if(x%word.length() >= time%word.length()){
						text += word.charAt(x%word.length());
					}else{
						text += " ";	
					}
				}
				text += "\n";
			}
			text += "\n";
	
			System.out.printf("%s\n", text);
			text = "";
			try{
				Thread.sleep(100);
			}catch(Exception e){
				Thread.currentThread().interrupt();
			}	
		}
	}
	
	public Main(){
		new Thread(this).start();
	
	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(Main::new);
	}
}
