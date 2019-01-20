package visit.me.gil.mota.visitme.views.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.models.Visit;

/**
 * Created by mota on 17/4/2018.
 */

public class VisitDialog {
    private Context context;
    private AlertDialog dialog;
    private Visit visit;


    public VisitDialog(Context contxt, Visit visit) {
        this.context = contxt;
        this.visit = visit;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View viewInflated = LayoutInflater.from(context).inflate(R.layout.visit_dialog, null);

        builder.setView(viewInflated);

        fillView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void fillView(View view) {
        ImageView image = view.findViewById(R.id.image);
        Glide.with(context)
                .load(visit.getGuest().getImage())
                .dontAnimate()
                .placeholder(R.drawable.guy)
                .error(R.drawable.guy)
                .into(image);
        TextView name = view.findViewById(R.id.name);
        name.setText(visit.getGuest().getName());
        TextView identification = view.findViewById(R.id.identification);
        identification.setText(visit.getGuest().getIdentification());

        TextView actualTime = view.findViewById(R.id.time_actual);
        actualTime.setText(visit.getDayOfVisit());
        TextView communityActual = view.findViewById(R.id.community_actual);
        communityActual.setText(visit.getCommunity().getName());
        TextView actualType = view.findViewById(R.id.type_actual);
        actualType.setText(visit.getKindString());
        TextView token = view.findViewById(R.id.token);
        token.setText(visit.getToken());
        View tokenAccess = view.findViewById(R.id.tokenAccess);
        tokenAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Con Este Token puedes, visitarme");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, visit.getToken());
                context.startActivity(Intent.createChooser(sharingIntent, "Token de Visita"));
            }
        });
    }


}
