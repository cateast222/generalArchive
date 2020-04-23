package com.ebs.platform.core.util;

import com.ebs.platform.core.enums.FileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FileUploadUtil {

	private static final String linuxUploadFolder = "/usr/local/ebsUpload/";
	private static final String windowsUploadFolder = "E:/pdf/admin/";

	//@Value("${com.ebs.uploadModule}")
	//private static String uploadModule;

	/**
	 * 字节数组转16进制字符串
	 *
	 * @param src
	 * @return 16进制字符串
	 */
	private static String bytesToHexString(byte[] src) {

		if (src == null || src.length <= 0) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length; i++) {
			String hs = Integer.toHexString(src[i] & 0xFF);
			if (hs.length() < 2) {
				sb.append("0");
			}
			sb.append(hs);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 得到文件头
	 *
	 * @param is 文件输入流
	 * @return 文件头
	 * @throws IOException
	 */
	private static String getFileHeader(InputStream is) throws IOException {

		byte[] b = new byte[28];
		is.read(b, 0, 28);
		//if (is != null) {
		//	is.close();
		//}
		return bytesToHexString(b);
	}

	/**
	 * 判断文件类型
	 *
	 * @param is 文件输入流
	 * @return 文件类型
	 */
	public static FileTypeEnum getFileType(InputStream is) throws IOException {

		String fileHeader = getFileHeader(is);
		if (fileHeader == null || fileHeader.length() == 0) {
			return null;
		}

		for (FileTypeEnum fileTypeEnum : FileTypeEnum.values()) {
			if (fileHeader.startsWith(fileTypeEnum.getValue())) {
				return fileTypeEnum;
			}
		}

		return null;
	}

	public static String uploadFile(MultipartFile file) throws Exception {
		if (file == null || file.isEmpty()) {
			throw new Exception("上传文件不能为空");
		}
		if (getFileType(file.getInputStream()) == null) {
			throw new Exception("文件类型验证失败");
		}

		File rootFolder = new File(linuxUploadFolder);
		if (!rootFolder.exists()) {
			rootFolder.mkdir();
		} else {
			if (!rootFolder.isDirectory()) {
				rootFolder.mkdir();
			}
		}

		//取得当前上传文件的文件名称
		String fileName = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		File newFolder = new File(linuxUploadFolder.concat(uuid));
		if (!newFolder.exists()) {
			newFolder.mkdir();
		}
		//String toFileName = (uploadModule == null ? "" : uploadModule + "/") + uuid + "/" + fileName;
		String toFileName = uuid + "/" + fileName;
		file.transferTo(new File(linuxUploadFolder.concat(toFileName)));

		return toFileName;
	}

	public static void deleteFile(String url) throws Exception {
		if (url == null || url.isEmpty()) {
			throw new Exception("参数为空");
		}

		File file = new File(linuxUploadFolder + url);
		if (!file.exists()) {
			throw new Exception("文件不存在");
		} else {
			if (file.isDirectory()) {
				throw new Exception("存在同名文件夹");
			} else {
				File parent = file.getParentFile();
				file.delete();
				//删除空文件夹
				if (parent.list().length < 1) {
					parent.delete();
				}
			}
		}
	}

}
