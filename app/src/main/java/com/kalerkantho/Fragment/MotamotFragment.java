package com.kalerkantho.Fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.kalerkantho.R;
import com.kalerkantho.Utils.LoginDialogFragment;


public class MotamotFragment extends Fragment {
    private Context con;
    private TextView motamotTitle,motatmotBtn,motamotThanks;
    private EditText subjectEditText,detailsEditText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.motamot,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();
        intiU();
    }

    private void intiU() {

        final Typeface face_reg = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_reg.ttf");
        final Typeface face_bold = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_Bold.ttf");

        motamotTitle = (TextView) getView().findViewById(R.id.motamotTitle);
        subjectEditText = (EditText) getView().findViewById(R.id.subjectEditText);
        detailsEditText = (EditText) getView().findViewById(R.id.detailsEditText);
        motatmotBtn = (TextView) getView().findViewById(R.id.motatmotBtn);
        motamotThanks = (TextView) getView().findViewById(R.id.motamotThanks);

        motamotTitle.setTypeface(face_bold);
        subjectEditText.setTypeface(face_reg);
        detailsEditText.setTypeface(face_reg);
        motatmotBtn.setTypeface(face_bold);
        motamotThanks.setTypeface(face_reg);

        motatmotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialogFragment dialogMenu= new LoginDialogFragment();
                dialogMenu.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
                dialogMenu.show(getActivity().getFragmentManager(), "");
            }
        });

    }
}
