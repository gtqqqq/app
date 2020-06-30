package com.test.code.test.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Test implements Serializable, ObjectFactory<Test> {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Date createDate;

    private Boolean state;

    private Double money;

    @Override
    public Test getObject() throws BeansException {
        return this;
    }
}
