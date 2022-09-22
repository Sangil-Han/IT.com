package com.kh.itcom.level.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

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

import com.kh.itcom.level.domain.LevelUp;
import com.kh.itcom.level.service.LevelService;
import com.kh.itcom.user.domain.User;

@Controller
public class LevelController {

	@Autowired
	private LevelService lService;

	@RequestMapping(value = "/level/upView.do", method = RequestMethod.GET)
	public String levelUpView(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/user/loginView.do";
		}
		return "user/levelUp";
	}

	@RequestMapping(value = "/level/up.do", method = RequestMethod.POST)
	public ModelAndView levelUpApplication(HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile, @ModelAttribute LevelUp lvUp,
			ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginUser");
			if (user != null) {
				String certFileName = uploadFile.getOriginalFilename();
				if (!certFileName.equals("")) {
					String rootPath = request.getSession().getServletContext().getRealPath("resources");
					String savePath = rootPath + "/files";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String certFileRename = sdf.format(new Date(System.currentTimeMillis()))
							+ certFileName.substring(certFileName.lastIndexOf("."));
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
				lvUp.setUserId(user.getUserId());
				int result = lService.applyLevelUp(lvUp);
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
}
