package es.dpinfo.recuapp.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import es.dpinfo.recuapp.AccountPreferences;
import es.dpinfo.recuapp.R;

/**
 * Created by dprimenko on 2/02/17.
 */
public class ConfigFragment extends Fragment {

    private TextView txvTitle;
    private Button btnSubmit;
    private CheckBox chkSports, chkTech, chkHome, chkImportant;
    private ConfigFragmentListener mCallback;

    public interface ConfigFragmentListener {
        void onListProductFragment();
    }

    public static ConfigFragment newInstance() {
        return new ConfigFragment();
    }

    public ConfigFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (ConfigFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ConfigFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View rootView = view;

        txvTitle = (TextView) rootView.findViewById(R.id.txv_config_title);
        chkSports = (CheckBox) rootView.findViewById(R.id.chk_config_sports);
        chkTech = (CheckBox) rootView.findViewById(R.id.chk_config_tech);
        chkHome = (CheckBox) rootView.findViewById(R.id.chk_config_home);
        chkImportant = (CheckBox) rootView.findViewById(R.id.chk_config_important);
        btnSubmit = (Button) rootView.findViewById(R.id.btn_submit);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "hallowen.ttf");
        txvTitle.setTypeface(typeface);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountPreferences.getInstance(getActivity()).putSportsConfig(chkSports.isChecked());
                AccountPreferences.getInstance(getActivity()).putTechConfig(chkTech.isChecked());
                AccountPreferences.getInstance(getActivity()).putHomeConfig(chkHome.isChecked());
                AccountPreferences.getInstance(getActivity()).putImportantConfig(chkImportant.isChecked());
                mCallback.onListProductFragment();
            }
        });
    }


}
