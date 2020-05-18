package com.chrisvz.rands.chrisvzrands.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrisvz.rands.chrisvzrands.repositories.DashboardRepository;
import com.chrisvz.rands.chrisvzrands.vo.Message;

@Service
public class DashboardServiceImpl implements DashboardService {

	 @Autowired
	    private DashboardRepository dashboardRepository;

	
	@Override
	public List getUserList(int i) {
		// TODO Auto-generated method stub
		return dashboardRepository.getUserList(i) ;
	}


	@Override
	public List getUserMeassageList(int from,int to) {
		// TODO Auto-generated method stub
		return dashboardRepository.getUserMeassageList(from,to);
	}


	@Override
	public int sendMsg(Message m) {
		// TODO Auto-generated method stub
		return dashboardRepository.sendMsg(m);
	}


	@Override
	public int addToUserList(int userID, int listID,String name) {
		// TODO Auto-generated method stub
		return dashboardRepository.sendMsg(userID, listID,name);
	}

}
