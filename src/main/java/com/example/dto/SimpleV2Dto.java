package com.example.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SimpleV2Dto {
    @Setter @Getter
    @NoArgsConstructor
    public static class Request {
        private String message;

        @Builder
        public Request(String message) {
            this.message = message;
        }
    }

    @Setter @Getter
    @NoArgsConstructor
    public static class Response {
        private String message;
        private String code;

        @Builder
        public Response(String message, String code) {
            this.message = message;
            this.code = code;
        }
    }

    @Setter @Getter
    @NoArgsConstructor
    public static class ErrorResponse {
        private String errorCode;
        private String errorDesc;

        @Builder
        public ErrorResponse(String errorCode, String errorDesc) {
            this.errorCode = errorCode;
            this.errorDesc = errorDesc;
        }
    }
}
