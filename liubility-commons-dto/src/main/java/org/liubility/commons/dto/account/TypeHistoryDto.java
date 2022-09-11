package org.liubility.commons.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.liubility.commons.dto.annotation.TableHeader;

import java.sql.Date;

/**
 * @Author JDragon
 * @Date 2021.02.19 下午 11:56
 * @Email 1061917196@qq.com
 * @Des:
 */
@Data
public class TypeHistoryDto {

    private long id;

    private long userId;

    private long articleId;

    @TableHeader("跟打日期")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date typeDate;

    @TableHeader("速度")
    private double speed;

    @TableHeader("击键")
    private double keySpeed;

    @TableHeader("码长")
    private double keyLength;

    @TableHeader("字数")
    private int number;

    @TableHeader("回改")
    private int deleteText;

    @TableHeader("退格")
    private int deleteNum;

    @TableHeader("错字")
    private int mistake;

    @TableHeader("选重")
    private int repeatNum;

    @TableHeader("键准")
    private double keyAccuracy;

    @TableHeader("键法")
    private double keyMethod;

    @TableHeader("打词")
    private double wordRate;

    @TableHeader("时间")
    private double time;

    @TableHeader("段号")
    private int paragraph;

    private boolean mobile;
}
