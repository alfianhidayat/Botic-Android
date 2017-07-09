package com.example.amrizalns.botic.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.networks.RetrofitApi;
import com.botic.coreapps.responses.BaseResponse;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.model.Booking;
import com.example.amrizalns.botic.utils.SessionLogin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class booking_gedung extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    public static String SER_KEY = "com.example.amrizalns.botic.model.Booking";
    private EditText mNoIdentias, mNoHP, mDescGedung, mName;
    private Spinner mJenisGedung, mJenisIdentitas;
    private RadioGroup mRadioGroupWaktu;
    private RadioButton mRadioButton;
    private TextView mdates, mterm;
    private Button next;
    private String mItemJenisGedung, mItemJenisIdentitas, mWaktu, mDate;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_gedung);

        mNoIdentias = (EditText)findViewById(R.id.field_no_identitas);
        mName = (EditText) findViewById(R.id.field_name);
        mNoHP = (EditText)findViewById(R.id.field_number_call);
        mDescGedung = (EditText)findViewById(R.id.field_desc_gedung);

        mJenisGedung = (Spinner) findViewById(R.id.jenis_gedung);
        mJenisGedung.setOnItemSelectedListener(this);

        mJenisIdentitas = (Spinner) findViewById(R.id.jenis_identitas);
        mJenisIdentitas.setOnItemSelectedListener(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.array_gedung, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        mJenisGedung.setAdapter(adapter);

        ArrayAdapter a = ArrayAdapter.createFromResource(this, R.array.array_identitas, R.layout.spinner_item);
        a.setDropDownViewResource(R.layout.spinner_dropdown);
        mJenisIdentitas.setAdapter(a);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);


        next = (Button) findViewById(R.id.btn_next_gedung);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = mRadioGroupWaktu.getCheckedRadioButtonId();
                View radioButton = mRadioGroupWaktu.findViewById(radioButtonID);
                int idx = mRadioGroupWaktu.indexOfChild(radioButton) + 1;
                RetrofitApi.getInstance().getApiService(SessionLogin.getAccessToken())
                        .booking(mJenisIdentitas.getSelectedItemPosition() + 1,
                                mNoIdentias.getText().toString(),
                                "Alfian Testing",
                                mNoHP.getText().toString(),
                                mdates.getText().toString(),
                                idx + "",
                                mDescGedung.getText().toString(),
                                2,
                                29)
                        .enqueue(new PageCallback<Object>(booking_gedung.this) {
                            @Override
                            protected void onStart() {
                                dialog.show();
                            }

                            @Override
                            protected void onFinish() {
                                dialog.dismiss();
                            }

                            @Override
                            protected void onSuccess(Object data) {
                                Toast.makeText(booking_gedung.this, "Booking Berhasil", Toast.LENGTH_SHORT).show();
                                BookingGedung();
                            }

                            @Override
                            protected void onError(String message) {
                                super.onError(message);
                                Toast.makeText(booking_gedung.this, "Booking Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        mRadioGroupWaktu = (RadioGroup) findViewById(R.id.rad_waktu_sewa);
        mRadioGroupWaktu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int selected = mRadioGroupWaktu.getCheckedRadioButtonId();
                mRadioButton = (RadioButton) findViewById(selected);
                mWaktu = mRadioButton.getText().toString();
            }
        });

        mterm = (TextView) findViewById(R.id.term);
        mterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(booking_gedung.this, popUp_sk_gedung.class);
                startActivity(intent);
            }
        });
    }

    private void BookingGedung() {
        Booking mBooking = new Booking();
        mBooking.setNoIdentitas(mNoIdentias.getText().toString());
        mBooking.setNama(mName.getText().toString());
        mBooking.setNoHP(mNoHP.getText().toString());
        mBooking.setDesGedung(mDescGedung.getText().toString());
        mBooking.setJenisGedung(String.valueOf(mItemJenisGedung));
        mBooking.setJenisIdentitas(String.valueOf(mItemJenisIdentitas));
        mBooking.setDatePick(String.valueOf(mDate));
        mBooking.setWaktu(String.valueOf(mWaktu));
        Intent intent = new Intent(booking_gedung.this, booking_gedung_finish.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY, mBooking);
        intent.putExtras(mBundle);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mItemJenisGedung = mJenisGedung.getSelectedItem().toString();
        mItemJenisIdentitas = mJenisIdentitas.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    public void setDate(final Calendar calendar) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        (mdates = (TextView) findViewById(R.id.txt_date)).setText(simpleFormat.format(calendar.getTime()));
        mDate = mdates.getText().toString();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate(cal);
    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int mount = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, mount, day);
        }

    }

}
