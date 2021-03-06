package net.pay.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;
import net.mypage.db.CouponBean;
import net.mypage.db.CouponDAO;
import net.pay.db.PayBean;
import net.pay.db.PayDAO;

public class PayList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("m_id");
		if(id==null){
			ActionForward forward=new  ActionForward();
			forward.setPath("./Main.ma");
			forward.setRedirect(true);
			return forward;
		}
		
		
		//이용권 유무 들고가야함. 		
		PayDAO pdao= new PayDAO();
		PayBean currentpaybean=pdao.getCurrentPay(id);
		

		request.setAttribute("currentpaybean", currentpaybean);
		
		//쿠폰 유무 들고가야함. 
		CouponDAO cdao= new CouponDAO();
		int couponcount=cdao.getCouponCount(id);
		boolean couponcheck=false; 
		if(couponcount>0){
			couponcheck=true;
		}
		
		request.setAttribute("couponcheck", couponcheck);
		
		
		List paylist= new ArrayList();
		//페이지 구하기
		int count = pdao.getPayListCount(id);		
		// 한 화면에 보여줄 글 개수 설정
		int pageSize=2;		
		// 페이지 번호(파라미터"pageNum") 가져오기
		String pageNum= request.getParameter("pageNum");		
		// 페이지 번호가 없으면 무조건 "1"페이지 설정		
		if(pageNum==null){
			pageNum = "1";
		}		
		//10개씩 첫번째페이지 첫행 구하기
		int currentPage=Integer.parseInt(pageNum);
		// 1페이지 10 => 1  2페이지 10 => 11  3페이지 10 => 21
		int startRow=(currentPage-1)*pageSize+1;
		
		// 마지막행 구하기

		int endRow = pageSize*currentPage;
		int pageCount=0;
		int pageBlock=2;
		int startPage=0;
		int endPage=0;
		if(count!=0){
			//게시판 전체 페이지수 구하기
			//   전체 글개수count 50개 한 화면에 보여줄 글개수 pageSize 10개
			// count 50 pageSize 10 => 전체 페이지수 50/10=>5+나머지 =>5+0
			// count 58 pageSize 10 => 전체 페이지수 58/10+나머지1=>5+1=6
			pageCount = count/pageSize;
			pageCount= (count%pageSize)!=0?  pageCount+1:pageCount;
			//int pageCount = count/pageSize+(count%pageSize==0? 0:1);		
			//한 화면에 보여줄 페이지수 설정			
			// 시작하는 페이지 번호 구하기
			// 1~10 => 1		11~20 =>11		21~30=>21
			startPage=((currentPage-1)/pageBlock)*pageBlock+1;		
			// 끝나는 페이지 번호 구하기
			endPage=startPage+pageBlock-1;
			// 구한 끝페이지 보다 전체페이지 수가 작은 경우
			if(pageCount<endPage){
				endPage=pageCount;
			}
			paylist=pdao.getPayList(id, startRow, pageSize);			
		}
	
		
		request.setAttribute("pageNum", pageNum);		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("count", count);
		//페이지 구하기
		
		//결제내역리스트 들고가야함.
		request.setAttribute("paylist", paylist);
				
				

				
				
		
		ActionForward forward=new  ActionForward();
		forward.setPath("./pay/pay_result.jsp");
		forward.setRedirect(false);
		return forward;
	}
	

}
