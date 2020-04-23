package com.ebs.platform.core.security;

import com.ebs.platform.core.enums.UserTypeEnum;
import com.ebs.platform.core.exception.BusinessException;
import com.ebs.platform.core.old.UserContextDTO;
import com.ebs.platform.core.security.model.SimpleAuthority;
import com.ebs.platform.core.util.SerializeUtils;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * jwt的验证过滤器
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/4/26 16:05
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private String secret = "foxzjwtcode";
//    private IAccountService accountService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
//        this.accountService = accountService;
    }

//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, IAccountService accountService) {
//        super(authenticationManager);
//        this.accountService = accountService;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,ServletException,BusinessException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        //开始验证jwt
        WebResult result = getHeaderJwtTokenUser(request);
        if(result.getCode() == 0){
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)result.getData();
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                

               // System.out.println("getRequestURI:"+request.getRequestURI()+"getRequestURL:"+request.getRequestURL());
                //测试拦截
                //if(!"/archiveman/queryByFilter".equals(request.getRequestURI())){
                    //throw new BusinessException("您无权访问");
                //}



                // TODO: 2018-09-27 后台权限需要做过滤，暂时不进行过滤，需后续完善
                //chain.doFilter(request, response); 注释BY_LWY

                AntPathRequestMatcher matcher;
                //Boolean isPass = false;
                Boolean isPass = true;
                for (GrantedAuthority ga : authentication.getAuthorities()) {
                    SimpleAuthority sa = (SimpleAuthority) ga;
                    String url = sa.getUrl();
                    matcher = new AntPathRequestMatcher(url);
                    if (matcher.matches(request)) {
//                        if( sa.getType().toLowerCase().contains(request.getMethod().toLowerCase())){
//                            isPass = true;
//                        }  //先不验证请求类型
                        isPass = true;//先设置为true 实际这里应该是false
                    }
                }
                if(isPass){
                    chain.doFilter(request, response);
                }else{
                    WebUtils.sendMessage(response, "抱歉,您无权访问资源" + request.getRequestURI().toString());
                }



            } else {
                WebUtils.sendMessage(response, "身份验证失败!");
            }
        }else{
            WebUtils.sendMessage(response,result.getMessage());
        }
    }
    private WebResult<UsernamePasswordAuthenticationToken> getHeaderJwtTokenUser(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return getAuthentication(token);
    }
    /**
     * 根据token中的用户标志，查询用户的基本信息，以及api权限
     * @param token
     * @return
     */
    private WebResult<UsernamePasswordAuthenticationToken> getAuthentication(String token) throws BusinessException {
        if (token != null) {
            try {
                Claims user = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody();
                UserContextDTO obj = UserContextDTO.parse(user);
                obj.setToken(token);
                //return WebUtils.success(new UsernamePasswordAuthenticationToken(UserContextDTO.parse(user), null, null));
                Object obj22 = new UsernamePasswordAuthenticationToken(obj, null, null);

                List<SimpleAuthority> mySimple = new ArrayList<>();

                SimpleAuthority abc = new SimpleAuthority();
                abc.setUrl("/archiveman/queryByFilter");
                abc.setType("POST");
                mySimple.add(abc);

                System.out.println("helloWorld"+obj22.toString());

                return WebUtils.success(new UsernamePasswordAuthenticationToken(obj, null, mySimple));
            }
            catch(ExpiredJwtException eje){
                return WebUtils.error(8002,"登录已过期，请重新登录");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return WebUtils.error(-1,ex.getMessage());
            }
        }
        return WebUtils.success();
    }

}

