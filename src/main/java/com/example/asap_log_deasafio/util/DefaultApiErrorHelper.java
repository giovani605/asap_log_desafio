package com.example.asap_log_deasafio.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultApiErrorHelper {

    private String mensagem;

    private Integer statusCode;

    private String statusCodeType;

}
