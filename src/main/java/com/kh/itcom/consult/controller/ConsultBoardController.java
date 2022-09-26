package com.kh.itcom.consult.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.kh.itcom.consult.domain.ConsultBoard;
import com.kh.itcom.consult.domain.ConsultBoardComment;
import com.kh.itcom.consult.domain.ConsultDownCount;
import com.kh.itcom.consult.domain.ConsultUpCount;
import com.kh.itcom.consult.domain.ConsultViewCount;
import com.kh.itcom.consult.service.ConsultBoardService;
import com.kh.itcom.user.domain.User;

@Controller
public class ConsultBoardController {
	@Autowired
	private ConsultBoardService cService;
	
	@RequestMapping(value="/consult/consultWriteFormView.do", method=RequestMethod.GET)
	public String showBoardWriteForm() {
		return "consult/boardWriteForm";
	}
	
	@RequestMapping(value="/consult/consultList.do", method=RequestMethod.GET)
	public ModelAndView boardListView(
			ModelAndView mv
			, @ModelAttribute ConsultViewCount viewCount
			, @RequestParam(value="page", required=false) Integer page
			, HttpSession session) {
		int currentPage = (page != null) ? page : 1;
		int totalCount = cService.getTotalCount("","");
		int boardLimit = 10;
		int naviLimit = 5;
		int maxPage;
		int startNavi;
		int endNavi;
		maxPage = (int)((double)totalCount/boardLimit + 0.9);
		startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
		endNavi = startNavi + naviLimit - 1;
		if(maxPage < endNavi) {
			endNavi = maxPage;
		}
		List<ConsultBoard> cList = cService.printAllBoard(currentPage, boardLimit);
		try {
			Admin loginAdmin = (Admin)session.getAttribute("loginAdmin");
			User loginUser = (User)session.getAttribute("loginUser");
			String adminId = (loginAdmin!= null) ? loginAdmin.getAdminId() : "";
			String userId = (loginUser!= null) ? loginUser.getUserId() : "";
			mv.addObject("userId", userId);
			mv.addObject("adminId", adminId);
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		if(!cList.isEmpty()) {
			mv.addObject("urlVal", "consultList");
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("cList",cList);
			mv.setViewName("consult/consultListView");
		}
		return mv;
	}
	@RequestMapping(value="/consult/consultDetailView.do", method=RequestMethod.GET)
	public ModelAndView boardDetailView(
			ModelAndView mv
			, @ModelAttribute ConsultViewCount viewCount
			, @RequestParam("cBoardNo") Integer cBoardNo
			, @RequestParam("page") Integer page
			, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Admin loginAdmin = (Admin)session.getAttribute("loginAdmin");
			User loginUser = (User)session.getAttribute("loginUser");
			String loginAdminId = (loginAdmin != null) ? loginAdmin.getAdminId() : "";
			String loginUserId = (loginUser != null) ? loginUser.getUserId() : "";
			String userViewable = loginUser.getViewable();
			ConsultBoard cBoard = cService.printOneByNo(cBoardNo);
			viewCount.setUserId(loginUserId);
			if(userViewable.equals("N")) {
				int usePoint = cService.modifyPoint(loginUser);
			}
			List<ConsultBoardComment> cList = cService.printAllComment(cBoardNo);
			int viewCountCheck = cService.printViewCountCheck(viewCount);
			int totalViewCount = cService.printTotalViewCount(viewCount);
			if(viewCountCheck == 0 && loginAdminId.isEmpty()) {
				int registViewCount = cService.registBoardViewCount(viewCount);
				int updateViewCount = cService.updateBoardViewCount(cBoardNo);
			}
			int totalUp = cService.printTotalUpCount(cBoardNo);
			int totalDown = cService.printTotalDownCount(cBoardNo);
			session.setAttribute("cBoardNo", cBoardNo);
			mv.addObject("totalViewCount", totalViewCount);
			mv.addObject("totalUp", totalUp);
			mv.addObject("totalDown", totalDown);
			mv.addObject("loginAdminId", loginAdminId);
			mv.addObject("loginUserId", loginUserId);
			mv.addObject("cList", cList);
			mv.addObject("loginUser", loginUser);
			mv.addObject("cBoard", cBoard);
			mv.addObject("page", page);
			mv.setViewName("consult/detailView");
		   } catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/consult/consultSearch.do", method=RequestMethod.GET)
	public ModelAndView boardSerachList(
			ModelAndView mv
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = cService.getTotalCount(searchCondition, searchValue);
			int boardLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			maxPage = (int)((double)totalCount/boardLimit + 0.9);
			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
			endNavi = startNavi + naviLimit - 1;
			if(maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<ConsultBoard> cList = cService.printAllByValue(
					searchCondition, searchValue, currentPage, boardLimit);
			if(!cList.isEmpty()) {
				mv.addObject("cList", cList);
			}else {
				mv.addObject("cList", null);
			}
			mv.addObject("urlVal", "consultSearch");
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("searchValue", searchValue);
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.setViewName("consult/consultListView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping(value="/consult/consultRegister.do", method=RequestMethod.POST)
	public ModelAndView boardRegist( //일반회원 viewable N 이 상담후기 작성시 Y로 업데이트
			ModelAndView mv
			, @ModelAttribute ConsultBoard cBoard
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, HttpSession session) {
		try {
			User loginUser = (User)session.getAttribute("loginUser");
			String userViewable = loginUser.getViewable(); 
			if(userViewable.equals("N")) {
				cService.modifyViewable(loginUser);
			}
			String cBoardFileName = uploadFile.getOriginalFilename();
			if(!cBoardFileName.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\cBoardUploadFile";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String cBoardFileRename
				= sdf.format(new Date(System.currentTimeMillis()))+"."
						+cBoardFileName.substring(cBoardFileName.lastIndexOf(".")+1);
				File file = new File(savePath);
				if(!file.exists()) {
					file.mkdir();
				}
				uploadFile.transferTo(new File(savePath+"\\"+cBoardFileRename));
				// 파일을 buploadFile 경로에 저장하는 메소드
				String cBoardFilePath = savePath+"\\"+cBoardFileRename;
				cBoard.setcBoardFileName(cBoardFileName);
				cBoard.setcBoardFileRename(cBoardFileRename);
				cBoard.setcBoardFilePath(cBoardFilePath);
			}
			int cBoardNo = cBoard.getcBoardNo();
			int result = cService.registerBoard(cBoard);
			mv.setViewName("redirect:/consult/consultList.do");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/consult/consultModifyView.do", method=RequestMethod.GET)
	public ModelAndView boardModifyView(
			ModelAndView mv
			, @RequestParam("cBoardNo") Integer cBoardNo
			, @RequestParam("page") int page) {
		try {
			ConsultBoard cBoard = cService.printOneByNo(cBoardNo);
			mv.addObject("cBoard", cBoard);
			mv.addObject("page",page);
			mv.setViewName("consult/modifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	@RequestMapping(value="/consult/consultModify.do", method=RequestMethod.POST)
	public ModelAndView boardModify(
			ModelAndView mv
			, @ModelAttribute ConsultBoard cBoard
			, @RequestParam(value="reloadFile", required=false) MultipartFile reloadFile
			, @RequestParam("page") Integer page
			, @RequestParam("cBoardNo") Integer cBoardNo
			, HttpServletRequest request) {
		try {
			String cBoardFileName = reloadFile.getOriginalFilename();
			if(reloadFile != null && !cBoardFileName.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\cBoardUploadFile";
				File file = new File(savedPath + "\\"+cBoard.getcBoardFileRename());
				if(file.exists()) {
					file.delete();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
				String cBoardFileRename = sdf.format(new Date(System.currentTimeMillis()))
						+"." + cBoardFileName.substring(cBoardFileName.lastIndexOf(".")+1);
				String cBoardFilePath = savedPath + "\\" + cBoardFileRename;
				reloadFile.transferTo(new File(cBoardFilePath));
				cBoard.setcBoardFileName(cBoardFileName);
				cBoard.setcBoardFileRename(cBoardFileRename);
				cBoard.setcBoardFilePath(cBoardFilePath);
			}
			int result = cService.modifyBoard(cBoard);
			mv.setViewName("redirect:/consult/consultDetailView.do?cBoardNo="+cBoardNo+"&page="+page);
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/consult/consultRemove.do", method=RequestMethod.GET)
	public ModelAndView boardRemove(
			ModelAndView mv
			, @RequestParam("page") int page
			, @RequestParam("cBoardNo") int cBoardNo) {
		int result = cService.removeBoard(cBoardNo);
		mv.setViewName("redirect:/consult/consultList.do?&page="+page);
		return mv;
	}
	
	@RequestMapping(value="/consult/boardUpCount.do", method=RequestMethod.POST)
	public ModelAndView boardUpCount(
			ModelAndView mv
			, @ModelAttribute ConsultUpCount upCount
			, @ModelAttribute ConsultBoard consultBoard
			, @RequestParam("page") int page
			, HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		String userId = user.getUserId();
		upCount.setUserId(userId);
		int cBoardNo = upCount.getcBoardNo();
		int consultBoardNo = consultBoard.getcBoardNo();
		int upCountCheck = cService.upCountCheck(upCount);
		if(upCountCheck == 0) {
			int insertUpCount = cService.registerUpCount(upCount);
			int printBoardUp = cService.registerBoardUp(consultBoardNo);
		}else if(upCountCheck == 1) {
			int deleteUpCount = cService.removeUpCount(upCount);
		}
		mv.setViewName("redirect:/consult/consultDetailView.do?cBoardNo="+cBoardNo+"&page="+page);
		return mv;
	}

	@RequestMapping(value="/consult/boardDownCount.do", method=RequestMethod.POST)
	public ModelAndView boardDownCount(
			ModelAndView mv
			, @ModelAttribute ConsultDownCount downCount
			, @RequestParam("page") int page
			, HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		String userId = user.getUserId();
		downCount.setUserId(userId);
		int cBoardNo = downCount.getcBoardNo();
		int downCountCheck = cService.downCountCheck(downCount);
		if(downCountCheck == 0) {
			int insertUpCount = cService.registerDownCount(downCount);
		}else if(downCountCheck == 1) {
			int deleteUpCount = cService.removeDownCount(downCount);
		}
		mv.setViewName("redirect:/consult/consultDetailView.do?cBoardNo="+cBoardNo+"&page="+page);
		return mv;
	}

	@RequestMapping(value="/consult/consultAddComment.do", method=RequestMethod.POST)
	public ModelAndView addComment(
			ModelAndView mv
			, @ModelAttribute ConsultBoardComment comment
			, @RequestParam("page") int page
			, HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		String userId = user.getUserId();
		comment.setUserId(userId);
		int cBoardNo = comment.getcBoardNo();
		int result = cService.registerComment(comment);
		if(result > 0) {
			mv.setViewName("redirect:/consult/consultDetailView.do?cBoardNo="+cBoardNo+"&page="+page);
		}
		return mv;
	}
	@RequestMapping(value="/consult/removeComment.do", method=RequestMethod.GET)
	public ModelAndView removeComment(
			ModelAndView mv
			, @RequestParam("commentNo") Integer commentNo
			, @RequestParam("page") Integer page
			, @RequestParam("cBoardNo") int cBoardNo) {
		int result = cService.deleteComment(commentNo);
		if(result > 0) {
			mv.setViewName("redirect:/consult/consultDetailView.do?cBoardNo="+cBoardNo+"&page="+page);
		}
		return mv;
	}
	@RequestMapping(value="/consult/modifyComment.do", method=RequestMethod.POST)
	public ModelAndView modifyComment(
			@ModelAttribute ConsultBoardComment comment
			, ModelAndView mv
			, @RequestParam("page") int page
			, @RequestParam("cBoardNo") int cBoardNo) {
		int result = cService.modifyComment(comment);
		mv.setViewName("redirect:/consult/consultDetailView.do?cBoardNo="+cBoardNo+"&page="+page);
		return mv;
	}
}
