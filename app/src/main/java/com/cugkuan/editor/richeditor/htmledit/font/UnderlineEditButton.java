package com.cugkuan.editor.richeditor.htmledit.font;

import android.widget.ImageView;

import com.cugkuan.editor.richeditor.htmledit.EditButton;
import com.cugkuan.editor.richeditor.htmledit.RichEditorBridge;

/**
 * Copyright (C) 2017 北京太阳星网络科技有限公司
 * Created by Kuan on 2018/6/20;16:44
 *
 * @Author Kuan
 * @Date 2018/6/20
 * 小鸟校园
 * BirdCampus
 */
public class UnderlineEditButton extends EditButton {

    public UnderlineEditButton(ImageView textView, RichEditorBridge richEditor) {
        super(textView, richEditor);
    }

    @Override
    public void handle() {
        changeMark();
        mRichEditor.getRichEditor().setUnderline();
    }
}
