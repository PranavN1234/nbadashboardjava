package com.batch.nbadashboardjava.config;

import com.batch.nbadashboardjava.model.Teams;
import io.micrometer.common.lang.NonNull;
import org.springframework.batch.item.ItemProcessor;

public class TeamsItemProcessor implements ItemProcessor<Teams, Teams> {
    @Override
    public Teams process(@NonNull Teams item) throws Exception {
        return item;
    }
}
