import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.SwingUtilities;

public class Main implements Runnable{
	private static int time = 0;
	private static int timeD = 0;
	private static int getTerminalProperty(String command) {
		try {
			Process process = new ProcessBuilder("/bin/sh", "-c", command + " </dev/tty").start();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line = reader.readLine();
				if (line != null) {
					return Integer.parseInt(line.trim());
				}
			}
		} catch (Exception e) {
			// Fallback default value if not running in a real interactive terminal
		}
		return 80; // Standard fallback default
	}
	
	private static String word = "HELLO WORLD";
	private static String text = "";
	private static String sprite = "|;:,. ";

	private static String Message = "@Yoshiheim";

	private static int columns = getTerminalProperty("tput cols");
	private static int rows = getTerminalProperty("tput lines");
	@Override
	public void run(){
		while(this != null){
			time++;
			if (time%3 == 0){
				timeD++;
			}
			if(timeD%2==0){
				timeD+=timeD;
			}
			for(int i = 1; i < 4; i++){

			for(int x = 1; x < rows; x++){
				for(int y = 1; y < columns; y++){
					if(y-(time%x)>0&&y-(time%10)<x*i&&x-y-(time%10)>0){
						text += "\033[30;45m\033[30m"+word.charAt((x-y+(timeD%10))/(word.length()*(x+y+(time%10))))+"\033[00m";
					}else if(x == 3&&y > Message.length()) {
						text += Message.charAt((x+y)%Message.length());
					}else if(x < y){
						text += String.format("\033[30;42m%c\033[00m", sprite.charAt(((time%3)+x*y-(time%2))%sprite.length()));
					}else{
						text += " ";
					}	
				}
				text += "\n";
			}
			text += "\n";
			}
	
			System.out.printf("%s\n", text);
			text = "";
			try{
				Thread.sleep(16);
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
