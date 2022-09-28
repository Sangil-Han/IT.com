package com.kh.itcom.user.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.admin.service.AdminService;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.MyPost;
import com.kh.itcom.user.domain.PointHistory;
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
	@RequestMapping(value = "/user/joinView.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView userJoinView(@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "chkMsg", required = false) String chkMsg,
			@RequestParam(value = "chkNum", required = false) Integer chkNum, ModelAndView mv) {
		if (userId != null) {
			mv.addObject("userId", userId);
		}
		if (chkNum != null) {
			mv.addObject("chkNum", chkNum);
		}
		if (chkMsg != null) {
			mv.addObject("chkMsg", chkMsg);
		}
		mv.setViewName("user/join"); // prefix: WEB-INF/views/, suffix: .jsp
		return mv;
	}

	/**
	 * 아이디 확인
	 * 
	 * @param userId
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/idCheck.do", method = RequestMethod.POST)
	public ModelAndView userIdCheck(@RequestParam("userId") String userId, ModelAndView mv) {
		Integer chkNum = aService.checkAdminId(userId);
		mv.addObject("userId", userId);
		if (chkNum > 0) {
			mv.addObject("chkMsg", "사용할 수 없는 아이디입니다.");
		} else {
			chkNum = uService.checkUserId(userId);
			if (chkNum > 0) {
				mv.addObject("chkMsg", "사용할 수 없는 아이디입니다.");
			} else {
				mv.addObject("chkNum", chkNum); // 0
				mv.addObject("chkMsg", "사용 가능한 아이디입니다.");
			}
		}
		mv.setViewName("redirect:/user/joinView.do");
		return mv;
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
			int check = aService.checkAdminId(user.getUserId());
			if (check > 0) {
				System.out.println("관리자 아이디");
			} else {
				check = uService.checkUserId(user.getUserId());
				if (check > 0) {
					System.out.println("중복 아이디");
				} else {
					int result = uService.joinUser(user);
					if (result > 0) {
						mv.setViewName("redirect:/home.do");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); // error check
			System.out.println(e.toString());
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
			int check = aService.checkAdminId(loginId);
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
			System.out.println(e.toString());
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
	public ModelAndView myPageView(HttpServletRequest request,
			@RequestParam(value = "page", required = false) Integer page, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if (loginUser != null) {
				PageInfo mppi = new PageInfo();
				mppi.setCurrentPage((page != null) ? page : 1);
				mppi.setRowCount(uService.printTotalMyPostCount(loginUser.getUserId())); // TODO:
				mppi.setRowLimit(5);
				mppi.setPageLimit(5);
				mppi.setPageCount((int) ((double) mppi.getRowCount() / mppi.getRowLimit() + 0.9));
				mppi.setStartPage(
						((int) ((double) mppi.getCurrentPage() / mppi.getPageLimit() + 0.9) - 1) * mppi.getPageLimit()
								+ 1);
				mppi.setEndPage(mppi.getStartPage() + mppi.getPageLimit() - 1);
				if (mppi.getPageCount() < mppi.getEndPage()) {
					mppi.setEndPage(mppi.getPageCount());
				}
				List<MyPost> myPostList = uService.printMyPostList(loginUser.getUserId(), mppi); // TODO: mppi param으로
				mv.addObject("mpList", myPostList);
				mv.addObject("mppi", mppi);
				mv.setViewName("user/myPage");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()); // error check
			System.out.println(e.toString());
		}
		return mv;
	}

	/**
	 * 등급 변동 내역
	 * 
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/levelHistoryView.do", method = RequestMethod.GET)
	public ModelAndView levelHitoryView(HttpServletRequest request, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if (loginUser != null) {
				List<LevelUp> lhList = uService.printLevelHistory(loginUser.getUserId());
				mv.addObject("lhList", lhList);
				mv.setViewName("user/levelHistory");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		return mv;
	}

	/**
	 * 포인트 내역
	 * 
	 * @param request
	 * @param page
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/pointHistoryView.do", method = RequestMethod.GET)
	public ModelAndView pointHistoryView(HttpServletRequest request,
			@RequestParam(value = "page", required = false) Integer page, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if (loginUser != null) {
				PageInfo phpi = new PageInfo();
				phpi.setCurrentPage((page != null) ? page : 1);
				phpi.setRowCount(uService.printTotalPointCount(loginUser.getUserId()));
				phpi.setRowLimit(10);
				phpi.setPageLimit(5);
				phpi.setPageCount((int) ((double) phpi.getRowCount() / phpi.getRowLimit() + 0.9));
				phpi.setStartPage(
						((int) ((double) phpi.getCurrentPage() / phpi.getPageLimit() + 0.9) - 1) * phpi.getPageLimit()
								+ 1);
				phpi.setEndPage(phpi.getStartPage() + phpi.getPageLimit() - 1);
				if (phpi.getPageCount() < phpi.getEndPage()) {
					phpi.setEndPage(phpi.getPageCount());
				}
				List<PointHistory> phList = uService.printPointHistory(loginUser.getUserId(), phpi);
				System.out.println(phList.toString());
				if (!phList.isEmpty()) {
					mv.addObject("phList", phList);
					mv.addObject("phpi", phpi);
				}
				mv.setViewName("user/pointHistory");
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		return mv;
	}

	/**
	 * 등업 신청 화면
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/levelUpView.do", method = RequestMethod.GET)
	public String levelUpView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/user/loginView.do";
		}
		return "user/levelUp";
	}

	/**
	 * 등업 신청
	 * 
	 * @param request
	 * @param uploadFile
	 * @param lvUp
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/levelUp.do", method = RequestMethod.POST)
	public ModelAndView levelUpApplication(HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile, @ModelAttribute LevelUp lvUp,
			ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if (loginUser != null) {
				String certFileName = uploadFile.getOriginalFilename();
				if (!certFileName.equals("")) {
					String root = request.getSession().getServletContext().getRealPath("resources");
					String savePath = root + "/files/levelup";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String certFileRename = sdf.format(new Date(System.currentTimeMillis())) + "."
							+ certFileName.substring(certFileName.lastIndexOf(".") + 1);
					File folder = new File(savePath);
					if (!folder.exists()) {
						folder.mkdir();
					}
					String certFilePath = savePath + "/" + certFileRename;
					uploadFile.transferTo(new File(certFilePath));
					lvUp.setCertFileName(certFileName);
					lvUp.setCertFileRename(certFileRename);
					lvUp.setCertFilePath(certFilePath);
				}
				int result = uService.applyLevelUp(lvUp);
				if (result > 0) {
					mv.setViewName("redirect:/user/myPageView.do");
				}
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		return mv;
	}

	/**
	 * 비밀번호 재설정 화면
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/pwResetView.do", method = RequestMethod.GET)
	public String userPwResetView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/user/loginView.do";
		} else {
			return "user/pwReset";
		}
	}

	/**
	 * 비밀번호 재설정
	 * 
	 * @param request
	 * @param userPw
	 * @param newPw
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/user/pwReset.do", method = RequestMethod.POST)
	public ModelAndView userPwReset(HttpServletRequest request, @RequestParam("userPw") String userPw,
			@RequestParam("newPw") String newPw, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			if (loginUser != null) {
				int check = uService.checkPw(new User(loginUser.getUserId(), userPw));
				if (check > 0) {
					int result = uService.resetPw(new User(loginUser.getUserId(), newPw));
					if (result > 0) {
						mv.setViewName("redirect:/user/myPageView.do");
					} else {
						System.out.println("비밀번호 재설정 실패");
					}
				} else {
					System.out.println("현재 비밀번호 틀림");
				}
			} else {
				mv.setViewName("redirect:/user/loginView.do");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
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
			User loginUser = (User) session.getAttribute("loginUser");
			if (loginUser != null) {
				String userId = loginUser.getUserId();
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
			System.out.println(e.getMessage());
			System.out.println(e.toString()); // error check
		}
		return mv;
	}
}
