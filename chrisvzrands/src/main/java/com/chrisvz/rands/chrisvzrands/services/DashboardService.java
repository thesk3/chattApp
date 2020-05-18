package com.chrisvz.rands.chrisvzrands.services;

import java.util.List;

import com.chrisvz.rands.chrisvzrands.vo.Message;

public interface DashboardService {

	List getUserList(int i);

	List getUserMeassageList(int from, int to);

	int sendMsg(Message m);

	int addToUserList(int userID, int listID, String name);

}
