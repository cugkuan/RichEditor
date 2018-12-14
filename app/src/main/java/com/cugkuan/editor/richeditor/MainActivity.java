package com.cugkuan.editor.richeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.cugkuan.editor.library.RichEditor;
import com.cugkuan.editor.richeditor.htmledit.HtmlEditView;

public class MainActivity extends AppCompatActivity {


    private RichEditor mRichEditor;

    private HtmlEditView htmlEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRichEditor = findViewById(R.id.editor);


        mRichEditor.focusEditor();

        htmlEditView = findViewById(R.id.htmlEdit);

        //这行代码必须要
        htmlEditView.setRichEditor(mRichEditor);

        htmlEditView.setOnHtmlEditListener(new HtmlEditView.OnHtmlEditListener() {
            @Override
            public void insertImage() {


                //这里是插入图片的业务逻辑代码
               // mRichEditor.insertImage("图片地址",null);

            }

            @Override
            public void confirm() {


                String text = mRichEditor.getHtml();

                if (TextUtils.isEmpty(text)){
                    text = "发布按钮点击了";
                }
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();


            }
        });
    }
}
