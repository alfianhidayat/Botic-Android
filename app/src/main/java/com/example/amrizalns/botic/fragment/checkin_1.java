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

import com.botic.coreapps.models.CheckInParams;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.utils.SessionLogin;

public class checkin_1 extends Fragment {

    private Button daftar_pj;
    private EditText mNamaPJ, mNohp, mJumlahTamu;
    private CheckBox aggrement;
    View view;

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

        mNamaPJ.setText(SessionLogin.getProfile().getName());

        return view;
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().isEmpty())
                if (Integer.parseInt(s.toString()) <= 10 && Integer.parseInt(s.toString()) > 0) {
                    daftar_pj.setEnabled(true);
                } else {
                    daftar_pj.setEnabled(false);
                    Toast.makeText(getActivity(), "Maksimal 10", Toast.LENGTH_SHORT).show();
                }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void VerifikasiDaftar() {
        final String names = mNamaPJ.getText().toString();
        if (names.length() == 0) {
            mNamaPJ.requestFocus();
            mNamaPJ.setError("Wajib Diisi");
        } else if (!names.matches("[a-zA-Z ]+")) {
            mNamaPJ.requestFocus();
            mNamaPJ.setError("Harus Huruf!");
        }

        if (!aggrement.isChecked()) {
            mNamaPJ.setError("Wajib Diisi!");
            mNohp.setError("Wajib Diisi!");
            mJumlahTamu.setError("Wajib Diisi!");
            aggrement.setError("Harus Dicentang!");
        } else {
            CheckInParams params = new CheckInParams();
            params.setPhone(mNohp.getText().toString());
            params.setLong_visit(1);
            try {
                int visitor = Integer.parseInt(mJumlahTamu.getText().toString());
                params.setVisitor_number(visitor);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            SessionLogin.saveCheckIn(params);
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
