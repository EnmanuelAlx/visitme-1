package visit.me.gil.mota.visitme.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.List;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.models.Alert;
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.views.adapters.IntervalAdapter;

/**
 * Created by mota on 29/4/2018.
 */

public class IntervalsDialog {
    private Context context;
    private Result result;
    private List<Interval> intervals;
    private IntervalAdapter adapter;
    public IntervalsDialog(Context contxt, Result result, List<Interval> intervals) {
        this.context = contxt;
        this.result = result;
        this.intervals = intervals;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.intervals_dialog, null);
        setupAdapter(view);
        builder.setView(view);
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        final AlertDialog d = builder.create();

        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (validateIntervals()) {
                            result.onClose(intervals);
                            d.dismiss();
                        }

                    }
                });
            }
        });

        d.show();
    }

    private void setupAdapter(View view) {
        RecyclerView recyler = view.findViewById(R.id.intervals);
        adapter = new IntervalAdapter(IntervalAdapter.EDITABLE_TYPE);
        adapter.setList(intervals);
        adapter.setHasStableIds(true);
        recyler.setAdapter(adapter);
        recyler.setLayoutManager(new LinearLayoutManager(context));
    }

    private boolean validateIntervals() {
        return true;
    }


    public interface Result {
        void onClose(List<Interval> intervals);
    }

}
