package com.ebs.platform.core.conf;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public interface IMapperSettings<T> {
    Optional<T> getSettingByDictCodeValue(String code);
    Set<T> filterSettings(Predicate<T> predicate);
    int count();

}
