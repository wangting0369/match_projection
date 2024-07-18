package com.zixian.matchprojection.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签
 * @TableName tag
 */
@TableName(value ="tag")
@Data
public class Tag implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 上传用户id
     */
    private Long userId;

    /**
     * 父标签
     */
    private Long parentId;

    /**
     * 是否是父标签
     */
    private Integer isParent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic //mybatis-plus 控制逻辑删除
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}