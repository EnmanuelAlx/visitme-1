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

public class VisitsViewModel extends TabedListViewModel<Visit> {

    private List<Visit> sporadics, scheduleds, frequents;
    private GetVisits getVisits;
    private int skipScheduleds = 0, skipSporadics = 0,  skipFrequents = 0;

    public VisitsViewModel(TabedListViewModel.Interactor interactor) {
        super(interactor);
    }


    @Override
    protected void runGetByCurrentTab() {
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

    @Override
    protected void refreshByCurrentTab() {
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

    @Override
    protected void init() {
        sporadics = new ArrayList<>();
        scheduleds = new ArrayList<>();
        frequents = new ArrayList<>();
        getVisits = new GetVisits(this);
    }

    @Override
    protected List<Visit> getListByCurrentTab()
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


}
