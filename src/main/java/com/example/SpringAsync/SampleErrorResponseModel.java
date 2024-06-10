package com.example.SpringAsync;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SampleErrorResponseModel {
    private String code;
    private String message;
    private LocalDateTime timestamp;
}