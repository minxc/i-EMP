package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;

/**********************************************************
 * $()
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

    @Id
    private String id;
    private String model;
    private String module;
    private String date_write;
    private String date_init;
    private String create_uid;
    private String write_uid;
    private String write_date;
    private String create_date;


}
