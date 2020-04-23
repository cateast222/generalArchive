package com.ebs.platform.business.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/26 16:20
 */
@Controller
public class DefaultController {
    @GetMapping("/")
    public String saveApp() {
        return "redirect:index.html";
    }
}
