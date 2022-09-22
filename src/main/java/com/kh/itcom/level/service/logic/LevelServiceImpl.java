package com.kh.itcom.level.service.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.level.domain.LevelUp;
import com.kh.itcom.level.service.LevelService;
import com.kh.itcom.level.store.LevelStore;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private LevelStore lStore;

	// 등업 신청
	@Override
	public int applyLevelUp(LevelUp lvUp) {
		int result = lStore.insertLevelUp(session, lvUp);
		return result;
	}

}
