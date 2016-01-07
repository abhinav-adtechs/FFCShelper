package app.hackdev.jordiie.ffcshelper.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.hackdev.jordiie.ffcshelper.R;

/**
 * Created by jordiie on 7/1/16.
 */
public class CreditsRegistered extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_credits_registered, container, false) ;
    }
}
