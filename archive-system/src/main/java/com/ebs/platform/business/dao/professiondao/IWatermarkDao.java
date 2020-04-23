package com.ebs.platform.business.dao.professiondao;


import com.ebs.platform.business.domain.professionentity.Watermark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IWatermarkDao extends JpaRepository<Watermark,Integer>, JpaSpecificationExecutor<Watermark> {
    Watermark queryByDeletedAndId(boolean b, String id);

    List<Watermark> queryByDeletedAndWatermarkCodeContainingOrWatermarkNameContaining(boolean b, String param, String param1);

    List<Watermark> queryByDeleted(boolean b);
}
