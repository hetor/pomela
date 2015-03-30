package com.sishuok.mvc.controller;

import com.pomela.springmvc.test.mvc.controllers.UserController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * 独立测试方式
 * <p>User: Zhang Kaitao
 * <p>Modify: He Tao</p>
 * <p>Date: 13-12-26
 * <p>Version: 1.0
 */
public class UserControllerStandaloneSetupTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        UserController userController = new UserController();
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void testView() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
//                .andExpect(MockMvcResultMatchers.view().name("user/view"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//        Assert.assertNotNull(result.getModelAndView().getModel().get("user"));

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/1"); //构造一个请求
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder); //执行一个请求
        resultActions = resultActions.andExpect(MockMvcResultMatchers.view().name("user/view")); //添加执行完成后的断言
        resultActions = resultActions.andExpect(MockMvcResultMatchers.model().attributeExists("user"));
        resultActions = resultActions.andDo(MockMvcResultHandlers.print()); //添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息
        MvcResult result = resultActions.andReturn(); //表示执行完成后返回相应的结果
        Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
    }

}
