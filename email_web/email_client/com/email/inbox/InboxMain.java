package com.email.inbox;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.email.window.WindowAdapter;


public class InboxMain {
	public static void inboxmain(String args[]){
		new AppFrame();
	}
}

@SuppressWarnings("serial")
class AppFrame extends Frame{
    Button btn = new Button("�����ʼ�");
    Label out = new Label("HELLO WORLD(�����ǩ)");
    
    @SuppressWarnings("deprecation")
	public AppFrame(){ 
   	 setSize(600,400);                  
     setLocationRelativeTo(null);      //���ô���λ����Ļ���룬��setsize����
     addWindowListener(new WindowAdapter());
     setLayout(new FlowLayout());
   	 add(btn);
   	 add(out);
   	 btn.addActionListener(new BtnActionAdapter());
   	 show();
    }
    
    class BtnActionAdapter implements ActionListener{
   	 public void actionPerformed(ActionEvent e){
   		 out.setText("����˵Ц�ˣ�����д��");
   	 }
    }
 }