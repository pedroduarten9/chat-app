package com.pneto.chatapplication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto {

    @NotBlank
    private String message;

    @NotBlank
    private String sender;

    @NotBlank
    private String destination;

    private Instant time;
}
