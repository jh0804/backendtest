package co.kr.metacoding.backendtest.integre;

import co.kr.metacoding.backendtest.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {

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

    // 유저 등록 성공
    @Test
    public void save_test() throws Exception {
        // given
        UserRequest.SaveDTO reqDTO = new UserRequest.SaveDTO();
        reqDTO.setName("haha");

        String requestBody = om.writeValueAsString(reqDTO);
        // System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        // System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4));
    }

    // 유저 등록 실패
    @Test
    public void save_fail_test() throws Exception {
        // given
        UserRequest.SaveDTO reqDTO = new UserRequest.SaveDTO();
        reqDTO.setName("ssar");

        String requestBody = om.writeValueAsString(reqDTO);
        // System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .post("/users")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        //System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("중복된 유저네임이 존재합니다."));
    }

    // 유저 조회 성공
    @Test
    public void get_detail_test() throws Exception {
        // given
        Integer userId = 1;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}", userId)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        // System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ssar"));
    }

    // 유저 조회 실패
    @Test
    public void get_detail_fail_test() throws Exception {
        // given
        Integer userId = 4;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}", userId)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        // System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("해당 유저를 찾을 수 없습니다."));
    }

    // 유저 수정 성공
    @Test
    public void update_test() throws Exception {
        // given
        Integer userId = 1;

        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setName("ssarUpdateName");

        String requestBody = om.writeValueAsString(reqDTO);
        // System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/users/{id}", userId)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        // System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ssarUpdateName"));
    }

    // 유저 수정 실패
    @Test
    public void update_fail_test() throws Exception {
        // given
        Integer userId = 4;

        UserRequest.UpdateDTO reqDTO = new UserRequest.UpdateDTO();
        reqDTO.setName("ssarUpdateName");

        String requestBody = om.writeValueAsString(reqDTO);
        // System.out.println(requestBody);

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/users/{id}", userId)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        // System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.reason").value("해당 유저를 찾을 수 없습니다."));
    }

    // URL에 ? & = : //를 제외한 특수문자가 포함 시 차단 filter 테스트
    @Test
    public void get_detail_filter_test() throws Exception {
        // given
        Integer userId = 1;

        // when
        ResultActions actions = mvc.perform(
                MockMvcRequestBuilders
                        .get("/users/{id}?name=test!!", userId)
        );

        // eye
        String responseBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.headers").isMap());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.body.reason").value("허용되지 않은 특수문자가 URL에 포함되어 있습니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("BAD_REQUEST"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.statusCodeValue").value(400));
    }
}
