package net.admin.chat.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatClient extends Thread implements ActionListener, Action {

	Frame frame;
	TextArea ta;
	TextField tf, tf2;
	Dialog dialog;
	Label label;
	Socket s1;
	DataOutputStream dos;
	DataInputStream dis;
	boolean stop;
	String host;
	
	public ChatClient(){
		launchFrame();
	}
	
	private void launchFrame(){
		frame  = new Frame("Chat");
		ta = new TextArea();
		tf = new TextField();
		frame.setBackground(Color.lightGray);
		ta.setEditable(false);
		
		frame.add(ta, BorderLayout.CENTER);
		frame.add(tf, BorderLayout.SOUTH);
		tf.addActionListener(this);

		frame.setSize(500, 300);
		frame.setVisible(true);
		tf.requestFocus();
		dialog = new Dialog(frame, "서버 ip", true);
		label = new Label("접속할 서버 IP를 입력하세요.");
		tf2 = new TextField(15);
		dialog.add(label, BorderLayout.NORTH);
		dialog.add(tf2, BorderLayout.CENTER);
		tf2.addActionListener(this);
		dialog.pack();
		dialog.show();
		service(host);
		tf2.requestFocus();
	}
	
	private void service(String host){
		try{
			 s1 = new Socket(host, 5430);  
			 dis = new DataInputStream(s1.getInputStream());
			 dos = new DataOutputStream(s1.getOutputStream());
			ta.append("접속완료..\n");	
			this.start();
		}catch(IOException io){
			io.printStackTrace();
		}
	}

  public static void main(String args[])throws IOException {
		new ChatClient();
	}

  public void actionPerformed(ActionEvent action){
		if(tf==action.getSource()){//
			try{
				String msg = tf.getText();
				ta.append(msg+'\n');
				if(msg.equals("exit")){
					ta.append("bye");
					stop=true;
					dos.close();
					s1.close();
					System.exit(0);
				}else{
					dos.writeUTF("클라이언트 :"+msg);
					tf.setText("");
				}
			}catch(IOException io){
				ta.append(io.toString()+'\n');
			}
		}else{						
			host=tf2.getText().trim();
			if(host.equals("")) host="localhost";
			dialog.dispose();
			}
	}
  
  public void run(){
		System.out.println("Thread started..");
		try{
			while(!stop){
				ta.append(dis.readUTF()+'\n');
			}
	        dis.close();
	        s1.close();
		}catch(EOFException eof){
			ta.append("Server로 부터 연결이 끊어졌습니다.");
		}catch(IOException io){
			io.printStackTrace();
		}
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		forward.setPath("");
		forward.setRedirect(false);
		return forward;
	}

}
