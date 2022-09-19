package com.kh.itcom.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.lecture.domain.LectureBoard;
import com.kh.itcom.lecture.service.LectureBoardService;

@Controller
public class LectureBoardController {
	@Autowired
	private LectureBoardService lbService;
	
	// 게시글 등록 화면
	@RequestMapping(value="/lectureBoard/writeView.do", method=RequestMethod.GET)
	public String showlectureWrite() {
		return "lectureBoard/lectureWriteForm";
	}
	
	// 게시글 등록
	@RequestMapping(value="/lectureBoard/register.do", method=RequestMethod.POST)
	public ModelAndView registLectureBoard(
			ModelAndView mv
			, @ModelAttribute LectureBoard lectureboard) {
		try {
			int result = lbService.registerLecture(lectureboard);
			mv.setViewName("redirect:/lectureBoard/list.do");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 전체 조회
	@RequestMapping(value="/lectureBoard/list.do", method=RequestMethod.GET)
	public ModelAndView lectureBoardListView(ModelAndView mv) {
		List<LectureBoard> lbList = lbService.printAllLectureBoard();
		if(!lbList.isEmpty()) {
			mv.addObject("lbList", lbList);
		}
		mv.setViewName("lectureBoard/lecturelistView");
		return mv;
	}
}
