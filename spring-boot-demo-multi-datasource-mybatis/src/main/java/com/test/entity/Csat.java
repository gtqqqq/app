package com.test.entity;

import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gtq
 * @since 2020-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Csat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer createHour;

    @TableField("create_Day")
    private Date createDay;

    private Integer ratePhone;

    private Integer rateRequest;

    private Integer rateIncident;


}
