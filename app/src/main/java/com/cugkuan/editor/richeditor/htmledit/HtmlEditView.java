package com.cugkuan.editor.richeditor.htmledit;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.cugkuan.editor.library.RichEditor;
import com.cugkuan.editor.richeditor.R;
import com.cugkuan.editor.richeditor.htmledit.font.BlodEditButton;
import com.cugkuan.editor.richeditor.htmledit.font.H1EditButton;
import com.cugkuan.editor.richeditor.htmledit.font.H2EditButton;
import com.cugkuan.editor.richeditor.htmledit.font.H3EditButton;
import com.cugkuan.editor.richeditor.htmledit.font.ItalicEditButton;
import com.cugkuan.editor.richeditor.htmledit.font.StrikeEditButton;
import com.cugkuan.editor.richeditor.htmledit.font.UnderlineEditButton;
import com.cugkuan.editor.richeditor.htmledit.more.DividerEditButton;
import com.cugkuan.editor.richeditor.htmledit.more.OrderlistEditButton;
import com.cugkuan.editor.richeditor.htmledit.more.QuoteEditButton;
import com.cugkuan.editor.richeditor.htmledit.more.UnOrderlistEditButton;

import java.util.LinkedList;
import java.util.List;


/**
 * Copyright (C) 2017 北京太阳星网络科技有限公司
 * Created by Kuan on 2018/6/15;14:18
 *
 * @Author Kuan
 * @Date 2018/6/15
 * 小鸟校园
 * BirdCampus
 */
public class HtmlEditView extends FrameLayout implements RichEditorBridge {


    public enum TextColor {

        DEFAULT(Color.BLACK),
        RED(Color.RED),
        GREEN(Color.GREEN),
        ORANGE(Color.YELLOW),
        BLUE(Color.BLUE);
        private int color;

        TextColor(int color) {
            this.color = color;
        }

        public int value() {
            return color;
        }
    }


    public interface OnHtmlEditListener {

        void insertImage();

        void confirm();
    }

    private boolean isOpenFont = false;

    private boolean isOpenColor = false;

    private View layoutFont;

    private View layoutColor;


    private ImageView ivOpenFont;
    private ImageView ivOpenColor;
    private RichEditor mRichEditor;

    private OnHtmlEditListener mOnHtmlEditListener;


    private List<EditButton> mEditButtons = new LinkedList<>();

    private EditButton mBold;
    private EditButton mItalic;
    private EditButton mStrike;
    private EditButton mUnderline;
    private EditButton mH1;
    private EditButton mH2;
    private EditButton mH3;
    private EditButton mOrder;
    private EditButton mUnorder;
    private EditButton mQuote;

    public HtmlEditView(@NonNull Context context) {
        this(context, null);
    }

