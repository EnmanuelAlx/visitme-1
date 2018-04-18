package visit.me.gil.mota.visitme.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.ActivityForgotPasswordBinding;
import visit.me.gil.mota.visitme.databinding.ActivityLoginBinding;
import visit.me.gil.mota.visitme.viewModels.ForgotPasswordViewModel;
import visit.me.gil.mota.visitme.viewModels.LoginViewModel;

public class ForgotPasswordActivity extends BindeableActivity{

    public ForgotPasswordViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    @Override public void initDataBinding()
    {
        ActivityForgotPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        viewModel = new ForgotPasswordViewModel(this);
        binding.setViewModel(viewModel);
    }


    @Override public void update(Observable observable, Object o)
    {
        if(observable instanceof ForgotPasswordViewModel)
        {
            ForgotPasswordViewModel viewModel = (ForgotPasswordViewModel) observable;
        }
    }
}
