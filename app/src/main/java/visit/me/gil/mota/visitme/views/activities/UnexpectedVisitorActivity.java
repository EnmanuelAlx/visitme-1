package visit.me.gil.mota.visitme.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.ActivityUnexpectedVisitorBinding;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.viewModels.UnexpectedVisitViewModel;
import visit.me.gil.mota.visitme.views.adapters.PhotoAdapter;

public class UnexpectedVisitorActivity extends BindeableActivity implements UnexpectedVisitViewModel.Contract {


    public UnexpectedVisitViewModel viewModel;
    private ActivityUnexpectedVisitorBinding  binding;
    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String data = getIntent().getStringExtra("data");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        Log.i("UNEXPECTED","DATA: " + data);
        try {
            JSONObject params = new JSONObject(data).getJSONObject("data");
            initDataBinding();
            viewModel = new UnexpectedVisitViewModel(this,params.getJSONObject("visit"),
                                                      params.getJSONArray("photos") );
            binding.setViewModel(viewModel);
            setupAdapter();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setupAdapter() {
        adapter = new PhotoAdapter();
        adapter.setList(viewModel.getPhotos());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.photos.setLayoutManager(manager);
        binding.photos.setAdapter(adapter);
    }

    @Override
    public void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unexpected_visitor);

    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof UnexpectedVisitViewModel) {
            UnexpectedVisitViewModel viewModel = (UnexpectedVisitViewModel) observable;
        }
    }


    @Override
    public void close() {
        Pnotify.makeText(this,"Accion Exitosa", Toast.LENGTH_SHORT, Pnotify.INFO).show();
        finish();
    }

    @Override
    public void onError(String error) {
        Pnotify.makeText(this,error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }
}