    public HtmlEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public HtmlEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(final Context context, AttributeSet attributeSet) {

        LayoutInflater.from(context).inflate(R.layout.layout_html_edit, this);

        layoutColor = findViewById(R.id.layout_color);
        layoutFont = findViewById(R.id.layout_font);


        layoutColor.setVisibility(GONE);
        layoutFont.setVisibility(GONE);
        ivOpenColor = findViewById(R.id.btn_open_color);
        ivOpenFont = findViewById(R.id.btn_open_font);

        findViewById(R.id.btn_commit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnHtmlEditListener != null) {
                    mOnHtmlEditListener.confirm();
                }
            }
        });


        findViewById(R.id.btn_open_color).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isOpenFont = false;
                if (isOpenColor) {
                    isOpenColor = false;
                } else {
                    isOpenColor = true;
                }
                openOrClosedLayout();
            }
        });
        findViewById(R.id.btn_open_font).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isOpenColor = false;
                if (isOpenFont) {
                    isOpenFont = false;
                } else {
                    isOpenFont = true;
                }
                openOrClosedLayout();
            }
        });

        findViewById(R.id.btn_insert_image).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnHtmlEditListener != null) {
                    mOnHtmlEditListener.insertImage();
                }
            }
        });
        findViewById(R.id.btn_undo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRichEditor.undo();
            }
        });
        findViewById(R.id.btn_redo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRichEditor.redo();
            }
        });

        //颜色值的选择
        findViewById(R.id.btn_color_default).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRichEditor.setTextColor(TextColor.DEFAULT.value());
                openOrClosedColor();
            }
        });

        findViewById(R.id.btn_color_blue).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRichEditor.setTextColor(TextColor.BLUE.value());
                ;
                openOrClosedColor();
            }
        });
        findViewById(R.id.btn_color_red).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRichEditor.setTextColor(TextColor.RED.value());
                openOrClosedColor();
            }
        });
        findViewById(R.id.btn_color_orange).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRichEditor.setTextColor(TextColor.ORANGE.value());
                openOrClosedColor();
            }
        });
        findViewById(R.id.btn_color_green).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRichEditor.setTextColor(TextColor.GREEN.value());
                openOrClosedColor();
            }
        });

        //字体的相关操作
        ImageView tvBold = findViewById(R.id.tv_bold);
        mBold = new BlodEditButton(tvBold, this);
        mBold.setMarkRes(R.mipmap.font_bold_s)
                .setUnMarkRes(R.mipmap.font_bold);
        mEditButtons.add(mBold);


        ImageView tvItalic = findViewById(R.id.tv_italic);
        mItalic = new ItalicEditButton(tvItalic, this);
        mItalic.setUnMarkRes(R.mipmap.font_itali)
                .setMarkRes(R.mipmap.font_itali_s);
        mEditButtons.add(mItalic);

        ImageView tvStrike = findViewById(R.id.tv_strike);
        mStrike = new StrikeEditButton(tvStrike, this);
        mStrike.setMarkRes(R.mipmap.font_strike_s)
                .setUnMarkRes(R.mipmap.font_strike);
        mEditButtons.add(mStrike);

        ImageView tvUnderline = findViewById(R.id.tv_underline);
        mUnderline = new UnderlineEditButton(tvUnderline, this);
        mUnderline.setUnMarkRes(R.mipmap.font_underline)
                .setMarkRes(R.mipmap.font_underline_s);
        mEditButtons.add(mUnderline);


        ImageView tvH1 = findViewById(R.id.tv_h1);
        mH1 = new H1EditButton(tvH1, this);
        mH1.setUnMarkRes(R.mipmap.font_h1)
                .setMarkRes(R.mipmap.font_h1_s);
        mEditButtons.add(mH1);

        ImageView tvH2 = findViewById(R.id.tv_h2);
        mH2 = new H2EditButton(tvH2, this);
        mH2.setMarkRes(R.mipmap.font_h2_s)
                .setUnMarkRes(R.mipmap.font_h2);
        mEditButtons.add(mH2);


        ImageView tvH3 = findViewById(R.id.tv_h3);
        mH3 = new H3EditButton(tvH3, this);
        mH3.setUnMarkRes(R.mipmap.font_h3)
                .setMarkRes(R.mipmap.font_h3_s);
        mEditButtons.add(mH3);


        ImageView tvDivider = findViewById(R.id.btn_divider);
        DividerEditButton divider = new DividerEditButton(tvDivider, this);
        mEditButtons.add(divider);

        ImageView tvOrder = findViewById(R.id.tv_orderList);
        mOrder = new OrderlistEditButton(tvOrder, this);
        mOrder.setMarkRes(R.mipmap.edit_order_list_s)
                .setUnMarkRes(R.mipmap.edit_order_list);
        mEditButtons.add(mOrder);

        ImageView tvUnorder = findViewById(R.id.tv_unOrderList);
        mUnorder = new UnOrderlistEditButton(tvUnorder, this);
        mUnorder.setUnMarkRes(R.mipmap.edit_unorder_list)
                .setMarkRes(R.mipmap.edit_unorder_list_s);
        mEditButtons.add(mUnorder);

        ImageView tvQuote = findViewById(R.id.btn_quote);
        mQuote = new QuoteEditButton(tvQuote, this);
        mQuote.setMarkRes(R.mipmap.edit_quote_s)
                .setUnMarkRes(R.mipmap.edit_quote);
        mEditButtons.add(mQuote);


        //配置关联性
        mH1.addRelevance(mH2)
                .addRelevance(mUnorder)
                .addRelevance(mOrder)
                .addRelevance(mQuote)
                .addRelevance(mH3);
        mH2.addRelevance(mH1)
                .addRelevance(mUnorder)
                .addRelevance(mOrder)
                .addRelevance(mQuote)
                .addRelevance(mH3);

        mH3.addRelevance(mH1)
                .addRelevance(mUnorder)
                .addRelevance(mOrder)
                .addRelevance(mQuote)
                .addRelevance(mH2);

        divider.addRelevance(mH1)
                .addRelevance(mH2)
                .addRelevance(mH3)
                .addRelevance(mUnorder)
                .addRelevance(mOrder)
                .addRelevance(mQuote)
                .addRelevance(mBold)
                .addRelevance(mItalic)
                .addRelevance(mStrike)
                .addRelevance(mUnderline);

        mUnorder.addRelevance(mOrder)
                .addRelevance(mH1)
                .addRelevance(mH2)
                .addRelevance(mH3)
                .addRelevance(mQuote);

        mOrder.addRelevance(mUnorder)
                .addRelevance(mH1)
                .addRelevance(mH2)
                .addRelevance(mH3)
                .addRelevance(mQuote);

        mQuote.addRelevance(mH1)
                .addRelevance(mH2)
                .addRelevance(mH3)
                .addRelevance(mOrder)
                .addRelevance(mUnorder);

    }

    public void setRichEditor(RichEditor richEditor) {

        mRichEditor = richEditor;
        mRichEditor.setOnDecorationChangeListener(new RichEditor.OnDecorationStateListener() {
            @Override
            public void onStateChangeListener(String text, List<RichEditor.Type> types) {
                for (EditButton button : mEditButtons) {
                    button.unMark();
                }
                for (int i = 0; i < types.size(); i++) {
                    changeType(types.get(i));
                }
                if (mOrder.isMark && mUnorder.isMark) {
                    mOrder.unMark();
                }


                Log.e("lmk","delete被调用");

            }
        });
    }

    public void changeType(RichEditor.Type type) {

        switch (type) {
            case H1:
                mH1.mark();
                break;
            case H2:
                mH2.mark();
                break;
            case H3:
                mH3.mark();
                break;
            case BOLD:
                mBold.mark();
                break;
            case ITALIC:
                mItalic.mark();
                break;
            case STRIKETHROUGH:
                mStrike.mark();
                break;
            case UNORDEREDLIST:
                mUnorder.mark();
                break;
            case ORDEREDLIST:
                mOrder.mark();
                break;
            case BLOCKQUOTE:
                mQuote.mark();
                break;
            case UNDERLINE:
                mUnderline.mark();
                break;
        }
    }

    @Override
    public RichEditor getRichEditor() {
        return mRichEditor;
    }

    public void setOnHtmlEditListener(OnHtmlEditListener listener) {
        mOnHtmlEditListener = listener;
    }


    private void openOrClosedColor() {
        isOpenFont = false;
        if (isOpenColor) {
            isOpenColor = false;
        } else {
            isOpenColor = true;
        }
        openOrClosedLayout();

    }


    private void openOrClosedLayout() {
        if (isOpenColor) {
            layoutColor.setVisibility(VISIBLE);
            ivOpenColor.setImageResource(R.mipmap.open_color_s);
        } else {
            layoutColor.setVisibility(GONE);
            ivOpenColor.setImageResource(R.mipmap.open_color);
        }
        if (isOpenFont) {
            layoutFont.setVisibility(VISIBLE);
            ivOpenFont.setImageResource(R.mipmap.font_s);
        } else {
            layoutFont.setVisibility(GONE);
            ivOpenFont.setImageResource(R.mipmap.font);
        }
    }

}
