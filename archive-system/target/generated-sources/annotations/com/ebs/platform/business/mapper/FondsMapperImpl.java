package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Fonds;
import com.ebs.platform.business.dto.fonds.FondsDTOByPage;
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
public class FondsMapperImpl implements FondsMapper {

    @Override
    public FondsDTOByPage from(Fonds entDO) {
        if ( entDO == null ) {
            return null;
        }

        FondsDTOByPage fondsDTOByPage = new FondsDTOByPage();

        fondsDTOByPage.setName( entDO.getName() );
        fondsDTOByPage.setDescription( entDO.getDescription() );
        fondsDTOByPage.setCode( entDO.getCode() );
        fondsDTOByPage.setId( entDO.getId() );

        return fondsDTOByPage;
    }

    @Override
    public Fonds to(FondsDTOByPage entDTO) {
        if ( entDTO == null ) {
            return null;
        }

        Fonds fonds = new Fonds();

        fonds.setId( entDTO.getId() );
        fonds.setCode( entDTO.getCode() );
        fonds.setName( entDTO.getName() );
        fonds.setDescription( entDTO.getDescription() );

        return fonds;
    }

    @Override
    public List<FondsDTOByPage> from(List<Fonds> entDOS) {
        if ( entDOS == null ) {
            return null;
        }

        List<FondsDTOByPage> list = new ArrayList<FondsDTOByPage>( entDOS.size() );
        for ( Fonds fonds : entDOS ) {
            list.add( from( fonds ) );
        }

        return list;
    }

    @Override
    public List<Fonds> to(List<FondsDTOByPage> entDTOS) {
        if ( entDTOS == null ) {
            return null;
        }

        List<Fonds> list = new ArrayList<Fonds>( entDTOS.size() );
        for ( FondsDTOByPage fondsDTOByPage : entDTOS ) {
            list.add( to( fondsDTOByPage ) );
        }

        return list;
    }
}
