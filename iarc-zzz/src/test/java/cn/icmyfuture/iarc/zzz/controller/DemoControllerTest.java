package cn.icmyfuture.iarc.zzz.controller;

import cn.icmyfuture.iarc.zzz.SpringTest;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class DemoControllerTest extends SpringTest {
    @Test
    public void pubG() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/demo/index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
