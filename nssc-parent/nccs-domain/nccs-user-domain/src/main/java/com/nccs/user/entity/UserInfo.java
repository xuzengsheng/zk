package com.nccs.user.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Monroe
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private String userCode;

    private String password;

    private Integer age;

    /**
     * 性别  0：女 ，1： 男
     */
    private Integer sex;


}
