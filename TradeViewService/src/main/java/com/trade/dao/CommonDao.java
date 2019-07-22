package com.trade.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.trade.vo.AccountVo;
import com.trade.vo.ManagementVo;
import com.trade.vo.TradeVo;
 
@Service
public class CommonDao {
	public List<?> getResource(String FileNm, String clsNm) throws FileNotFoundException, IOException {
		ClassPathResource resource = new ClassPathResource("data"+"/"+FileNm);
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
		List<?> rslt = br.lines().skip(1).map(line -> {
	        String[] lines = line.split(",");
	        Object obj = null;
	        if(clsNm.equals("TradeVo")) {
	        	obj = new TradeVo(lines[0], lines[1], lines[2], Integer.parseInt(lines[3]), Integer.parseInt(lines[4]), lines[5]);
	        }
	        else if(clsNm.equals("AccountVo")) {
	        	obj = new AccountVo(lines[0], lines[1], lines[2]);
	        }
	        else if(clsNm.equals("ManagementVo")) {
	        	obj = new ManagementVo(lines[0], lines[1]);
	        }
	        return obj;
	    }).collect(Collectors.toList());
		return rslt;
	}
}
 
