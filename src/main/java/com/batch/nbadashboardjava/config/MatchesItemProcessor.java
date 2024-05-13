package com.batch.nbadashboardjava.config;

import com.batch.nbadashboardjava.model.Matches;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class MatchesItemProcessor implements ItemProcessor<Matches, Matches> {
    @Override
    public Matches process(@NonNull Matches item) throws Exception {

        return item;
    }
}
