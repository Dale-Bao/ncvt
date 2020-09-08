package com.ncvt.common.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by guohang on 2019/10/28
 */
@Data
public class BaseEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID
     */
    @NotNull(message = "{param.empty}")
    @TableId(value = "id")
    @JSONField(ordinal = 0)
    private String id;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @TableField(strategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT)
    protected String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(strategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT)
    protected Date createdDt;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    @TableField(strategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    protected String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(strategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT_UPDATE)
    protected Date updatedDt;


    /**
     * 是否已逻辑删除,0-未删除，1-已删除
     */
    @ApiModelProperty("是否已逻辑删除,0-未删除，1-已删除")
    @TableLogic
    protected Integer isDeleted;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    protected String remark;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    protected Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
