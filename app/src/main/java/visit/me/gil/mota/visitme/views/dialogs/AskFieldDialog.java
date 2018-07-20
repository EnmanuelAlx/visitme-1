package visit.me.gil.mota.visitme.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.models.Alert;
import visit.me.gil.mota.visitme.models.Community;

public class AskFieldDialog {
    private Context context;
    private Result result;
    private EditText field;
    private TextView fieldText;
    private String message;

    public AskFieldDialog(Context contxt, String message, Result result) {
        this.context = contxt;
        this.result = result;
        this.message = message;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View view = LayoutInflater.from(context).inflate(R.layout.ask_field_dialog, null);
        field = view.findViewById(R.id.field);
        fieldText = view.findViewById(R.id.fieldMessage);
        fieldText.setText(message);
        builder.setView(view);
        builder.setPositiveButton("ACEPTAR", null);
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
                        String fieldResult = field.getText().toString();
                        if (!fieldResult.isEmpty()) {

                            result.onFieldFilled(fieldResult);
                            d.dismiss();
                        }

                    }
                });
            }
        });

        d.show();
    }
    public interface Result {
        void onFieldFilled(String field);
    }
}




