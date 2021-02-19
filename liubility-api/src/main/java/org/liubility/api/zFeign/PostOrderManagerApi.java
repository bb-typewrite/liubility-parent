package org.liubility.api.zFeign;

import org.liubility.commons.zFeign.ZFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author JDragon
 * @Date 2021.02.17 下午 3:13
 * @Email 1061917196@qq.com
 * @Des:
 */

@ZFeign(baseUrl = "http://localhost:8081", basePath = "/robot",depth = "result")
public interface PostOrderManagerApi {

}
