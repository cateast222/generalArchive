package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.CodeDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICodeDao extends JpaRepository<CodeDO,String> {

    List<CodeDO> findByDmhAndCc(String s, String s1);

    CodeDO findByDmhAndDm(String s, String parentDm);

    List<CodeDO> findByDmStartingWithAndDmh(String dm,String dmh);

    List<CodeDO> findByDmStartingWithAndDmhAndCc(String dm,String dmh,String cc);
}
