package com.minxc.app.common;

import lombok.*;

import java.io.Serializable;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/7/5
 *
 *********************************************************/
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ReuqestResult implements Serializable {
    private String code;
    private String message;
    private Object result;
}
