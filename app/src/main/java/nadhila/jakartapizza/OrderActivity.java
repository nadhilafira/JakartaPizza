package nadhila.jakartapizza;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    EditText mTextNama, mTextAlamat, mNumberPhone;
    TextView mTextHarga, mTextQty;
    Button mButtonOrder, mButtonPlus, mButtonMin;
    RadioGroup mRadioGroup;
    RadioButton mRadioItem1, mRadioItem2;
    String item = null;
    Context mContext;
    Spinner mSpinnerMenu, mSpinnerCrust;
    List<String> mListMenu = new ArrayList<>();
    List<String> mListCrust = new ArrayList<>();//membuat array list
    double harga = 0;
    int qty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = getApplicationContext();
        mTextNama = (EditText) findViewById(R.id.mTextNama);
        mTextNama.setTextColor(Color.WHITE);
        mTextAlamat = (EditText) findViewById(R.id.mTextAlamat);
        mTextAlamat.setTextColor(Color.WHITE);
        mNumberPhone = (EditText) findViewById(R.id.mNumberPhone);
        mNumberPhone.setTextColor(Color.WHITE);
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mTextQty = (TextView) findViewById(R.id.mTextQty);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mButtonMin = (Button) findViewById(R.id.mButtonMin);
        // radio
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioItem1 = (RadioButton) findViewById(R.id.mRadioItem1);
        mRadioItem2 = (RadioButton) findViewById(R.id.mRadioItem2);
        // spinner
        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mListMenu.add("BBQ Chicken");
        mListMenu.add("Cheese");
        mListMenu.add("Eggs Florentine");
        mListMenu.add("Grilled Shrimp");
        mListMenu.add("Pepperoni");//memasukan info ke spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdapter);
        mSpinnerMenu.setBackgroundColor(Color.WHITE);
        mSpinnerCrust = (Spinner) findViewById(R.id.mSpinnerCrust);
        mListCrust.add("Cheese Burst");
        mListCrust.add("Fresh-Pan-Pizza");
        mListCrust.add("Hand-Tossed");
        mListCrust.add("Wheat-Thin-Crust");//memasukan info ke spinner
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListCrust);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCrust.setAdapter(dataAdapter);
        mSpinnerCrust.setBackgroundColor(Color.WHITE);
        // spinner crust
    }

    public void onClickOrder(View view) { //hanya memilih salah satu radiobox dan mendapatkan text untuk item
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.mRadioItem1:
                item = mRadioItem1.getText().toString();
                break;
            case R.id.mRadioItem2:
                item = mRadioItem2.getText().toString();
                break;
        }

        String to = "nadhilafira@gmail.com";
        String subject = mTextNama.getText().toString();
        String message = "I'd like to order "
                + mTextQty.getText()
                + " pan/pans of "
                + mSpinnerMenu.getSelectedItem()
                + " Pizza with "
                + mSpinnerCrust.getSelectedItem()
                + " Crust and extra topping of "
                + item
                + ". My address is "
                + mTextAlamat.getText()
                + " and my phone number is "
                + mNumberPhone.getText()
                + ". \nCurrent Price: "
                + mTextHarga.getText()
                + " \n"
                + "Thank You.";

        Intent email = new Intent(Intent.ACTION_SEND); //order email
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Kirim email dengan"));
    }

    public void onClickPlus(View view) {
        harga = harga + 7.50; //logic tombol >
        qty = qty + 1;
        mTextQty.setText(qty + "");
        mTextHarga.setText("$" + harga);
    }

    public void onClickMin(View view) { //logic button <
        if (harga != 0) {
            harga = harga - 7.50;
            qty = qty - 1;
            mTextQty.setText(qty + "");
            mTextHarga.setText("$" + harga);
        } else {
            harga = 0;
            qty = 0;
            mTextQty.setText(qty + "");
            mTextHarga.setText("$" + harga);
        }
    }

    public void onClickReset(View v) {
        harga = 0; //logic reset button
        qty = 0;
        mTextNama.setText("");
        mTextAlamat.setText("");
        mNumberPhone.setText("");
        mTextHarga.setText("$" + harga);
        mTextQty.setText(qty + "");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
