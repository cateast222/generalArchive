package com.ebs.platform.core.controller;

import com.ebs.platform.core.exception.BusinessException;
import com.ebs.platform.core.util.DateUtil;
import com.ebs.platform.core.util.FileUploadUtil;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传Controller
 *
 * @author Rao
 */
@Api(value = "文件上传", description = "文件上传处理")
@RestController
@RequestMapping("upload")
public class UploadController {
	private static final String uploadFolder = "/ebsUploadFiles/";

	@Value("${com.ebs.uploadModule}")
	private String uploadModule;

	/**
	 * 上传至本地文件夹
	 */
	@ApiOperation(value = "上传至本地文件夹")
	@PostMapping("/addByWindow")
	public WebResult addByWindow(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return WebUtils.success();
		}

		String fileName = file.getOriginalFilename();
		String filePath = "E:/pdf/admin/";
		File dest = new File(filePath+fileName);
		System.out.println("改名前为"+dest.getName());
		if(dest.exists()){
			dest = new File(filePath+ DateUtil.GetMinSecondCodeString() +fileName);
			System.out.println("改名后是"+dest.getName());
		}

		try {
			file.transferTo(dest);
			return WebUtils.success(dest.getName());

		} catch (IOException e) {
			e.printStackTrace();
			return WebUtils.success(100,"null","上传失败，E:/pdf/admin/路径未找到");
		}
	}

	/**
	 * 上传文件接口
	 *
	 * @param file
	 * @return
	 */
	//@CrossOrigin
	@ApiOperation(value = "单文件上传")
	@PostMapping("/add")
	public WebResult add(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return WebUtils.error("上传文件不能为空");
		}

		String url;
		try {
			url = FileUploadUtil.uploadFile(file);
		} catch (Exception e) {
			return WebUtils.error(e.getMessage());
		}

		return WebUtils.success(url);
	}

	/**
	 * 上传文件接口
	 *
	 * @param url
	 * @return
	 */
	//@CrossOrigin
	@ApiOperation(value = "上传文件删除")
	@PostMapping("/remove")
	public WebResult remove(@RequestBody String url) {
		if (url == null || url.isEmpty()) {
			return WebUtils.error("参数为空");
		}

		try {
			FileUploadUtil.deleteFile(url);
		} catch (Exception e) {
			return WebUtils.error(e.getMessage());
		}
		return WebUtils.success();
	}
}
