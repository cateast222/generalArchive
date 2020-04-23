package com.ebs.platform.core.conf;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultMapperSettingsImpl<T extends BaseSettings> implements IMapperSettings<T> {

    private Set<T> _settings;
    public DefaultMapperSettingsImpl(Set<T> settings){
        this._settings = settings;
    }
    public Optional<T> getSettingByDictCodeValue(String code) {
        return this.filterSettings(s->s.getDictCodeValue()== code).stream().findFirst();
    }

    @Override
    public Set<T> filterSettings(Predicate<T> predicate) {
        return this._settings.stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    @Override
    public int count() {
        if(this._settings==null)
            return 0;
        return this._settings.size();
    }
}
