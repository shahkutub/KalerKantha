package com.kalerkantho;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.kalerkantho.Fragment.CommunicatorFragmentInterface;
import com.kalerkantho.Fragment.DetailsFragment;

public class DetailsActivity extends AppCompatActivity implements CommunicatorFragmentInterface {
    private Context con;
    FragmentTransaction mFragmentTransaction;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        con = this;

        mFragmentManager = getSupportFragmentManager();
       DetailsFragment dtf = new DetailsFragment();
        /*  mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.contain, dtf).commit();*/
        setContentFragment(dtf,false);
    }

    @Override
    public void setContentFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment == null) {
            return;
        }
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.contain);

        if (currentFragment != null && fragment.getClass().isAssignableFrom(currentFragment.getClass())) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contain, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }

    @Override
    public void addContentFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment == null) {
            return;
        }
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.contain);

        if (currentFragment != null && fragment.getClass().isAssignableFrom(currentFragment.getClass())) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contain, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
