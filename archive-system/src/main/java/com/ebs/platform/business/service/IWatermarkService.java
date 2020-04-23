package com.ebs.platform.business.service;

import com.ebs.platform.business.domain.professionentity.Watermark;

import java.util.List;
import java.util.Map;

public interface IWatermarkService {

    void save(Watermark watermark);

    Watermark findById(String id);

    List<Watermark> queryByFilter(String param);

    List<Map> findBySelect();
}
