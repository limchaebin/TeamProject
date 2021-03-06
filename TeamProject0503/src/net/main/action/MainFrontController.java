package net.main.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFrontController extends HttpServlet{



	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI=request.getRequestURI();
		String context=request.getContextPath();
		String command=requestURI.substring(context.length());
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/Main.ma")){
			action = new Main();
			try{
			forward=action.execute(request, response);
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/MainSearch.ma")){
			action = new MainSearch();
			try{
			forward=action.execute(request, response);
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/RealtimeAlarm.ma")){
			action = new RealtimeAlarm();
			try{
			forward=action.execute(request, response);
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}


		if(forward!=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);	
			}
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}
	
	
	
	
	
	
}
