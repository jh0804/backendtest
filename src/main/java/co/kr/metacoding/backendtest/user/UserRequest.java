package co.kr.metacoding.backendtest.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String name;

        public User toEntity(){
            return User.builder()
                    .name(name)
                    .build();
        }
    }
}
