package com.yida.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author guantianqi
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Csat implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    @TableField("create_Day")
    private Date createDay;

    private String csatType;

    private Integer h0;

    private Integer h1;

    private Integer h2;

    private Integer h3;

    private Integer h4;

    private Integer h5;

    private Integer h6;

    private Integer h7;

    private Integer h8;

    private Integer h9;

    private Integer h10;

    private Integer h11;

    private Integer h12;

    private Integer h13;

    private Integer h14;

    private Integer h15;

    private Integer h16;

    private Integer h17;

    private Integer h18;

    private Integer h19;

    private Integer h20;

    private Integer h21;

    private Integer h22;

    private Integer h23;


}
