package com.example.clientforbehance.common;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showRefresh();
    @StateStrategyType(SkipStrategy.class)
    void hideRefresh();
    void showError();
}
