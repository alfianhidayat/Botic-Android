package com.example.amrizalns.botic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amrizalns.botic.R;

public class checkin_1 extends Fragment {

    private Button daftar_pj;
    private EditText mNamaPJ, mNohp, mJumlahTamu;
    private CheckBox aggrement;
    View view;

    public checkin_1() {
    }

    public static checkin_1 newInstance(String param1, String param2) {
        checkin_1 fragment = new checkin_1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_checkin_1, container, false);

        mNamaPJ = (EditText) view.findViewById(R.id.field_nama_pj);
        mNohp = (EditText) view.findViewById(R.id.field_notelp);
        mJumlahTamu = (EditText) view.findViewById(R.id.field_jumlah_tamu);

        mJumlahTamu.addTextChangedListener(mTextWatcher);
        aggrement = (CheckBox) view.findViewById(R.id.check_pj);

        daftar_pj = (Button) view.findViewById(R.id.btn_checkin_pj);
        daftar_pj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifikasiDaftar();
            }
        });

        return view;
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (Integer.parseInt(s.toString()) <= 10 && Integer.parseInt(s.toString()) > 0){
                daftar_pj.setEnabled(true);
            }else{
                daftar_pj.setEnabled(false);
                Toast.makeText(getActivity(), "Maksimal 10", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void VerifikasiDaftar() {
        String namapj = mNamaPJ.getText().toString();
        String noHp = mNohp.getText().toString();
        String jmlTamu = mJumlahTamu.getText().toString();
        if (namapj.length() == 0 && noHp.length() == 0 && jmlTamu.length() == 0) {
            mNamaPJ.setError("Wajib Diisi!");
            mNohp.setError("Wajib Diisi!");
            mJumlahTamu.setError("Wajib Diisi!");
        } else if (!aggrement.isChecked()) {
            Toast.makeText(getActivity(), "Harus Dicentang!", Toast.LENGTH_SHORT).show();
        } else {
            String jumlah = mJumlahTamu.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("daftar", jumlah);

            checkin_2 check_2 = new checkin_2();
            check_2.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_container, check_2);
            fragmentTransaction.commit();
        }
    }
}
