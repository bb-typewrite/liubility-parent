package org.liubility.commons.dto.account;

import lombok.*;

/**
 * @Author JDragon
 * @Date 2021.04.29 下午 10:41
 * @Email 1061917196@qq.com
 * @Des:
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NumDto {

    private int num;

    private int rightNum;

    private int misNum;

    private int dateNum;

    private String numKey;
}
