package org.liubility.commons.dto.robot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author JDragon
 * @Date 2021.02.17 下午 3:23
 * @Email 1061917196@qq.com
 * @Des:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RobotMsgResult {

    private String message;

    private Long senderId;

    private Long acceptId;

    private String param;
}
