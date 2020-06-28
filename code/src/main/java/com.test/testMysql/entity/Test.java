package com.test.testMysql.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gtq
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;


}
