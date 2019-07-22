package com.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trade.dao.CommonDao;
 
@Service
public class CommonService {
    
	
	public List<?> getResource(String fileNm, String rsltType) throws Exception{
		CommonDao cmmDao = new CommonDao();
        return cmmDao.getResource(fileNm, rsltType);
    }
}
 
