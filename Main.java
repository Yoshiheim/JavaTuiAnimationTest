import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class Main extends JFrame implements Runnable{
	//public static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	public static int time = 0;
	public static int stat = 0;
 	/*
	public static void DrawWall(JFrame frame, Point point){
		for(int i = 0; i < point.x; i++){
			for(int y = 0; y < point.y; y++){
				int d = i;
				JLabel label = new JLabel("#");
				label.setOpaque(false);
				label.setForeground(new Color(d,d,d));
				int y2 = (i*10) % 5;
				if(i % 5 == 0){
					label.setBounds(i*10,y2, i*10, 50);
				}else{
					label.setBounds(i*10,y*10, i*10, 50);
				}
				labels.add(label);
				frame.add(label);
			
				//label.setBounds(i*15-(y*5),y*10-(point.x*5), i*10-(y*5), 50);
				//labels.add(label);
			}
		}
	}
	*/ 

	@Override
	public void run(){
		while(this != null){
			time++;

			String text = "";
				for(int x = 1; x < 20; x++){
					for(int y = 1; y < 20; y++){
						if(x == y + (int)(Math.sin(time)*stat)){
							text += "\033[43m \033[0m";
						}else if(x/y < y/(int)((Math.sin(time)*2)+10)){
							text += "\033[40m \033[0m";	
						}else{
							text += "\033[44m \033[0m";	
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
	
		/*
		for(int x = 0; x < 100; x++){
				
			JCheckBox box = new JCheckBox();
		
			box.setSize(30,30);
			int y = x % 5;
			int y2 = y % 3;
			int y3 = y2 % 2;
			if(x % 2 == 0){
				box.setLocation(10*y3,30*x);
				for(int i = 1; i < 20; i++){
					JLabel box2 = new JLabel("$");
					box2.setLocation(10*y3,30*x*i);
					box2.setSize(20, 20);
					this.add(box2);
				}
			}else{
				box.setLocation(30*y,30*x);
			}
		
	
			box.addItemListener(e -> {
				if(e.getStateChange() == ItemEvent.SELECTED){
					stat += 2; 
				}else {
					stat -= 2;
				}	
			});

			this.add(box);
		}
		for(int d = 0; d < 3; d++){
			DrawWall(this, new Point(10+d*10,10+d*10));
		}
		
		
		this.setVisible(true);
		this.setSize(500, 500);
		*/
	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(Main::new);
	}
}
