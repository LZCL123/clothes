package com.zhichao.clothes.controller.clothes;

import com.zhichao.clothes.common.oss.AliOssUtil;
import com.zhichao.clothes.common.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传
 *
 * @author lonelykkk
 * @email 2765314967@qq.com
 * @date 2024/5/15 9:12
 * @Version V1.0
 */
@RestController
@Slf4j
@RequestMapping("/app/file")
@Tag(name = "文件处理")
public class OSSFileUploadController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("upload")
    public Result upload(@RequestParam MultipartFile image) {
        System.out.println(image);
        log.info("file：{}", image);
        try {
            //原始文件名
            String originalFilename = image.getOriginalFilename();
            //截取原始文件名的后缀   dfdfdf.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName =  UUID.randomUUID().toString() + extension;

            //文件的请求路径
            String filePath = aliOssUtil.upload(image.getBytes(), objectName);
            return Result.ok(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
        return Result.fail(507, "文件上传失败");
    }
}

