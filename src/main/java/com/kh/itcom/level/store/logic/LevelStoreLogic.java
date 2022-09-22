package com.kh.itcom.level.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.itcom.level.domain.LevelUp;
import com.kh.itcom.level.store.LevelStore;

@Repository
public class LevelStoreLogic implements LevelStore {

	@Override
	public int insertLevelUp(SqlSession session, LevelUp lvUp) {
		int result = session.insert("LevelMapper.insertLevelUp", lvUp);
		return result;
	}

}
