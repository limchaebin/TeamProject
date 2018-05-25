package net.follow.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.follow.db.FollowDAO;

public class FollowReview implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//
		//FollowFavorite.fo?id=<%mv.getm_id%/>들고오기
		//id값 챙겨왔다.

		request.setCharacterEncoding("utf-8");
		
		String id= request.getParameter("m_id");
		FollowDAO fdao= new FollowDAO();
		List followfavoritelist=fdao.followreview(id);
		
		request.setAttribute("followfavoritelist", followfavoritelist);
		

		ActionForward forward = new ActionForward();
	    forward.setPath("./follow/followreviewlist.jsp");
	    forward.setRedirect(false);

		return forward;
	}

}
