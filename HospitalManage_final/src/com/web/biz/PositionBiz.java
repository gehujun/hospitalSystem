package com.web.biz;

import java.util.List;

import java.util.Map;
import com.web.entity.TPosition;

public interface PositionBiz {

	/**
	 * Map<String,Object> map
	 * map.put("poName","医生");
	 * @return
	 */
	public List<TPosition>  queryByCondition(Map<String, Object> map);
}
