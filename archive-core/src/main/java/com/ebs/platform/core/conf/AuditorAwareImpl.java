package com.ebs.platform.core.conf;

import com.ebs.platform.core.old.UserContextDTO;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@EnableJpaAuditing
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * 根据登录状态，获取当前userid，用于创建、修改、删除Entity时自动赋值
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<String> result = null;
        try{
            UserContextDTO user = (UserContextDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            result = Optional.of(user.getUserId());
        }catch (Exception ex){
            result = Optional.of(null);
        }
        return result;
    }


}
