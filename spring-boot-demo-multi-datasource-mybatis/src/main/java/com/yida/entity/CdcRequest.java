package com.yida.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CDC_Request")
public class CdcRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Number")
    private String Number;

    @TableField("Requested for")
    private String Requested for;

    @TableField("PRID")
    private String prid;

    @TableField("Short description")
    private String Short description;

    @TableField("Priority")
    private String Priority;

    @TableField("State")
    private String State;

    @TableField("Assignment group")
    private String Assignment group;

    @TableField("Name")
    private String Name;

    @TableField("Closed by")
    private String Closed by;

    @TableField("Opened")
    private LocalDateTime Opened;

    @TableField("Actual Start")
    private LocalDateTime Actual Start;

    @TableField("Actual End")
    private LocalDateTime Actual End;

    @TableField("Duration")
    private Float Duration;

    @TableField("Closed")
    private LocalDateTime Closed;

    @TableField("Resolve Time")
    private String Resolve Time;

    @TableField("Closure comments")
    private String Closure comments;

    @TableField("Configuration item")
    private String Configuration item;

    @TableField("Made SLA")
    private Boolean Made SLA;

    @TableField("Description")
    private String Description;

    @TableField("Opened by")
    private String Opened by;

    @TableField("Contact type")
    private String Contact type;

    @TableField("Application")
    private String Application;

    @TableField("Category")
    private String Category;

    @TableField("CnDescription")
    private String CnDescription;

    @TableField("Hour")
    private String Hour;

    @TableField("SubApplication")
    private String SubApplication;


}
