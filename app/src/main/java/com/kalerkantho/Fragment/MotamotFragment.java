package com.kalerkantho.Fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalerkantho.R;
import com.kalerkantho.Utils.LoginDialogFragment;

;


public class MotamotFragment extends Fragment {
    private Context con;
    private TextView tvComment;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return view=inflater.inflate(R.layout.motamot,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();
        intiU();
    }

    private void intiU() {
        tvComment=(TextView) view.findViewById(R.id.tvComment);

        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
//                dialogMenu.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        tvSPCommCount.setText(AppConstant.commentCount);
//                        if (!PersistentUser.isLogged(con)) {
//                            callProductCommentListAPI(con, AppConstant.productIDfeedRow, "demo");
//                        } else {
//                            callProductCommentListAPI(con, AppConstant.productIDfeedRow, "all");
//                        }
//                    }
//                });
                loginDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
                loginDialogFragment.show(getActivity().getFragmentManager(), "");
            }
        });

    }
}
