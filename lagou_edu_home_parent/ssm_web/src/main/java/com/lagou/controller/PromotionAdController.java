package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> allPromotionAdByPage = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        return new ResponseResult(true,200,"广告分页查询成功",allPromotionAdByPage);
    }
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){
        //1.判断文件是否为空
        if(multipartFile.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));
        //3.获取原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //4.生成新文件名
        String newFilename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //5.文件上传
        String uploadpath = substring + "/upload/";
        File filePath = new File(uploadpath, newFilename);
        //如果目录不存在就创建目录
        if(!filePath.exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }
        //图片真正上传
        try {
            multipartFile.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //6.将文件名与路径返回，进行响应
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFilename);
        map.put("filePath","http://localhost:8080/upload/"+newFilename);
        ResponseResult re = new ResponseResult(true, 200, "图片上传成功", map);
        return re;
    }
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdSstatus(id, status);
        return new ResponseResult(true,200,"广告动态上下线成功",null);
    }
}
