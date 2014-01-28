package com.codepath.gridimagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	
	private Spinner spinnerItemSize, spinnerColor, spinnerItemType;
	private EditText siteFilter;
	private Settings itemSettings;
	
	private String urlFilterText = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		spinnerItemSize = (Spinner) findViewById(R.id.spinnerImgSize);
		spinnerColor = (Spinner) findViewById(R.id.spinnerColorFilter);
		spinnerItemType = (Spinner) findViewById(R.id.spinnerImgType);
		siteFilter = (EditText) findViewById(R.id.etSIteFilterInput);
		
		itemSettings = (Settings) getIntent().getSerializableExtra("settings");
		if (itemSettings != null){
			setSpinnerToValue(spinnerItemSize, itemSettings.getItemSize());
			setSpinnerToValue(spinnerColor, itemSettings.getColorFilter());
			setSpinnerToValue(spinnerItemType, itemSettings.getItemType());
			siteFilter.setText(urlFilterText);
		}
		else {
			spinnerItemSize.setSelection(0);
			spinnerColor.setSelection(0);
			spinnerItemType.setSelection(0);
			siteFilter.setText("");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void onSaveSettings (View v){
		String sis= spinnerItemSize.getSelectedItem().toString();
		String sc = spinnerColor.getSelectedItem().toString();
		String sit= spinnerItemType.getSelectedItem().toString();
		String sf = siteFilter.getText().toString();	
		Settings s = new Settings(sis, sc, sit, sf);
		
		Intent data = new Intent();
		data.putExtra("settings", s);
		
		setResult(RESULT_OK, data); 
		finish();
	}
	
	public void onCancel (View v){
		setResult(RESULT_CANCELED);
		finish();
	}
	
	public void setSpinnerToValue(Spinner spinner, String value) {
	    int index = 0;
	    SpinnerAdapter adapter = spinner.getAdapter();
	    for (int i = 0; i < adapter.getCount(); i++) {
	        if (adapter.getItem(i).equals(value)) {
	            index = i;
	        }
	    }
	    spinner.setSelection(index);
	}

}
