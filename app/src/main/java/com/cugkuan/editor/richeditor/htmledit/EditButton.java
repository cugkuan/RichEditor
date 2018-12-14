package com.cugkuan.editor.richeditor.htmledit;

import android.view.View;
import android.widget.ImageView;

import com.cugkuan.editor.library.RichEditor;

import java.util.LinkedList;
import java.util.List;



/**
 * 编辑的按钮
 */
public abstract class EditButton {


    private int  TAG;

    public boolean isMark = false;

    protected ImageView textView;

    /**
     * 操作相关联的的对象
     */
    protected List<EditButton> relevance = new LinkedList<>();

    protected RichEditorBridge mRichEditor;

    /**
     * 标记的资源
     */
    private int markRes = -1;
    /**
     * 没有标记的资源
     */
    private int unMarkRes = -1;

    public EditButton(ImageView textView, RichEditorBridge editorBrige) {
        this.textView = textView;

        TAG = textView.getId();
        mRichEditor = editorBrige;

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle();
            }
        });
    }

    public EditButton setMarkRes(int res){
        markRes = res;
        return this;
    }
    public EditButton setUnMarkRes(int res){
        unMarkRes = res;
        return this;
    }

    public RichEditor getRichEditor(){
        return mRichEditor.getRichEditor();
    }
    /**
     *添加关联的Button
     * @param editButton
     * @return
     */
    public EditButton addRelevance(EditButton editButton){
        relevance.add(editButton);
        return this;
    }

    /**
     *事件的关联处理
     */
    public abstract void handle();



    public boolean  changeMark(){
        if (isMark){
            unMark();
        }else {
            mark();
        }
        return isMark;
    }

    public int getTag(){
        return TAG;
    }

    public void mark() {
        isMark = true;
        if (markRes != -1){
            textView.setImageResource(markRes);
        }
    }

    public void unMark() {
        isMark = false;
        if (unMarkRes != -1){
            textView.setImageResource(unMarkRes);
        }
    }
}
