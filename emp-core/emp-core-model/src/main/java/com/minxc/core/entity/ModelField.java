package com.minxc.core.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**********************************************************
 * $()
 * @author Xcm   xianchangmin@126.com
 * @date 2018/6/8
 *
 *********************************************************/

@Entity
@Table(name = "MR_MODEL_FIELDS")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false, unique = false, updatable = true)
    private Model model;
    @Column(name = "NAME", length = 64)
    private String name; //字段名称，为显示使用
    @Column(name = "CODE", length = 64)
    private String code;  //数据库字段
    @Column(name = "TYPE", length = 64)
    private String type; //字段类型
    @Column(name = "LENGTH")
    private int length; //字段长度
    @Column(name = "NULLABLE")
    private boolean nullable;  //是否为空的
    @Column(name = "ISKEY")
    private boolean isKey;   //是否主键
    @Column(name = "COMMENT", length = 255)
    private String comment; //注释
    @Column(name = "IDX")
    private boolean index;  //是否是索引
    @Column(name = "IDX_NAME", length = 64)
    private String indexName; //索引名称
    @Column(name = "ISUNIQUE")
    private boolean uique; // 是否唯一
    @Column(name = "IS_FOREIGN_KEY")
    private boolean isForeignKey;   //是否外键
    @Column(name = "REFERENCE_TABLE", length = 64)
    private String referenceTable; //外键参照表
    @Column(name = "REFERENCE_FIELD", length = 64)
    private String referenceField;  //外键参照列
    @Column(name = "DEFAULT_VALUE", length = 64)
    private String defaultValue; //字段默认值
    @Column(name = "SELECTABLE")
    private boolean selectable;  //是否可检索， 限制字段在页面是否可以展示----目前先放在这里，表示该字段是否的可以参与数据权限的使用
    @Column(name = "CREATE_UID", length = 64)
    private String createUid;
    @Column(name = "WRITE_UID", length = 64)
    private String writeUid;
    @Column(name = "CREATE_DATE", length = 64)
    private String createDate;
    @Column(name = "WRITE_DATE", length = 64)
    private String writeDate;
}
