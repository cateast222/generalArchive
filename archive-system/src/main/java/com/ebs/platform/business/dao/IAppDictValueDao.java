package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.*;
import com.ebs.platform.core.annotation.Tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:46
 */
// @Tenant 在这个dao里面用不了
public interface IAppDictValueDao extends JpaRepository<AppDictValueDO,String>,JpaSpecificationExecutor<AppDictValueDO> {

    //    @Query(value="select max(sort)+1 from tb_dict_value where dict_id = ?",nativeQuery=true)
//    Optional<Integer> maxSort(String dictId);SqlServer

    @Query(value="select max(sort)+1 from tb_dict_value where dict_id = ?",nativeQuery=true)
    Integer maxSort(String dictId);// MySql

    List<AppDictValueDO> queryByAppAndDeleted(AppDO app,boolean isDelete);

  //  @Query(value="select * from tb_dict_value ",nativeQuery=true)
    List<AppDictValueDO> queryByTenantAndDictAndDeleted(TenantDO tenantDO, AppDictDO dict, boolean isDelete);

    List<AppDictValueDO> queryByCreatedByAndDictAndDeleted(AccountDO user,AppDictDO dict,boolean isDelete);

	AppDictValueDO queryByIdAndDeleted(String id,Boolean b);

	List<AppDictValueDO> queryByAppAndDeletedAndValueIn(AppDO appDO, boolean b, List<String> typeDictValueList);

    List<AppDictValueDO> queryByDeleted(boolean b);

    List<AppDictValueDO> findByDeletedAndIdIn(boolean b, List<String> all);

    @Query(value = "SELECT T2.id FROM (  SELECT  @r AS _id,( SELECT @r \\:= parent_id FROM tb_dict_value WHERE id = _id ) AS parent_id, @l \\:= @l + 1 AS lvl " +
            " FROM ( SELECT @r \\:= ?1, @l \\:= 0 ) vars, tb_dict_value h  " +
            " WHERE @r <> '0'  ) T1  JOIN tb_dict_value T2 ON T1._id = T2.id WHERE is_deleted=0 ORDER BY T1.lvl DESC",nativeQuery = true)
    List<String> findByNative(String id);

    AppDictValueDO findByIdAndDeleted(String nativePlaceId, boolean b);
}
