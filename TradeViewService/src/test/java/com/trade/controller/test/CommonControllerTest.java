package com.trade.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommonControllerTest {
    
    private static MediaType JSON_CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType());
    
	private MockMvc mockMvc;
	
    @Autowired
    private WebApplicationContext webApplicationContext;
 
    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void testNo1() throws Exception {
        mockMvc.perform(get("/no1"))
               .andDo(print())
               .andExpect(status().isOk());
    }
    
    @Test
    public void testNo2() throws Exception {
        mockMvc.perform(get("/no2"))
               .andDo(print())
               .andExpect(status().isOk());
    }
    
    @Test
    public void testNo3() throws Exception {
        mockMvc.perform(get("/no3"))
               .andDo(print())
               .andExpect(status().isOk());
    }
    
    @Test
    public void testNo4() throws Exception {
        mockMvc.perform(get("/no4").param("brName", "판교점"))
               .andDo(print())
               .andExpect(status().isOk());
    }
    
    @Test
    public void testNo4Error() throws Exception {
        mockMvc.perform(get("/no4").param("brName", "분당점"))
               .andDo(print())
               .andExpect(status().is4xxClientError());
    }

}
 
