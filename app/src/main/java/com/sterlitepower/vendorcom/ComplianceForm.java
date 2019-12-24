package com.sterlitepower.vendorcom;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ComplianceForm extends AppCompatActivity {

    Map<String, String> paths=new HashMap<>();
    Map<String, Boolean> compliance_state=new HashMap<>();

    Button comp1_file_sel,comp2_file_sel;
    CheckBox applicable, not_applicable;
    FrameLayout form_status, complaince_list_frame;
    TextView comp1_pathview,comp2_pathview;
    CheckBox comp1_status, comp2_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compliance_form);
        form_status=findViewById(R.id.app_not_app);
        comp1_pathview=findViewById(R.id.comp1_path);
        comp2_pathview=findViewById(R.id.comp2_path);
        applicable=findViewById(R.id.comp_status_app);
        not_applicable=findViewById(R.id.comp_status_not_app);
        complaince_list_frame=findViewById(R.id.comp_list_form);
        comp1_file_sel=findViewById(R.id.comp1_file_btn);
        comp2_file_sel=findViewById(R.id.comp2_file_btn);
        comp1_status=findViewById(R.id.comp1_status);
        comp2_status=findViewById(R.id.comp2_status);

        applicable.toggle();

        applicable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    complaince_list_frame.setVisibility(View.VISIBLE);
                    not_applicable.toggle();
                }
                else if(!(not_applicable.isChecked())){
                    not_applicable.toggle();
                    complaince_list_frame.setVisibility(View.GONE);
                    applicable.toggle();
                }
            }
        });
        not_applicable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    applicable.toggle();
                    complaince_list_frame.setVisibility(View.GONE);

                }
                else if(!(applicable.isChecked())){
                    applicable.toggle();
                    complaince_list_frame.setVisibility(View.VISIBLE);
                    not_applicable.toggle();
                }
            }
        });
        comp1_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    comp1_file_sel.setEnabled(false);
                    comp1_pathview.setText("");
                }
                else {
                    if(paths.containsKey("1")){ comp1_pathview.setText(paths.get("1"));}
                    comp1_file_sel.setEnabled(true);
                }
            }
        });
        comp2_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    comp2_file_sel.setEnabled(false);
                    comp2_pathview.setText("");
                }
                else {
                    if(paths.containsKey("1")){ comp2_pathview.setText(paths.get("2"));}
                    comp2_file_sel.setEnabled(true);
                }
            }
        });
        comp1_file_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadfile("1");
            }
        });
        comp2_file_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadfile("2");
            }
        });
    }
    public int PICK_FILE_REQUEST=12;
    private void loadfile(final String complaine_name){
        new ChooserDialog(ComplianceForm.this)
                .withStartFile("*/")
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        //Toast.makeText(ComplianceForm.this, "FILE: " + path, Toast.LENGTH_SHORT).show();
                        paths.put(complaine_name, path);
                        update_text_pathview(complaine_name);

                    }
                })
                // to handle the back key pressed or clicked outside the dialog:
                .withOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Log.d("CANCEL", "CANCEL");
                        dialog.cancel(); // MUST have
                    }
                })
                .build()
                .show();

    }

    private void update_text_pathview(String complaine_name) {
        switch (complaine_name){
            case "1":
                comp1_pathview.setText(paths.get("1"));
            case "2":
                comp2_pathview.setText(paths.get("2"));

        }
    }

}
