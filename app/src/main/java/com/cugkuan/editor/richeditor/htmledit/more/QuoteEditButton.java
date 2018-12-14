package com.cugkuan.editor.richeditor.htmledit.more;

import android.widget.ImageView;

import com.cugkuan.editor.richeditor.htmledit.EditButton;
import com.cugkuan.editor.richeditor.htmledit.RichEditorBridge;


/**
 * Copyright (C) 2017 北京太阳星网络科技有限公司
 * Created by Kuan on 2018/6/20;18:44
 *
 * @Author Kuan
 * @Date 2018/6/20
 * 小鸟校园
 * BirdCampus
 */
public class QuoteEditButton  extends EditButton {

    public QuoteEditButton(ImageView textView, RichEditorBridge editorBrige) {
        super(textView, editorBrige);
    }

    @Override
    public void handle() {


        for (EditButton button :relevance){
            if (button instanceof  UnOrderlistEditButton){
                if (button.isMark){
                    button.unMark();
                    getRichEditor().setBullets();
                }
            }
            if (button instanceof  OrderlistEditButton){
                if (button.isMark){
                    button.unMark();
                    getRichEditor().setNumbers();
                }
            }
            button.unMark();
        }
        if (isMark){
            getRichEditor().removeFormat();
            unMark();
        }else {
            getRichEditor().setBlockquote();
            mark();
        }

    }
}
