package visit.me.gil.mota.visitme.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import visit.me.gil.mota.visitme.R;

public class WaitForApproveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_approve);
    }

    @Override
    public void onBackPressed() {
        //BLOCKED
    }
}
