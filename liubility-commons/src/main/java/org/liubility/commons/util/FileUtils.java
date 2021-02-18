package org.liubility.commons.util;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * @Author JDragon
 * @Date 2021.02.17 下午 3:31
 * @Email 1061917196@qq.com
 * @Des:
 */
public class FileUtils {
    /**
     * 图片上传 返回访问地址
     * @return imgUrl
     */
    public static String uploadFileReturnUrl(HttpServletRequest request, String filePath, MultipartFile file , String prefixUrl) throws IOException {

        // 获取完整的文件名
        String trueFileName = file.getOriginalFilename();
        // 获取文件后缀名
        assert trueFileName != null;
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
//            // 生成新文件的名字
        String fileName = UUID.randomUUID() + suffix;
//            // 获取项目地址
        String itemPath = getItemPath(request);

        // 判断当前要上传的路径是否存在
        String targetParentPath = filePath + prefixUrl;
        File targetParentFile = new File(targetParentPath);

        if (!targetParentFile.exists()) {
            targetParentFile.mkdirs();
        }


        File targetFile = new File(targetParentPath, fileName);
        file.transferTo(targetFile);

        return itemPath + prefixUrl +fileName;
    }
    public static String getItemPath(HttpServletRequest request){
        String scheme = request.getScheme(); // 获取链接协议，http
        String serverName = request.getServerName(); // 获取服务器名称 localhost
        int serverPort = request.getServerPort(); // 获取端口 8080


        if(scheme.equals("http")&&request.getHeader("origin").contains("https")){
            scheme = "https";
        }
        String path = scheme+"://"+serverName;
        if(serverPort!=80){
            path += ":"+serverPort;
        }
        return path;
    }

    public static void download(String urlPath, String localPath){
        try {
            URL url = new URL(urlPath);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(localPath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
