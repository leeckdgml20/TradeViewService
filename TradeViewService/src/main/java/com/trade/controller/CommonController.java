package com.trade.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.trade.service.CommonService;
import com.trade.vo.AccountVo;
import com.trade.vo.ManagementVo;
import com.trade.vo.TradeVo;
 
@RestController
public class CommonController {
    
	@Autowired
    CommonService cmmnService;

	public CommonController() {
		
	}
    @RequestMapping("/main")
    public @ResponseBody ModelAndView root_test() throws Exception{
    	ModelAndView mav = new ModelAndView("main");
    	return mav;
    }
 
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/no1", method = RequestMethod.GET)
    public @ResponseBody ModelAndView calcSumAmtByYear() throws Exception{
    	List<TradeVo> trades = (List<TradeVo>) cmmnService.getResource("데이터_거래내역.csv","TradeVo");
    	List<AccountVo> accounts = (List<AccountVo>) cmmnService.getResource("데이터_계좌정보.csv","AccountVo");
    	
    	Map<String, Map<String, Integer>>sumAmtByYearAndAcctNo = trades.stream().filter(trade -> trade.getCnclYn().equals("Y"))
    			.collect(Collectors.groupingBy(TradeVo::getYear,Collectors.groupingBy(TradeVo::getAcctNo,Collectors.summingInt(TradeVo::calcAmtMinusFee))));
    	Map<String, String> maxSumAmtByYearAndAcctNo = new HashMap<String, String>();
    	sumAmtByYearAndAcctNo.forEach((x,y)-> maxSumAmtByYearAndAcctNo.put(x, y.entrySet().stream().max((w,v) -> w.getValue() > v.getValue() ? 1:  -1).get().toString()));
    	
    	JSONArray jsonArray = new JSONArray();
    	maxSumAmtByYearAndAcctNo.forEach((x,y)-> {
    			JSONObject jsonObject = new JSONObject();
    			jsonObject.put("year", x);
    			if(y != null) {
    				String[] split = y.split("=");
        			if(split.length ==2) {
        				String acctNm = accounts.stream().filter(account -> account.getAcctNo().equals(split[0])).map(AccountVo::getAcctNm).findFirst().get();
        				jsonObject.put("name", acctNm);
        				jsonObject.put("acctNo", split[0]);
        				jsonObject.put("sumAmt", split[1]);
        			}
    			}
    			jsonArray.add(jsonObject);
    		});
    	ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("jsonText", jsonArray);
        
        return mav;
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/no2", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getInactiveTradeByYear() throws Exception{
		List<TradeVo> trades = (List<TradeVo>) cmmnService.getResource("데이터_거래내역.csv","TradeVo");
    	List<AccountVo> accounts = (List<AccountVo>) cmmnService.getResource("데이터_계좌정보.csv","AccountVo");
    	
    	Map<String, List<String>> activeListByYear = trades.stream().filter(trade -> trade.getCnclYn().equals("Y")).collect(Collectors.groupingBy(TradeVo::getYear,Collectors.mapping(TradeVo::getAcctNo, Collectors.toList())));
    	
    	JSONArray jsonArray = new JSONArray();
    	activeListByYear.forEach((x,y)->  {
    		List<String> activeAcctNo= y.stream().distinct().collect(Collectors.toList());
    		List<AccountVo> inactiveAccounts = accounts.stream().collect(Collectors.toList());
    		inactiveAccounts.removeIf(z->activeAcctNo.contains(z.getAcctNo()));
    		inactiveAccounts.forEach(z->{
    			JSONObject jsonObject = new JSONObject();
    			jsonObject.put("year", x);
    			jsonObject.put("name", z.getAcctNm());
        		jsonObject.put("acctNo", z.getAcctNo());
        		jsonArray.add(jsonObject);
    		});
    	});
    	ModelAndView mav = new ModelAndView("jsonView");
    	mav.addObject("jsonText", jsonArray);
        return mav;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/no3", method = RequestMethod.GET)
    public @ResponseBody ModelAndView calcSumAmtByYearAndMng() throws Exception{
		List<TradeVo> trades = (List<TradeVo>) cmmnService.getResource("데이터_거래내역.csv","TradeVo");
		List<AccountVo> accounts = (List<AccountVo>) cmmnService.getResource("데이터_계좌정보.csv","AccountVo");
		List<ManagementVo> managements = (List<ManagementVo>) cmmnService.getResource("데이터_관리점정보.csv","ManagementVo");
    	
		trades.forEach(x-> {
			String acctNo = x.getAcctNo();
			x.setAcctNo(accounts.stream().filter(y-> acctNo.equals(y.getAcctNo())).findFirst().get().getMngCd());
		});
		Map<String, Map<String, Integer>>sumAmtByYearAndMngCd = trades.stream().filter(trade -> trade.getCnclYn().equals("Y"))
    			.collect(Collectors.groupingBy(TradeVo::getYear,Collectors.groupingBy(TradeVo::getAcctNo,Collectors.summingInt(TradeVo::getAmt))));
		
		JSONArray jsonArray = new JSONArray();
		sumAmtByYearAndMngCd.forEach((x,y)->{
			List<String> list = new ArrayList();
	        list.addAll(y.keySet());
	        Collections.sort(list,new Comparator(){
	            public int compare(Object o1,Object o2){
	                return ((Comparable) y.get(o1)).compareTo(y.get(o2));
	            }
	        });
	        Collections.reverse(list);
	        Iterator it = list.iterator();
	        
	        JSONObject jsonObject = new JSONObject();
			jsonObject.put("year", x);
			JSONArray subArray = new JSONArray();
	        while(it.hasNext()) {
	            String brCode = (String) it.next();
	            int sumAmt = y.get(brCode);
	            
	            JSONObject subObject = new JSONObject();
				subObject.put("sumAmt", sumAmt);
				subObject.put("brCode", brCode);
				subObject.put("brName", managements.stream().filter(t->brCode.equals(t.getMngCd())).findFirst().get().getMngNm());
				subArray.add(subObject);
	        }
	        jsonObject.put("dataList", subArray);
	        jsonArray.add(jsonObject);
		});
    	ModelAndView mav = new ModelAndView("jsonView");
    	mav.addObject("jsonText", jsonArray);
        return mav;
    }
    
    
    @SuppressWarnings({ "unchecked" })
    @RequestMapping(value = "/no4", method = RequestMethod.GET)
    public @ResponseBody ModelAndView calcSumAmtByMng(@RequestParam(value = "brName") String mngNm) throws Exception{
		List<TradeVo> trades = (List<TradeVo>) cmmnService.getResource("데이터_거래내역.csv","TradeVo");
		List<AccountVo> accounts = (List<AccountVo>) cmmnService.getResource("데이터_계좌정보.csv","AccountVo");
		List<ManagementVo> managements = (List<ManagementVo>) cmmnService.getResource("데이터_관리점정보.csv","ManagementVo");
		managements.removeIf(x->x.getMngCd().equals("B"));
		
		Optional<ManagementVo> optionalVo= managements.stream().filter(x->x.getMngNm().equals(mngNm)).findFirst();
		ModelAndView mav = new ModelAndView("jsonView");
		if(!optionalVo.isPresent()) {
			JSONObject jsonObject = new JSONObject();
	        jsonObject.put("code", "404");
	        jsonObject.put("메세지", "br code not found error");
	        mav.addObject("jsonText", jsonObject);
			mav.setStatus(HttpStatus.NOT_FOUND);
		}
		else {
			String mngCd = optionalVo.get().getMngCd();
			trades.forEach(x-> {
				String cmpAcctNo = x.getAcctNo();
				String replaceMngCd = accounts.stream().filter(y-> cmpAcctNo.equals(y.getAcctNo())).findFirst().get().getMngCd();
				replaceMngCd = replaceMngCd.equals("B") ? "A" : replaceMngCd;
				x.setAcctNo(replaceMngCd);
			});
			Integer sumAmt = trades.stream().filter(trade -> trade.getCnclYn().equals("Y") && trade.getAcctNo().equals(mngCd)).collect(Collectors.summingInt(TradeVo::getAmt));
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("sumAmt", sumAmt);
	        jsonObject.put("brCode", mngCd);
	        jsonObject.put("brName", mngNm);
	        mav.addObject("jsonText", jsonObject);
		}
        return mav;
    }
 
}
 
