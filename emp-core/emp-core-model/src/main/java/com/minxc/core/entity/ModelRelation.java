package com.minxc.core.entity;

import lombok.*;

import java.io.Serializable;

/**********************************************************
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/8
 *
 *********************************************************/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ModelRelation implements Serializable {

    private static final long serialVersionUID = -6622870628230967875L;

    private String id;
    private String model;
    private String module;
    private String writeDate;
    private String createDate;
    private String createUid;
    private String writeUid;
    


}
