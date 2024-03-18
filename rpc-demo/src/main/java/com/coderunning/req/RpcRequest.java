package com.coderunning.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author haowei.chen
 * @since 2024/3/16 22:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcRequest implements Serializable {
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

    // getter and setter
}


