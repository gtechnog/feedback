package com.sample.feedback.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;

import com.sample.feedback.model.DataMap;

import java.util.List;

public class CustomRadioGroup extends RadioGroup {

    public CustomRadioGroup(Context context) {
        super(context);
    }

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setChoiceModel(@NonNull DataMap dataMap) {
        removeAllViews();
        List<String> options =  dataMap.getOptions();
        for (int i = 0 ; i< options.size(); i++) {
            RadioButton radioButton = new AppCompatRadioButton(getContext());
            radioButton.setText(dataMap.getOptions().get(i));
            radioButton.setId(View.generateViewId());
            addView(radioButton);
        }
    }
}
