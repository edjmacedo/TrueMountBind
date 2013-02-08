package com.truemount.bind;

import java.io.File;

import com.truemount.bind.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity implements OnClickListener {

    private static final int REQUEST_PICK_FILE = 1;	

    private EditText mFilePathEditText;
    private EditText mFilePathEditTextDestination;
    private Button mStartActivityButton;
    private Button mStartActivityButtonDestination;        
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Set the views
        mFilePathEditText = (EditText)findViewById(R.id.file_path_text_view);
        mStartActivityButton = (Button)findViewById(R.id.start_file_picker_button);
        mFilePathEditTextDestination = (EditText)findViewById(R.id.file_path_text_view_destination);
        mStartActivityButtonDestination = (Button)findViewById(R.id.start_file_picker_button_destination);

        mStartActivityButton.setOnClickListener(this);
        mStartActivityButtonDestination.setOnClickListener(this);
    }        
            
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.start_file_picker_button:
            // Create a new Intent for the file picker activity
            Intent intent = new Intent(this, FilePickerActivity.class);

            // Set the initial directory to be the sdcard
            //intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH, Environment.getExternalStorageDirectory());

            // Show hidden files
            //intent.putExtra(FilePickerActivity.EXTRA_SHOW_HIDDEN_FILES, true);

            // Only make .png files visible
            //ArrayList<String> extensions = new ArrayList<String>();
            //extensions.add(".png");
            //intent.putExtra(FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS, extensions);

            // Start the activity
            startActivityForResult(intent, REQUEST_PICK_FILE);            
            break;
        case R.id.start_file_picker_button_destination:
        	
        	Intent intentDestination = new Intent(this, FilePickerActivityDestination.class);
        	
        	startActivityForResult(intentDestination, REQUEST_PICK_FILE);
        	break;
        }
    }        

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            switch(requestCode) {
            case REQUEST_PICK_FILE:
                if(data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
                    // Get the file path
                    File f = new File(data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));

                    // Set the file path text view
                    mFilePathEditText.setText(f.getPath());                    
                }                                        
            }
        }else{
        	switch(requestCode) {
            case REQUEST_PICK_FILE:
            	if(data.hasExtra(FilePickerActivityDestination.EXTRA_FILE_PATH_DESTINATION)){
                	// Get the file path
                    File fd = new File(data.getStringExtra(FilePickerActivityDestination.EXTRA_FILE_PATH_DESTINATION));

                    // Set the file path text view                    
                    mFilePathEditTextDestination.setText(fd.getPath());
                }
        	}
        }
    }
}
