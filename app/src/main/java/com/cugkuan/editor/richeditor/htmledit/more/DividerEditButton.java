package com.cugkuan.editor.richeditor.htmledit.more;

import android.widget.ImageView;

import com.cugkuan.editor.richeditor.htmledit.EditButton;
import com.cugkuan.editor.richeditor.htmledit.RichEditorBridge;
import com.cugkuan.editor.richeditor.htmledit.font.ContentEditButton;

/**
 * Copyright (C) 2017 北京太阳星网络科技有限公司
 * Created by Kuan on 2018/6/20;18:05
 *
 * @Author Kuan
 * @Date 2018/6/20
 * 小鸟校园
 * BirdCampus
 */
public class DividerEditButton extends EditButton {

    public interface OnDividerListener{

        void click();
    }

    private OnDividerListener mListener;

    public DividerEditButton(ImageView textView, RichEditorBridge editorBrige) {
        super(textView, editorBrige);
    }

    public void setOnClickListener(OnDividerListener listener){
        mListener = listener;
    }

    @Override
    public void handle() {
        if (mListener != null){
            mListener.click();
        }
        mRichEditor.getRichEditor().insertHorizontalRule();
        for (EditButton button :relevance){
            button.unMark();
            if (button instanceof ContentEditButton){
                button.mark();
            }
        }
    }
}
