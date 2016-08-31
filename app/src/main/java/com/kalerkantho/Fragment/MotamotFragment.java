package com.kalerkantho.Fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.aapbd.utils.storage.PersistData;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AlertMessage;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.LoginDialogFragment;


public class MotamotFragment extends Fragment {
    private Context con;
    private TextView motamotTitle,motatmotBtn,motamotThanks;
    private EditText subjectEditText,detailsEditText;
private Typeface face_reg;

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

        face_reg = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_reg.ttf");
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
                if (TextUtils.isEmpty(PersistData.getStringData(con, AppConstant.email))){
                    loginDialoag(con);
//                    AlertMessage.showMessage(con,getResources().getString(R.string.status),getResources().getString(R.string.login_first));
                }else {

                }
            }
        });

    }

    private   void loginDialoag(final Context con) {
        final Dialog dialogLogin = new Dialog(con);
        dialogLogin.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLogin.setContentView(R.layout.dialog_login);
        dialogLogin.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialogLogin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView tvTitel2 = (TextView) dialogLogin.findViewById(R.id.tvTitel2);
        TextView tvDescription2 = (TextView) dialogLogin.findViewById(R.id.tvDescription2);
        TextView tvLeftCommund = (TextView) dialogLogin.findViewById(R.id.tvLeftCommund);
        TextView tvRightCommund = (TextView) dialogLogin.findViewById(R.id.tvRightCommund);

        //==================Font set==========================

        tvTitel2.setTypeface(face_reg);
        tvLeftCommund.setTypeface(face_reg);
        tvRightCommund.setTypeface(face_reg);
        tvDescription2.setTypeface(face_reg);


        tvLeftCommund.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LoginDialogFragment dialogMenu = new LoginDialogFragment();
                dialogMenu.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
                dialogMenu.show(getActivity().getFragmentManager(), "");
            }
        });

        tvRightCommund.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogLogin.dismiss();
            }
        });

        dialogLogin.show();
    }
}
