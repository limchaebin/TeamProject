package net.vip.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class VipInfo implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
			
		//회원 정보 가져오기
		MemberDAO memberdao=new MemberDAO();
		MemberBean memberbean=new MemberBean();
				
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("m_id"); 
		if(m_id==null){
			ActionForward forward=new ActionForward();
			forward.setRedirect(true);		
			forward.setPath("./MemberLogin.me");
			return forward;
		}		
		
		memberbean=memberdao.getMember(m_id);
		request.setAttribute("memberbean", memberbean);

		//이동 
		ActionForward forward= new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./vip/vip_info.jsp");
						
		return forward;
	}

}
