package net.vip.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vip.db.VipBean;
import net.vip.db.VipDAO;

public class VipInsert implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("VipInsert execute");
		request.setCharacterEncoding("utf-8");
		
		
		//vip 영화 넣기
		VipBean vipbean = new VipBean();
		
		vipbean.setV_date(request.getParameter("v_date"));
		vipbean.setV_when(request.getParameter("v_when"));
		vipbean.setV_kor_title(request.getParameter("v_kor_title"));
		vipbean.setV_eng_title(request.getParameter("v_eng_title"));
		vipbean.setV_year(Integer.parseInt(request.getParameter("v_year")));
		vipbean.setV_country(request.getParameter("v_country"));
		vipbean.setV_age(Integer.parseInt(request.getParameter("v_age")));
		vipbean.setV_genre(request.getParameter("v_genre"));
		vipbean.setV_time(Integer.parseInt(request.getParameter("v_time")));
		vipbean.setV_director(request.getParameter("v_director"));
		vipbean.setV_actor(request.getParameter("v_actor"));
		vipbean.setV_story(request.getParameter("v_story"));
		vipbean.setV_video(request.getParameter("v_video"));
		vipbean.setV_critic_1_by(request.getParameter("v_critic_1_by"));
		vipbean.setV_critic_1(request.getParameter("v_critic_1"));
		vipbean.setV_critic_2_by(request.getParameter("v_critic_2_by"));
		vipbean.setV_critic_2(request.getParameter("v_critic_2"));
		
		VipDAO vipdao=new VipDAO();
		vipdao.insertVip(vipbean);

		//시사회 좌석 테이블 리셋
		vipdao.resetVipSeat();

		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("VipMovieList.vi");
		return forward;
	}

}
