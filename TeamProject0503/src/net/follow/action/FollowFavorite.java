package net.follow.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.follow.db.FollowDAO;

public class FollowFavorite implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		String id= request.getParameter("m_id");
		FollowDAO fdao= new FollowDAO();
		List followfavoritelist=fdao.followfavorite(id);
		
		request.setAttribute("followfavoritelist", followfavoritelist);
		

		ActionForward forward = new ActionForward();
	    forward.setPath("./follow/followfavorite.jsp");
	    forward.setRedirect(false);

		return forward;
	}

}
