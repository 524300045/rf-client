package com.wologic.ui;

import java.io.File;
import java.util.ArrayList;

import com.wologic.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SaveFileDialogActivity extends Activity {

	String DefaultFilePath;  
    String DefaultFileName;  
    ArrayList<File> FileList = new ArrayList<File>();  
    File FileNow;  
    String Ext;  
    private TextView tbBack;
    public final static int RESULT_CODE = 4;  
      
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.savefiledialog);  
          
        //获取参数  
        Intent intent = getIntent();  
        DefaultFilePath = intent.getStringExtra("DefaultFilePath");  
        DefaultFileName = intent.getStringExtra("DefaultFileName");  
        this.Ext = intent.getStringExtra("Ext");  
        tbBack = (TextView) findViewById(R.id.tvback);
        //Toast.makeText(this,DefaultFilePath + "," + DefaultFileName, Toast.LENGTH_LONG).show();  
        //Toast.makeText(this,(new File(DefaultFilePath)).toString(), Toast.LENGTH_LONG).show();  
        //  

		tbBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
          
        this.FileNow = new File(DefaultFilePath);  
        this.RefreshFileList();  
        //  
        EditText EditFileName = (EditText)findViewById(R.id.editFileName);  
        EditFileName.setText(DefaultFileName);  
        //设置ListView单击事件  
        ListView mListView = (ListView)findViewById(R.id.FileList);  
   
        mListView.setOnItemClickListener(new OnItemClickListener(){  
  
            @Override  
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {  
                if (FileList.get(arg2).isDirectory()){  
                    FileNow = FileList.get(arg2);  
                    RefreshFileList();  
                }  
                else{  
                    EditText EditFileName = (EditText)findViewById(R.id.editFileName);  
                    EditFileName.setText(FileList.get(arg2).getName());  
                }  
            }  
              
        });  
    }  
      
    protected void RefreshFileList(){  
        //将这些文件名加入listview  
        this.FileList.clear();  
        File[] TempFiles = this.FileNow.listFiles();  
        if (TempFiles != null){  
            for (int i = 0;i < TempFiles.length;i ++){  
                if (TempFiles[i].isDirectory()){  
                    this.FileList.add(TempFiles[i]);  
                }  
                else{  
                    if (TempFiles[i].getName().endsWith(this.Ext)){  
                        this.FileList.add(TempFiles[i]);  
                    }  
                }  
            }  
            //赋值给listView  
            String[] TempStrArr = new String[this.FileList.size()];  
            for (int i = 0;i < TempStrArr.length;i ++){  
                TempStrArr[i] = this.FileList.get(i).isDirectory() ? "[" + this.FileList.get(i).getName() + "]" : this.FileList.get(i).getName();  
            }  
            ListView mListView = (ListView)findViewById(R.id.FileList);  
            mListView.setAdapter(new ArrayAdapter<String>(this,  
                    android.R.layout.simple_list_item_1, TempStrArr));  
        }  
        else{  
            Toast.makeText(this,"权限不够！", Toast.LENGTH_LONG).show();  
            if (this.FileNow.getParentFile() != null){  
                this.FileNow = this.FileNow.getParentFile();  
            }  
            else{  
                this.FileNow = new File(DefaultFilePath);  
            }  
            this.RefreshFileList();  
        }  
    }  
      
    public void Return (View srcView){  
        if (this.FileNow.getParentFile() != null){  
            this.FileNow = this.FileNow.getParentFile();  
            this.RefreshFileList();  
        }  
    }  
      
    public void Cancel(View srcView){  
        this.finish();  
    }  
      
    public void Enter (View srcView){  
    	
    	EditText EditFileName = (EditText)findViewById(R.id.editFileName);  
    	if(EditFileName.getText().toString().trim().equals(""))
    	{
    		Toast.makeText(this,"请输入导出文件名称", Toast.LENGTH_LONG).show();
    		return;
    	}
        Intent intent = new Intent();  
        intent.putExtra("FilePathName",this.FileNow.getAbsolutePath() + "/" + EditFileName.getText());   
        setResult(SaveFileDialogActivity.RESULT_CODE, intent);  
        this.finish();  
    }  
}
