package com.sterlitepower.vendorcom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

public class ComplianceForm extends AppCompatActivity {

    Button comp1_file_sel,comp2_file_sel;
    CheckBox applicable, not_applicable;
    FrameLayout form_status, complaince_list_frame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compliance_form);
        form_status=findViewById(R.id.app_not_app);
        applicable=findViewById(R.id.comp_status_app);
        not_applicable=findViewById(R.id.comp_status_not_app);
        complaince_list_frame=findViewById(R.id.comp_list_form);
        comp1_file_sel=findViewById(R.id.comp1_file_btn);
        comp2_file_sel=findViewById(R.id.comp2_file_btn);

        applicable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    complaince_list_frame.setVisibility(View.VISIBLE);
                    if(not_applicable.isChecked()){
                        not_applicable.toggle();
                    }
                }
            }
        });
        not_applicable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    complaince_list_frame.setVisibility(View.GONE);
                    if (applicable.isChecked()){
                        applicable.toggle();
                    }
                }
            }
        });
    }
}
