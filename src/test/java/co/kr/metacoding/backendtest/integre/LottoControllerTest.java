package co.kr.metacoding.backendtest.integre;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LottoControllerTest {
    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setUp(){
        // 테스트 시작 전에 실행할 코드
    }

    @AfterEach
    public void tearDown(){
        // 테스트 후 정리할 코드
    }

    // 로또 번호 발급
    @Test
    public void save_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/lottos")
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        // System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers.length()").value(6));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers[0]").isNumber());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers[1]").isNumber());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers[2]").isNumber());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers[3]").isNumber());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers[4]").isNumber());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.numbers[5]").isNumber());

    }


}
