package com.wico.ui.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.wico.R;
import com.wico.ui.PageActivity;

public class LinkCreator {

    private Context context;
    private TextView textView;

    public void addLinks(Context context, CharSequence input, TextView textView){
        this.context = context;
        this.textView = textView;
        String inputString = input.toString();
        if (checkForClickableLinkTag(inputString)){
            executeClickableLinkActivity(inputString);
        }else{
            doNothing();
        }
    }

    private void doNothing(){
    }

    private boolean checkForClickableLinkTag(String inputString) {
        if (inputString.contains("[") && inputString.contains("]")) {
            return true;
        } else {
            return false;
        }
    }

    private void executeClickableLinkActivity(final String inputString){
        SpannableString spannableInputString = new SpannableString(inputString);
        int indexErrorMargin = 1;
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(context, PageActivity.class);
                intent.putExtra("pagePath",inputString);
                context.startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint drawState) {
                super.updateDrawState(drawState);
                drawState.setUnderlineText(true);
            }
        };

        spannableInputString.setSpan(clickableSpan, inputString.indexOf("[") + indexErrorMargin, inputString.indexOf("]"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableInputString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.GREEN);
    }
}
