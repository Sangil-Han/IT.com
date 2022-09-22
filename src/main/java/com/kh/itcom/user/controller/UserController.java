package com.kh.itcom.user.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.admin.service.AdminService;
import com.kh.itcom.point.domain.PointHistory;
import com.kh.itcom.user.domain.User;
import com.kh.itcom.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService uService;
	@Autowired
	private AdminService aService;

	/**
	 * 회원가입 화면
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/joinView.do", method = RequestMethod.GET)
	public String userJoinView() {
		return "user/join"; // prefix: WEB-INF/views/, suffix: .jsp
	}

	/**
	 * 회원가입
	 * 
	 * @param user
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/join.do", method = RequestMethod.POST)
	public ModelAndView userJoin(@ModelAttribute User user, ModelAndView mv) {
		try {
			int check = aService.checkId(user.getUserId());
			if (check > 0) {
				// 사용할 수 없는 아이디입니다.
			} else {
				int result = uService.joinUser(user);
				if (result > 0) {
					mv.setViewName("redirect:/home.do");
				} else {
					// 회원가입 실패
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); // error check
		}
		return mv;
	}

	/**
	 * 로그인 화면
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/loginView.do", method = RequestMethod.GET)
	public String userLoginView() {
		return "user/login";
	}

	/**
	 * 로그인
	 * 
	 * @param user
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
	public ModelAndView userLogin(@RequestParam("loginId") String loginId, @RequestParam("loginPw") String loginPw,
			HttpServletRequest request, ModelAndView mv) {
		try {
			int check = aService.checkId(loginId);
			if (check > 0) {
				Admin loginAdmin = aService.loginAdmin(new Admin(loginId, loginPw));
				if (loginAdmin != null) {
					HttpSession session = request.getSession();
					session.setAttribute("loginAdmin", loginAdmin);
					mv.setViewName("redirect:/home.do");
				}
			} else {
				User loginUser = uService.loginUser(new User(loginId, loginPw));
				if (loginUser != null) {
					HttpSession session = request.getSession();
					session.setAttribute("loginUser", loginUser);
					mv.setViewName("redirect:/home.do");
				} else {
					// 로그인 실패
					System.out.println("회원 로그인 실패");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); // error check
		}
		return mv;
	}

	/**
	 * 로그아웃
	 * 
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/logout.do", method = RequestMethod.GET)
	public ModelAndView userLogout(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
			mv.setViewName("redirect:/home.do");
		}
		return mv;
	}

	/**
	 * 마이페이지
	 * 
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/myPageView.do", method = RequestMethod.GET)
	public ModelAndView myPageView(HttpServletRequest request, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginUser");
			if (user != null) {
				mv.setViewName("user/myPage");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); // error check
		}
		return mv;
	}

	/**
	 * 회원 탈퇴
	 * 
	 * @param session
	 * @param userPw
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/withdraw.do", method = RequestMethod.POST)
	public ModelAndView userWithdraw(HttpSession session, @RequestParam("userPw") String userPw, ModelAndView mv) {
		try {
			User user = (User) session.getAttribute("loginUser");
			if (user != null) {
				String userId = user.getUserId();
				HashMap<String, String> userInfo = new HashMap<String, String>() {
					{
						put("userId", userId);
						put("userPw", userPw);
					}
				};
				int result = uService.removeUser(userInfo);
				if (result > 0) {
					mv.setViewName("redirect:/user/logout.do");
				}
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.toString()); // error check
		}
		return mv;
	}
}
