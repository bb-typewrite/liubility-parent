package org.liubility.commons.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author JDragon
 * @Date 2021.02.20 下午 12:26
 * @Email 1061917196@qq.com
 * @Des:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryArticleDto {

    private ArticleDto articleDto;

    private TypeHistoryDto typeHistoryDto;
}
