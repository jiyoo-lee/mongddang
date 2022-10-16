package com.jeeyulee.mongddang.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultDTO {
    private Boolean success;
    private Object data;
}
