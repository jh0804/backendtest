package co.kr.metacoding.backendtest.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class JoinDTO {
        private Integer id;

        public JoinDTO(User user) {
            this.id = user.getId();
        }
    }

    @Data
    public static class DTO {
        private Integer id;
        private String name;

        public DTO(User user) {
            this.id = user.getId();
            this.name = user.getName();
        }
    }
}
