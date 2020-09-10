package com.nvct.edu.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ncvt.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dale
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ncvt_edu_course")
public class EduCourse extends BaseEntity<EduCourse> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称`
     */
    private String title;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
