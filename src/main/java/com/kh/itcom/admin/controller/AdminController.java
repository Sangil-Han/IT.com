package com.kh.itcom.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.admin.service.AdminService;
import com.kh.itcom.user.domain.User;

@Controller
public class AdminController {

	@Autowired
	private AdminService aService;

	/**
	 * 관리자 페이지
	 * @param request
	 * @param page
	 * @param content
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/admin/adminPageView.do", method = RequestMethod.GET)
	public ModelAndView adminPageView(HttpServletRequest request,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "content", required = false) String content, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("loginAdmin");
			if (admin != null) {
				String tabContent = (content != null) ? content : null;
				// 교육원 조회
				// ...
				// 회원 관리
				int currentPage = (page != null) ? page : 1;
				int userCount = aService.printTotalUserCount();
				int userLimit = 10;
				int pageLimit = 5;
				int pageCount = (int) ((double) userCount / userLimit + 0.9);
				int startPage = ((int) ((double) currentPage / pageLimit + 0.9) - 1) * pageLimit + 1;
				int endPage = startPage + pageLimit - 1;
				if (pageCount < endPage) {
					endPage = pageCount;
				}
				HashMap<String, Integer> pageInfo = new HashMap<String, Integer>() {
					{
						put("currentPage", currentPage);
						put("userLimit", userLimit);
					}
				};
				List<User> uList = aService.printUserList(pageInfo);
				if (!uList.isEmpty()) {
					mv.addObject("tabContent", tabContent);
					mv.addObject("currentPage", currentPage);
					mv.addObject("startPage", startPage);
					mv.addObject("endPage", endPage);
					mv.addObject("pageCount", pageCount);
					mv.addObject("uList", uList);
					mv.addObject("url", "adminPageView");
				}
				// 등업 관리
				// ...
				mv.setViewName("admin/adminPage");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.toString()); // error check
		}
		return mv;
	}

}
