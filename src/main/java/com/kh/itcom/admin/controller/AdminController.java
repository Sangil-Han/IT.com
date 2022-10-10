package com.kh.itcom.admin.controller;

import java.util.Arrays;
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
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.User;

@Controller
public class AdminController {

	@Autowired
	private AdminService aService;

	/**
	 * 관리자 페이지
	 * 
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
				PageInfo upi = new PageInfo();
				upi.setCurrentPage((page != null) ? page : 1);
				upi.setRowCount(aService.printTotalUserCount());
				upi.setRowLimit(10);
				upi.setPageLimit(5);
				upi.setPageCount((int) ((double) upi.getRowCount() / upi.getRowLimit() + 0.9));
				upi.setStartPage(
						((int) ((double) upi.getCurrentPage() / upi.getPageLimit() + 0.9) - 1) * upi.getPageLimit()
								+ 1);
				upi.setEndPage(upi.getStartPage() + upi.getPageLimit() - 1);
				if (upi.getPageCount() < upi.getEndPage()) {
					upi.setEndPage(upi.getPageCount());
				}
				List<User> uList = aService.printUserList(upi);
				if (!uList.isEmpty()) {
					mv.addObject("uList", uList);
					mv.addObject("url", "adminPageView");
					mv.addObject("tabContent", tabContent);
					mv.addObject("upi", upi);
				}
				// 등업 관리
				PageInfo lupi = new PageInfo();
				lupi.setCurrentPage((page != null) ? page : 1);
				lupi.setRowCount(aService.printTotalLevelUpCount()); // TODO: printTotalLevelUpCount
				lupi.setRowLimit(10);
				lupi.setPageLimit(5);
				lupi.setPageCount((int) ((double) lupi.getRowCount() / lupi.getRowLimit() + 0.9));
				lupi.setStartPage(
						((int) ((double) lupi.getCurrentPage() / lupi.getPageLimit() + 0.9) - 1) * lupi.getPageLimit()
								+ 1);
				lupi.setEndPage(lupi.getStartPage() + lupi.getPageLimit() - 1);
				if (lupi.getPageCount() < lupi.getEndPage()) {
					lupi.setEndPage(lupi.getPageCount());
				}
				List<LevelUp> luList = aService.printLevelUpList(lupi); // TODO: printLevelUpList
				if (!luList.isEmpty()) {
					mv.addObject("luList", luList);
					mv.addObject("url", "adminPageView");
					mv.addObject("tabContent", tabContent);
					mv.addObject("lupi", lupi);
				}
				mv.setViewName("admin/adminPage");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.toString()); // error check
		}
		return mv;
	}

	/**
	 * 회원 삭제
	 * 
	 * @param request
	 * @param idList
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/admin/userDelete.do", method = RequestMethod.POST)
	public ModelAndView userSuspension(HttpServletRequest request, @RequestParam("checked") String checked,
			ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("loginAdmin");
			if (admin != null) {
				List<String> checkedUsers = Arrays.asList(checked.split(","));
				int result = aService.removeUsers(checkedUsers);
				if (result > 0) {
					mv.addObject("content", "user");
					mv.setViewName("redirect:/admin/adminPageView.do");
				}
				mv.setViewName("redirect:/admin/adminPageView.do");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.toString()); // error check
		}
		return mv;
	}

	// 등업 신청 승인
	@RequestMapping(value = "/admin/levelUpApprove.do", method = RequestMethod.POST)
	public ModelAndView levelUpApprove(HttpServletRequest request, @RequestParam("checked") String checked,
			ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("loginAdmin");
			if (admin != null) {
				System.out.println(0);
				List<String> checkedUsers = Arrays.asList(checked.split(","));
				System.out.println(checkedUsers.toString());
				int result = aService.approveLevelUp(checkedUsers);
				System.out.println("result:" + result);
				System.out.println(1);
				mv.addObject("content", "level");
				mv.setViewName("redirect:/admin/adminPageView.do");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.toString()); // error check
		}
		return mv;
	}

	// 등업 신청 거절
	@RequestMapping(value = "/admin/levelUpDeny.do", method = RequestMethod.POST)
	public ModelAndView levelUpDeny(HttpServletRequest request, @RequestParam("checked") String checked,
			ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("loginAdmin");
			if (admin != null) {
				List<String> checkedUsers = Arrays.asList(checked.split(","));
				int result = aService.denyLevelUp(checkedUsers);
				mv.addObject("content", "level");
				mv.setViewName("redirect:/admin/adminPageView.do");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.toString()); // error check
		}
		return mv;
	}

}
