package com.example.backend.dto;

import com.example.backend.enums.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class ErrorResponse {

        private final String errorCode;
        private final String errorMessage;
        private final String detailMessage;
        private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        public static ResponseEntity<Object> toResponseObjectEntity(ErrorCode e) {
                return ResponseEntity
                        .status(e.getHttpStatus())
                        .body(ErrorResponse.builder()
                                .errorCode(e.name())
                                .errorMessage(e.getDetail())
                                .detailMessage(e.getDetail())
                                .build()
                        );
        }

        public static ResponseEntity<Object> toResponseObjectEntity(ErrorCode e, String detailMessage) {
                return ResponseEntity
                        .status(e.getHttpStatus())
                        .body(ErrorResponse.builder()
                                .errorCode(e.name())
                                .errorMessage(e.getDetail())
                                .detailMessage(detailMessage)
                                .build()
                        );
        }


}