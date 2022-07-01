package com.example.interfaces.okhttp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OkhttpExampleDto {

    @Getter @Setter
    public static class GetRequest {
        private int id;

        public GetRequest(int id) {
            this.id = id;
        }
    }

    @Getter @Setter
    public static class GetResponse {
        private int id;

        public GetResponse(int id) {
            this.id = id;
        }
    }

    @Getter @Setter
    @NoArgsConstructor
    public static class PostRequest {
        private String name;
    }

    @Getter @Setter
    @NoArgsConstructor
    public static class PostResponse {
        private  String name;
    }
}
