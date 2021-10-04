package org.liubility.commons.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author JDragon
 * @Date 2021.02.20 下午 12:16
 * @Email 1061917196@qq.com
 * @Des:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Integer id;

    private String title;

    private String content;

    public ArticleDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
