package com.pneto.chatapplication.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Message extends BaseEntity {

    @NotBlank
    @Column(name = "valueTo")
    private String to;

    @NotBlank
    @Column(name = "valueFrom")
    private String from;

    @NotBlank
    @Lob
    private String messagePayload;
}
