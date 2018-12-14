package com.cugkuan.editor.richeditor.htmledit.more;

import android.widget.ImageView;

import com.cugkuan.editor.richeditor.htmledit.EditButton;
import com.cugkuan.editor.richeditor.htmledit.RichEditorBridge;


/**
 * Copyright (C) 2017 北京太阳星网络科技有限公司
 * Created by Kuan on 2018/6/20;18:05
 *
 * @Author Kuan
 * @Date 2018/6/20
 * 小鸟校园
 * BirdCampus
 */
public class UnOrderlistEditButton extends EditButton {

    public UnOrderlistEditButton(ImageView textView, RichEditorBridge editorBrige) {
        super(textView, editorBrige);
    }

    @Override
    public void handle() {
        getRichEditor().removeFormat();

        for (EditButton button : relevance) {
            button.unMark();
        }
        changeMark();
        getRichEditor().setBullets();
    }
}
