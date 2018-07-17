package com.minxc.core.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/8
 *
 *********************************************************/

@Entity
@Table(name="MR_MODEL_FIELDS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ModelField implements Serializable {

    private static final long serialVersionUID = 9189059039281141720L;

    @Id
    private String id;
    private String model;
    private String model_id;
    private String name;
    private String state;
    private String field_description;
    private String help;
    private String ttype;
    private String relation;
    private String relation_field;
    private String index;
    private String copy;
    private String related;
    private String readonly;
    private String required;
    private String selectable;
    private String translate;
    private String serialization_field_id;
    private String relation_table;
    private String column1;
    private String column2;
    private String store;
    private String domain;
    private String selection;
    private String create_date;
    private String on_delete;
    private String write_uid;
    private String depends;
    private String size;
    private String complete_name;
    private String create_uid;
    private String compute;
    private String write_date;
    private String website_form_blacklisted;

}
