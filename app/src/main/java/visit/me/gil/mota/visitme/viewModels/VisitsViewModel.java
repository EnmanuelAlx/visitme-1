package visit.me.gil.mota.visitme.viewModels;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.useCases.GetVisits;
import visit.me.gil.mota.visitme.useCases.UseCase;

/**
 * Created by mota on 15/4/2018.
 */

public class VisitsViewModel extends Observable implements UseCase.Result {
    private Interactor interactor;
    private int currentTab;
    private List<Visit> sporadics, scheduleds, frequents;
    private GetVisits getVisits;
    private int skipScheduleds = 0, skipSporadics = 0,  skipFrequents = 0;

    public VisitsViewModel(Interactor interactor) {
        this.interactor = interactor;
        currentTab = 0;
        sporadics = new ArrayList<>();
        scheduleds = new ArrayList<>();
        frequents = new ArrayList<>();
        getVisits = new GetVisits(this);
        runGetByCurrentTab();
    }

    public void refresh() {
        refreshByCurrentTab();
        runGetByCurrentTab();
    }

    private void runGetByCurrentTab() {
        switch (currentTab) {
            case 0:
                getVisits.setParams(currentTab, skipScheduleds, scheduleds);

                break;
            case 1:
                getVisits.setParams(currentTab, skipFrequents, frequents);

                break;
            case 2:
                getVisits.setParams(currentTab, skipSporadics, sporadics);

                break;
        }
        interactor.loading(true);
        getVisits.run();
    }

    private void refreshByCurrentTab() {
        switch (currentTab) {
            case 0:
                skipScheduleds = 0;
                scheduleds.clear();
                break;
            case 1:
                skipFrequents = 0;
                frequents.clear();
                break;
            case 2:
                skipSporadics = 0;
                sporadics.clear();
                break;
        }
    }

    public void changeTab(int position) {
        currentTab = position;
        List<Visit> currentList = getListByCurrentTab();
        if (currentList.size() > 0)
            changeListByCurrentTab();
        else
            runGetByCurrentTab();
    }

    private void changeListByCurrentTab() {
        List<Visit> toChange = getListByCurrentTab();
        interactor.changeList(toChange);
    }

    private List<Visit> getListByCurrentTab()
    {
        switch (currentTab) {
            case 0:
                return scheduleds;
            case 1:
                return frequents;
            case 2:
                return sporadics;
            default:
                return scheduleds;
        }
    }

    @Override
    public void onError(String error) {
        interactor.showError(error);
    }

    @Override
    public void onSuccess() {
        changeListByCurrentTab();
    }

    public interface Interactor {
        void changeList(List<Visit> visit);
        void loading(boolean loading);
        void showError(String error);
    }
}
