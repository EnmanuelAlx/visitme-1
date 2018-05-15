package visit.me.gil.mota.visitme.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.Observable;
import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.ActivityRegisterBinding;
import visit.me.gil.mota.visitme.viewModels.RegisterViewModel;

public class RegisterActivity extends BindeableActivity implements RegisterViewModel.SelectImage {
    private RegisterViewModel viewModel;
    private String image;
    private boolean edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edit = this.getIntent().getBooleanExtra("edit", false);
        initDataBinding();
        setupObserver(viewModel);
    }

    @Override
    public void update(Observable observable, Object o) {
        if(observable instanceof RegisterViewModel)
        {
            RegisterViewModel viewModel = (RegisterViewModel) observable;
        }
    }

    @Override
    public void initDataBinding() {
        ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = new RegisterViewModel(this,this, edit);
        binding.setViewModel(viewModel);
    }

    @Override
    public void select() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(getIntent, "Selecciona una Imagen de Perfil");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, 222);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 222 && resultCode == Activity.RESULT_OK) {
            Uri u = data.getData();
            if (u != null) {
                image = u.getPath();
                viewModel.changeImage(u);
            }
        }
    }

}
