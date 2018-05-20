package visit.me.gil.mota.visitme.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.otaliastudios.autocomplete.RecyclerViewPresenter;

import java.util.ArrayList;
import java.util.List;

import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.useCases.GetCompanies;
import visit.me.gil.mota.visitme.useCases.UseCase;
import visit.me.gil.mota.visitme.viewModels.ItemUserViewModel;
import visit.me.gil.mota.visitme.views.adapters.UserAdapter;

public class CompaniesPresenter extends RecyclerViewPresenter implements UseCase.Result {

    private UserAdapter adapter;
    private List<User> companies;
    private GetCompanies get;
    public CompaniesPresenter(Context context, ItemUserViewModel.Contract contract) {
        super(context);
        adapter = new UserAdapter(contract);
        companies = new ArrayList<>();
        adapter.setList(companies);
        get = new GetCompanies(this, companies);
    }

    @Override
    protected RecyclerView.Adapter instantiateAdapter() {
        return adapter;
    }

    @Override
    protected void onQuery(@Nullable CharSequence query) {
        companies.clear();
        get.setQuery(query != null ? query.toString() : "");
        get.run();
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess() {
        Log.i("QUERY","SHOW ITEMS:"+ companies.toString());
        adapter.notifyDataSetChanged();
    }
}
