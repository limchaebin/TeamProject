package net.category.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryFrontController extends HttpServlet{



	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI=request.getRequestURI();
		String context=request.getContextPath();
		String command=requestURI.substring(context.length());
		ActionForward forward=null;
		Action action=null;
		
		
		//주소 비교
		  if(command.equals("/CategoryList.ca")){
			  	action = new CategoryList();
			  try {
		            forward = action.execute(request, response);
		         } catch (Exception e) {
		            e.printStackTrace();
		         }
		  }else if(command.equals("/CategoryMovie.ca")){
			  	action = new CategoryMovie_InfoAction();
				  try {
			            forward = action.execute(request, response);
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
		  }else if(command.equals("/InsertReview.ca")){
			  	action = new InsertReview();
				  try {
			            forward = action.execute(request, response);
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
		  }else if(command.equals("/DeleteReview.ca")){
			  	action = new DeleteReview();
				  try {
			            forward = action.execute(request, response);
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
		  }else if(command.equals("/ModifyReview.ca")){
			  	action = new ModifyReview();
				  try {
			            forward = action.execute(request, response);
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
		  }else if(command.equals("/ModifyReviewAction.ca")){
			  	action = new ModifyReviewAction();
				  try {
			            forward = action.execute(request, response);
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
		  }else if(command.equals("/RecommendAction.ca")){
			  	action = new RecommendAction();
				  try {
			            forward = action.execute(request, response);
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
		  }else if(command.equals("/ReportAction.ca")){
			  	action = new ReportAction();
				  try {
			            forward = action.execute(request, response);
			         } catch (Exception e) {
			            e.printStackTrace();
			         }
		  }
		  
			
		  
		
		  
		//이동(주소비교에서 이동방식, 이동할곳 정보를 찾아올것)
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
