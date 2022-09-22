package com.kh.itcom.level.store;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.level.domain.LevelUp;

public interface LevelStore {

	// 등업 신청
	int insertLevelUp(SqlSession session, LevelUp lvUp);

}
