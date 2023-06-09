package com.example.physio_plus_app.R1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.physio_plus_app.R;
import com.example.physio_plus_app.Utils.HttpHandler.PSF.PSFRegisterCenterHandler;


public class R1 extends AppCompatActivity {
    private Button r1Button;
    private EditText namePhysioEditText;
    private EditText addressPhysioEditText;
    private EditText afmPhysioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r1_activity);

        r1Button = findViewById(R.id.r1_button);
        namePhysioEditText = findViewById(R.id.namePhysio);
        addressPhysioEditText = findViewById(R.id.addressPhysio);
        afmPhysioEditText = findViewById(R.id.amka_LG);

        //Topbar
        ImageView goBackButton = findViewById(R.id.back_button);
        goBackButton.setOnClickListener(v -> finish());

        r1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namePhysio = namePhysioEditText.getText().toString();
                String addressPhysio = addressPhysioEditText.getText().toString();
                String afmPhysio = afmPhysioEditText.getText().toString();

                // Call the psfCreate method with the obtained data
                try {
                    String response = PSFRegisterCenterHandler.request(namePhysio, addressPhysio, afmPhysio);//R1HttpHandler.psfCreate(namePhysio, addressPhysio, afmPhysio);

                    if (response.equals("Tax ID number already exists")) {
                        Toast.makeText(R1.this, "Tax ID number already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(R1.this, "Data written to database", Toast.LENGTH_SHORT).show();

                        // Clear the EditText fields
                        namePhysioEditText.getText().clear();
                        addressPhysioEditText.getText().clear();
                        afmPhysioEditText.getText().clear();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(R1.this, "Error writing data to database", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    public void backButtonR1(View view) {
        finish();
    }
}