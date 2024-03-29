package com.bojonegorotic.amrizalns.botic.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.SpinnerItemAdapter;
import com.bojonegorotic.amrizalns.botic.model.Booking;
import com.bojonegorotic.amrizalns.botic.utils.SessionLogin;
import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.models.Asset;
import com.botic.coreapps.models.IdentityType;
import com.botic.coreapps.networks.RetrofitApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class booking_gedung extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static String SER_KEY = "com.bojonegorotic.amrizalns.botic.model.Booking";
    private EditText mNoIdentias, mNoHP, mDescGedung, mName;
    private Spinner mJenisGedung, mJenisIdentitas;
    private RadioGroup mRadioGroupWaktu;
    private RadioButton mRadioButton;
    private TextView mdates, mterm;
    private Button next;
    private String mItemJenisGedung, mItemJenisIdentitas, mDate;
    String mWaktu = "Siang - Malam ( 24 Jam )";
    ProgressDialog dialog;
    private CheckBox aggrement;

    List<String> spListGedung = new ArrayList<>();
    List<String> spListIdentity = new ArrayList<>();
    SpinnerItemAdapter adapterGedung;
    SpinnerItemAdapter adapterIdentity;
    List<Asset> assets = new ArrayList<>();
    List<IdentityType> identityTypes = new ArrayList<>();
    int selectedItemAsset;
    int selectedItemIdentity;
    String selectAsset, selectIdentity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_gedung);

        mNoIdentias = (EditText) findViewById(R.id.field_no_identitas);
        mName = (EditText) findViewById(R.id.field_name);
        mNoHP = (EditText) findViewById(R.id.field_number_call);
        mDescGedung = (EditText) findViewById(R.id.field_desc_gedung);

        aggrement = (CheckBox) findViewById(R.id.check_gedung);

        mJenisGedung = (Spinner) findViewById(R.id.jenis_gedung);

        mJenisIdentitas = (Spinner) findViewById(R.id.jenis_identitas);

        mdates = (TextView) findViewById(R.id.txt_date);
        mdates.setText(getCurrentDate());

        adapterGedung = new SpinnerItemAdapter(this, R.layout.spinner_item_lis, spListGedung);
        mJenisGedung.setAdapter(adapterGedung);
        mJenisGedung.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemAsset = position;
                selectAsset = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedItemAsset = 0;
            }
        });


        adapterIdentity = new SpinnerItemAdapter(this, R.layout.spinner_item_lis, spListIdentity);
        mJenisIdentitas.setAdapter(adapterIdentity);
        mJenisIdentitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemIdentity = position;
                selectIdentity = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedItemIdentity = 0;
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        next = (Button) findViewById(R.id.btn_next_gedung);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = mName.getText().toString();
                String noIdentitas = mNoIdentias.getText().toString();
                String noHP = mNoHP.getText().toString();
                String desGed = mDescGedung.getText().toString();

                if (nama.isEmpty()  && noIdentitas.isEmpty() && noHP.isEmpty() && desGed.isEmpty()) {
                    mName.setError("Required!");
                    mNoIdentias.setError("Required!");
                    mNoHP.setError("Required!");
                    mDescGedung.setError("Required!");
                } else if (!nama.matches("[a-zA-Z]+")){
                    mName.setError("Nama Tidak Boleh Angka!");
                } else if (!isValidMobile(noHP)) {
                    mNoHP.setError("Nomer HP harus sesuai");
                } else if (!aggrement.isChecked()) {
                    aggrement.setError("Harus Dicentang!");
                } else {
                    int radioButtonID = mRadioGroupWaktu.getCheckedRadioButtonId();
                    View radioButton = mRadioGroupWaktu.findViewById(radioButtonID);
                    int idx = mRadioGroupWaktu.indexOfChild(radioButton) + 1;
                    RetrofitApi.getInstance(booking_gedung.this).getApiService(SessionLogin.getAccessToken())
                            .booking(identityTypes.get(selectedItemIdentity).getId(),
                                    mNoIdentias.getText().toString(),
                                    mName.getText().toString(),
                                    mNoHP.getText().toString(),
                                    mdates.getText().toString(),
                                    idx + "",
                                    mDescGedung.getText().toString(),
                                    assets.get(selectedItemAsset).getId(),
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
                                    Toast.makeText(booking_gedung.this, R.string.bookingberhasil, Toast.LENGTH_SHORT).show();
                                    BookingGedung();
                                }

                                @Override
                                protected void onError(String message) {
                                    super.onError(message);
                                    Toast.makeText(booking_gedung.this, R.string.bookinggagal, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                protected void onUnauthorized() {
                                    SessionLogin.reset();
                                    Intent intent = new Intent(booking_gedung.this, signIn.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });

        mRadioGroupWaktu = (RadioGroup) findViewById(R.id.rad_waktu_sewa);
        mRadioGroupWaktu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int selected = mRadioGroupWaktu.getCheckedRadioButtonId();
                mRadioButton = (RadioButton) findViewById(selected);
                mWaktu = mRadioButton.getText().toString();
                getListAsset();
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
        getListIdentity();
        getListAsset();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private void getListAsset() {
        int radioButtonID = mRadioGroupWaktu.getCheckedRadioButtonId();
        View radioButton = mRadioGroupWaktu.findViewById(radioButtonID);
        int idx = mRadioGroupWaktu.indexOfChild(radioButton) + 1;
        RetrofitApi.getInstance(this).getApiService(SessionLogin.getAccessToken()).getListAsset(idx, mdates.getText().toString())
                .enqueue(new PageCallback<List<Asset>>(booking_gedung.this) {
                    @Override
                    protected void onStart() {

                    }

                    @Override
                    protected void onFinish() {

                    }

                    @Override
                    protected void onSuccess(List<Asset> data) {
                        spListGedung.clear();
                        assets.clear();
                        assets.addAll(data);
                        for (Asset asset : data) {
                            spListGedung.add(asset.getName());
                        }
                        adapterGedung.notifyDataSetChanged();
                    }

                    @Override
                    protected void onError(String message) {
                        super.onError(message);
                    }

                    @Override
                    protected void onUnauthorized() {
                        SessionLogin.reset();
                        Intent intent = new Intent(booking_gedung.this, signIn.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
    }

    private void getListIdentity() {
        RetrofitApi.getInstance(this).getApiService(SessionLogin.getAccessToken()).getListIdentity()
                .enqueue(new PageCallback<List<IdentityType>>(booking_gedung.this) {
                    @Override
                    protected void onStart() {

                    }

                    @Override
                    protected void onFinish() {

                    }

                    @Override
                    protected void onSuccess(List<IdentityType> data) {
                        spListIdentity.clear();
                        identityTypes.clear();
                        identityTypes.addAll(data);
                        for (IdentityType dt : data) {
                            spListIdentity.add(dt.getType());
                        }
                        adapterIdentity.notifyDataSetChanged();
                    }

                    @Override
                    protected void onError(String message) {
                        super.onError(message);
                    }

                    @Override
                    protected void onUnauthorized() {
                        SessionLogin.reset();
                        Intent intent = new Intent(booking_gedung.this, signIn.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
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
        mBooking.setJenisGedung(assets.get(selectedItemAsset).getName());
        mBooking.setJenisIdentitas(String.valueOf(mItemJenisIdentitas));
        mBooking.setDatePick(String.valueOf(mDate));
        mBooking.setWaktu(String.valueOf(mWaktu));
        Intent intent = new Intent(booking_gedung.this, booking_gedung_finish.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY, mBooking);
        intent.putExtras(mBundle);
        startActivity(intent);
    }

    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    public void setDate(final Calendar calendar) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        final DateFormat dateFormat = DateFormat.getDateInstance();
        (mdates = (TextView) findViewById(R.id.txt_date)).setText(simpleFormat.format(calendar.getTime()));
        mDate = mdates.getText().toString();
        getListAsset();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate(cal);
        view.setMinDate(System.currentTimeMillis() - 1000);

    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return dialog;
        }

    }

    public String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatdate = sdf.format(c.getTime());
        mDate = formatdate;
        return formatdate;
    }
}
