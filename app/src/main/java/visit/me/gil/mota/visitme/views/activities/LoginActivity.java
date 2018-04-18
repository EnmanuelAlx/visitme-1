package visit.me.gil.mota.visitme.views.activities;

import android.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.ActivityLoginBinding;
import visit.me.gil.mota.visitme.viewModels.LoginViewModel;

public class LoginActivity extends BindeableActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupObserver(loginViewModel);
    }

    @Override public void initDataBinding()
    {
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        binding.setViewModel(loginViewModel);
    }


    @Override public void update(Observable observable, Object o)
    {
        if(observable instanceof LoginViewModel)
        {
            LoginViewModel viewModel = (LoginViewModel) observable;
        }
    }
}
