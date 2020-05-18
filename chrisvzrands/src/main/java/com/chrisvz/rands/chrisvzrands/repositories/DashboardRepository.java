package com.chrisvz.rands.chrisvzrands.repositories;

import java.util.List;

import com.chrisvz.rands.chrisvzrands.vo.Message;

public interface DashboardRepository {
	List getUserList(int i);

	List getUserMeassageList(int from, int to);

	int sendMsg(Message m);

	int sendMsg(int userID, int listID, String name);

}
