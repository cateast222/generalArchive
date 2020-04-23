package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.Product;
import com.ebs.platform.business.service.IPlatformService;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 11:23
 */
@Api(value = "平台管理", description = "管理平台相关的接口")
@RestController
@RequestMapping("platform")
public class PlatformController  {

    @Autowired
    private IPlatformService platformService;

    @ApiOperation(value = "平台首次使用初始化")
    @PostMapping("firstInit")
    public WebResult firstInit() {
        platformService.firstInit();
        return WebUtils.success();
    }

    @ApiOperation(value = "Test Redis.")
    @GetMapping("/getPrud/{id}")
    @Cacheable(cacheNames ="product",key="#id")//检查缓存中是否有值，如果没有则执行方法，如果有则取缓存中的数据
    public Product getPrud(@PathVariable String id){
        System.out.println("已缓存 ： " + id);

        Product product = new Product();
        product.setId(id);
        product.setName("test " + id );
        return product;
    }

    @ApiOperation(value = "setPrud")
    @PostMapping("/setPrud/{id}")
    @CacheEvict(cacheNames = "product",allEntries = true)//清除key中所有value
    public void setPrud(@PathVariable String id){
        Product product = new Product();
        product.setId(id);
        product.setName("test " + id );
    }


}
