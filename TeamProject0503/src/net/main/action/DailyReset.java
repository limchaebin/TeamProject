package net.main.action;

import java.util.TimerTask;

import net.member.db.MemberDAO;
import net.roulette.db.rouletteDAO;

public class DailyReset extends TimerTask{

	@Override
	public void run() {
		rouletteDAO rdao = new rouletteDAO();
		rdao.rouletteReset();
		MemberDAO mdao = new MemberDAO();
		mdao.m_rouletteReset();
	}
	
}
