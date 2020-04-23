package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.TableConfig;
import com.ebs.platform.business.dto.archive.TableConfigDTO;
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
public class TableConfigMapperImpl implements TableConfigMapper {

    @Override
    public TableConfigDTO from(TableConfig tableConfig) {
        if ( tableConfig == null ) {
            return null;
        }

        TableConfigDTO tableConfigDTO = new TableConfigDTO();

        tableConfigDTO.setEditOrder( tableConfig.getEditOrder() );
        tableConfigDTO.setColumnLength( tableConfig.getColumnLength() );
        tableConfigDTO.setIsForeign( tableConfig.getIsForeign() );
        tableConfigDTO.setDefaultValue( tableConfig.getDefaultValue() );
        tableConfigDTO.setMinLength( tableConfig.getMinLength() );
        tableConfigDTO.setTypeLevel( tableConfig.getTypeLevel() );
        tableConfigDTO.setListShow( tableConfig.getListShow() );
        tableConfigDTO.setArchiveType( tableConfig.getArchiveType() );
        tableConfigDTO.setColumnDict( tableConfig.getColumnDict() );
        tableConfigDTO.setIsPrimary( tableConfig.getIsPrimary() );
        tableConfigDTO.setColumnTypecode( tableConfig.getColumnTypecode() );
        tableConfigDTO.setColumnDatasource( tableConfig.getColumnDatasource() );
        tableConfigDTO.setCascadeData( tableConfig.getCascadeData() );
        tableConfigDTO.setColumnCode( tableConfig.getColumnCode() );
        tableConfigDTO.setId( tableConfig.getId() );
        tableConfigDTO.setEditShow( tableConfig.getEditShow() );
        tableConfigDTO.setIsRequired( tableConfig.getIsRequired() );
        tableConfigDTO.setQueryShow( tableConfig.getQueryShow() );
        tableConfigDTO.setColumnLimit( tableConfig.getColumnLimit() );
        tableConfigDTO.setColumnComponent( tableConfig.getColumnComponent() );
        tableConfigDTO.setTypeCode( tableConfig.getTypeCode() );
        tableConfigDTO.setColumnType( tableConfig.getColumnType() );
        tableConfigDTO.setQueryOrder( tableConfig.getQueryOrder() );
        tableConfigDTO.setListOrder( tableConfig.getListOrder() );
        tableConfigDTO.setColumnName( tableConfig.getColumnName() );

        return tableConfigDTO;
    }

    @Override
    public TableConfig to(TableConfigDTO tableConifgDTO) {
        if ( tableConifgDTO == null ) {
            return null;
        }

        TableConfig tableConfig = new TableConfig();

        tableConfig.setId( tableConifgDTO.getId() );
        tableConfig.setColumnName( tableConifgDTO.getColumnName() );
        tableConfig.setColumnCode( tableConifgDTO.getColumnCode() );
        tableConfig.setColumnType( tableConifgDTO.getColumnType() );
        tableConfig.setColumnTypecode( tableConifgDTO.getColumnTypecode() );
        tableConfig.setTypeCode( tableConifgDTO.getTypeCode() );
        tableConfig.setTypeLevel( tableConifgDTO.getTypeLevel() );
        tableConfig.setMinLength( tableConifgDTO.getMinLength() );
        tableConfig.setColumnLength( tableConifgDTO.getColumnLength() );
        tableConfig.setIsPrimary( tableConifgDTO.getIsPrimary() );
        tableConfig.setIsForeign( tableConifgDTO.getIsForeign() );
        tableConfig.setIsRequired( tableConifgDTO.getIsRequired() );
        tableConfig.setColumnComponent( tableConifgDTO.getColumnComponent() );
        tableConfig.setColumnDatasource( tableConifgDTO.getColumnDatasource() );
        tableConfig.setColumnDict( tableConifgDTO.getColumnDict() );
        tableConfig.setCascadeData( tableConifgDTO.getCascadeData() );
        tableConfig.setColumnLimit( tableConifgDTO.getColumnLimit() );
        tableConfig.setDefaultValue( tableConifgDTO.getDefaultValue() );
        tableConfig.setQueryShow( tableConifgDTO.getQueryShow() );
        tableConfig.setListShow( tableConifgDTO.getListShow() );
        tableConfig.setEditShow( tableConifgDTO.getEditShow() );
        tableConfig.setQueryOrder( tableConifgDTO.getQueryOrder() );
        tableConfig.setListOrder( tableConifgDTO.getListOrder() );
        tableConfig.setEditOrder( tableConifgDTO.getEditOrder() );
        tableConfig.setArchiveType( tableConifgDTO.getArchiveType() );

        return tableConfig;
    }

    @Override
    public List<TableConfigDTO> from(List<TableConfig> tableConfigs) {
        if ( tableConfigs == null ) {
            return null;
        }

        List<TableConfigDTO> list = new ArrayList<TableConfigDTO>( tableConfigs.size() );
        for ( TableConfig tableConfig : tableConfigs ) {
            list.add( from( tableConfig ) );
        }

        return list;
    }

    @Override
    public List<TableConfig> to(List<TableConfigDTO> tableConifgDTOS) {
        if ( tableConifgDTOS == null ) {
            return null;
        }

        List<TableConfig> list = new ArrayList<TableConfig>( tableConifgDTOS.size() );
        for ( TableConfigDTO tableConfigDTO : tableConifgDTOS ) {
            list.add( to( tableConfigDTO ) );
        }

        return list;
    }
}
