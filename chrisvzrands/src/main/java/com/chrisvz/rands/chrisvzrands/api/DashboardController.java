package com.chrisvz.rands.chrisvzrands.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chrisvz.rands.chrisvzrands.services.DashboardService;
import com.chrisvz.rands.chrisvzrands.vo.CustomeResponse;
import com.chrisvz.rands.chrisvzrands.vo.Message;
import com.chrisvz.rands.chrisvzrands.vo.UserAdd;
import com.chrisvz.rands.chrisvzrands.vo.UserID;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
public class DashboardController {

	@Autowired
	DashboardService dashboardService;
	
//	@GetMapping("/getUserList")
	@RequestMapping(value = "/getUserList")
	public @ResponseBody CustomeResponse getme(@RequestParam(value = "id", required = false) Integer id) {

		System.out.println("in get message--->"+id);
		
		List l=dashboardService.getUserList(id);

		System.out.println("in list l--->"+l);
			
		CustomeResponse  vo =new CustomeResponse ();
		vo.setCode(200);
		vo.setData(l);
		vo.setMessage("success");
		return vo;
	}
	
	@PostMapping("/addToUserList")
	public @ResponseBody CustomeResponse addToUserList(@RequestBody UserAdd  ua) {
		
		
		System.out.println("in message"+ua.toString());
		int i=dashboardService.addToUserList(ua.getUserID(),ua.getListID(),ua.getName());
		List l=dashboardService.getUserList(1);

		CustomeResponse  vo =new CustomeResponse ();
		vo.setCode(200);
		vo.setData(l);
		vo.setMessage("success");
		return vo;
	}


	@GetMapping("/getUserMessages")
	public @ResponseBody CustomeResponse getMeassages(@RequestParam int from ,@RequestParam int to ) {
		
		
		System.out.println("in get message--->"+from+"---"+to);

		List l=dashboardService.getUserMeassageList(from,to);
		//List l=dashboardService.getUserMeassageList(a,b);

		
		CustomeResponse  vo =new CustomeResponse ();
		vo.setCode(200);
		vo.setData(l);
		vo.setMessage("success");
		return vo;
	}
	@PostMapping("/SendMsg")
	public @ResponseBody CustomeResponse SendMsg(@RequestBody Message m ) {
		
		System.out.println("in send--->"+m);
		
		int i=dashboardService.sendMsg(m);
		List l=dashboardService.getUserMeassageList(m.getTo(),m.getFrom());
		
		System.out.println("in message");
		CustomeResponse  vo =new CustomeResponse ();
		vo.setCode(200);
		vo.setData(l);
		vo.setMessage("success");
		return vo;
	}

}
