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
    Button btn = new Button("接收邮件");
    Label out = new Label("HELLO WORLD(输出标签)");
    
    @SuppressWarnings("deprecation")
	public AppFrame(){ 
   	 setSize(600,400);                  
     setLocationRelativeTo(null);      //设置窗口位于屏幕中央，在setsize后面
     addWindowListener(new WindowAdapter());
     setLayout(new FlowLayout());
   	 add(btn);
   	 add(out);
   	 btn.addActionListener(new BtnActionAdapter());
   	 show();
    }
    
    class BtnActionAdapter implements ActionListener{
   	 public void actionPerformed(ActionEvent e){
   		 out.setText("真是说笑了，您先写吧");
   	 }
    }
 }