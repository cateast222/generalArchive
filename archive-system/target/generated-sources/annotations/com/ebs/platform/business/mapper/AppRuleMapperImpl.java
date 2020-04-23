package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.AppRuleDO;
import com.ebs.platform.business.dto.AppRuleDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-03T17:06:49+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class AppRuleMapperImpl implements AppRuleMapper {

    @Override
    public AppRuleDTO from(AppRuleDO appRuleDO) {
        if ( appRuleDO == null ) {
            return null;
        }

        AppRuleDTO appRuleDTO = new AppRuleDTO();

        String id = appRuleDOAppId( appRuleDO );
        if ( id != null ) {
            appRuleDTO.setAppId( id );
        }
        appRuleDTO.setId( appRuleDO.getId() );
        appRuleDTO.setDeleted( appRuleDO.getDeleted() );
        appRuleDTO.setUrlPattern( appRuleDO.getUrlPattern() );
        appRuleDTO.setMethods( appRuleDO.getMethods() );

        return appRuleDTO;
    }

    @Override
    public AppRuleDO to(AppRuleDTO appRuleDTO) {
        if ( appRuleDTO == null ) {
            return null;
        }

        AppRuleDO appRuleDO = new AppRuleDO();

        appRuleDO.setId( appRuleDTO.getId() );
        appRuleDO.setDeleted( appRuleDTO.getDeleted() );
        appRuleDO.setUrlPattern( appRuleDTO.getUrlPattern() );
        appRuleDO.setMethods( appRuleDTO.getMethods() );

        return appRuleDO;
    }

    @Override
    public List<AppRuleDTO> from(List<AppRuleDO> appRuleDOS) {
        if ( appRuleDOS == null ) {
            return null;
        }

        List<AppRuleDTO> list = new ArrayList<AppRuleDTO>( appRuleDOS.size() );
        for ( AppRuleDO appRuleDO : appRuleDOS ) {
            list.add( from( appRuleDO ) );
        }

        return list;
    }

    @Override
    public List<AppRuleDO> to(List<AppRuleDTO> appRuleDTOS) {
        if ( appRuleDTOS == null ) {
            return null;
        }

        List<AppRuleDO> list = new ArrayList<AppRuleDO>( appRuleDTOS.size() );
        for ( AppRuleDTO appRuleDTO : appRuleDTOS ) {
            list.add( to( appRuleDTO ) );
        }

        return list;
    }

    private String appRuleDOAppId(AppRuleDO appRuleDO) {
        if ( appRuleDO == null ) {
            return null;
        }
        AppDO app = appRuleDO.getApp();
        if ( app == null ) {
            return null;
        }
        String id = app.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
