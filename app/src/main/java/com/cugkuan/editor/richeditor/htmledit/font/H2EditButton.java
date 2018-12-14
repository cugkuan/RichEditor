package com.cugkuan.editor.richeditor.htmledit.font;

import android.widget.ImageView;

import com.cugkuan.editor.richeditor.htmledit.EditButton;
import com.cugkuan.editor.richeditor.htmledit.RichEditorBridge;
import com.cugkuan.editor.richeditor.htmledit.more.OrderlistEditButton;
import com.cugkuan.editor.richeditor.htmledit.more.UnOrderlistEditButton;

/**
 * Copyright (C) 2017 北京太阳星网络科技有限公司
 * Created by Kuan on 2018/6/20;16:44
 *
 * @Author Kuan
 * @Date 2018/6/20
 * 小鸟校园
 * BirdCampus
 */
public class H2EditButton extends EditButton {

    public H2EditButton(ImageView textView, RichEditorBridge richEditor) {
        super(textView, richEditor);
    }

    @Override
    public void handle() {
        mRichEditor.getRichEditor().removeFormat();
        if (changeMark()){
            mRichEditor.getRichEditor().setHeading(2);
            for (EditButton button : relevance){
                if (button instanceof UnOrderlistEditButton){
                    if (button.isMark){
                        getRichEditor().setBullets();
                    }
                }
                if (button instanceof OrderlistEditButton){
                    if (button.isMark){
                        getRichEditor().setNumbers();
                    }
                }
                button.unMark();
            }
        }
    }
}
