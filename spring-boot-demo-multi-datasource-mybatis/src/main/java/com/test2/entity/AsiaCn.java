package com.test2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gtq
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ASIA_CN")
public class AsiaCn implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Time")
    private LocalDateTime Time;

    @TableField("IncomingSum")
    private Float IncomingSum;

    @TableField("Incoming_CN")
    private Float incomingCn;

    @TableField("Mounth")
    private Integer Mounth;


}
