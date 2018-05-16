package visit.me.gil.mota.visitme.views.activities;

import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;


import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.ActivityChangePasswordBinding;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.viewModels.ChangePasswordViewModel;

public class ChangePasswordActivity extends BindeableActivity implements ChangePasswordViewModel.Contract {

    public static final int CLOSE = 888;
    public ChangePasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof ChangePasswordViewModel) {
            ChangePasswordViewModel viewModel = (ChangePasswordViewModel) observable;
        }
    }

    @Override
    public void initDataBinding() {
        ActivityChangePasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        viewModel = new ChangePasswordViewModel(this, getIntent().getStringExtra("code"), getIntent().getStringExtra("email"));
        binding.setViewModel(viewModel);
    }


    @Override
    public void showInfo(String str) {
        Pnotify.makeText(this,str, Toast.LENGTH_SHORT, Pnotify.INFO).show();
    }

    @Override
    public void onError(String err) {
        Pnotify.makeText(this,err, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }

    @Override
    public void finishSuccessfull() {
        setResult(CLOSE);
        finish();
    }
}
