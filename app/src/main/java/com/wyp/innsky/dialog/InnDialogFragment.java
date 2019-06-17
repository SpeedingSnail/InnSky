package com.wyp.innsky.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.wyp.innsky.R;

/**
 * Created by yingping_wang on 2019/3/19.
 */
public class InnDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = "InnDialogFragment";

    public static Builder create(Context context, FragmentManager fm) {
        return new Builder(context,fm);
    }

    private TextView tvTitle, tvMessage;
    private EditText edt;
    private CheckBox cb;
    private Button btnPositive, btnNegative;

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_MESSAGE = "key_message";
    private static final String KEY_EDT_HINT = "key_edt_hint";
    private static final String KEY_CHECK_BOX_TEXT = "key_check_box_text";
    private static final String KEY_POSITIVE_TEXT = "key_positive_text";
    private static final String KEY_NEGATIVE_TEXT = "key_negative_text";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.dialog_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tv_dialog_title);
        tvMessage = view.findViewById(R.id.tv_dialog_message);
        edt = view.findViewById(R.id.edt_dialog);
        cb = view.findViewById(R.id.cb_dialog);
        btnPositive = view.findViewById(R.id.btn_positive);
        btnNegative = view.findViewById(R.id.btn_negative);

        Bundle args = getArguments();
        setTitle(args.getCharSequence(KEY_TITLE));
        setMessage(args.getCharSequence(KEY_MESSAGE));
        setEditHint(args.getCharSequence(KEY_EDT_HINT));
        setCheckBox(args.getCharSequence(KEY_CHECK_BOX_TEXT),null);
        setPositive(args.getCharSequence(KEY_POSITIVE_TEXT),null);
        setNegative(args.getCharSequence(KEY_NEGATIVE_TEXT),null);

//        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT ;
//        getDialog().getWindow().setAttributes(params);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public interface OnCheckListener{
        void onCheck(boolean isChecked);
    }


    public static class Builder {

        private Context context;
        private FragmentManager fm;
        private CharSequence title, message, btnPositiveText, btnNegativeText, edtHint,checkBoxText;
        private View.OnClickListener positiveListener,negativeListener;
        private OnCheckListener onCheckListener;

        public Builder(Context context,FragmentManager fm) {
            this.context = context;
            this.fm = fm;
        }

        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(int resId) {
            this.title = context.getString(resId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int resId) {
            this.message = context.getString(resId);
            return this;
        }

        public Builder setEditHint(CharSequence edtHint) {
            this.edtHint = edtHint;
            return this;
        }

        public Builder setEditHint(int resId) {
            this.edtHint = context.getString(resId);
            return this;
        }

        public Builder setPositive(CharSequence btnPositiveText,View.OnClickListener positiveListener){
            this.btnPositiveText = btnPositiveText;
            this.positiveListener = positiveListener;
            return this;
        }

        public Builder setPositive(int resId,View.OnClickListener positiveListener){
            this.btnPositiveText = context.getString(resId);
            this.positiveListener = positiveListener;
            return this;
        }

        public Builder setNegative(CharSequence btnNegativeText,View.OnClickListener negativeListener){
            this.btnNegativeText = btnNegativeText;
            this.negativeListener = negativeListener;
            return this;
        }

        public Builder setNegative(int resId,View.OnClickListener negativeListener){
            this.btnNegativeText = context.getString(resId);
            this.negativeListener = negativeListener;
            return this;
        }

        public Builder setCheckBox(CharSequence checkBoxText,OnCheckListener onCheckListener) {
            this.checkBoxText = checkBoxText;
            this.onCheckListener = onCheckListener;
            return this;
        }

        public Builder setCheckBox(int resId,OnCheckListener onCheckListener) {
            this.checkBoxText = context.getString(resId);
            this.onCheckListener = onCheckListener;
            return this;
        }

        public void show() {
            Bundle args = new Bundle();
            args.putCharSequence(KEY_TITLE,title);
            args.putCharSequence(KEY_MESSAGE,message);
            args.putCharSequence(KEY_EDT_HINT,edtHint);
            args.putCharSequence(KEY_POSITIVE_TEXT,btnPositiveText);
            args.putCharSequence(KEY_NEGATIVE_TEXT,btnNegativeText);
            args.putCharSequence(KEY_CHECK_BOX_TEXT,checkBoxText);
            InnDialogFragment dialog = (InnDialogFragment) Fragment.instantiate(context,InnDialogFragment.class.getName(),args);
            dialog.show(fm,TAG);
        }
    }

    private void setNegative(CharSequence btnNegativeText, View.OnClickListener negativeListener) {
        if (TextUtils.isEmpty(btnNegativeText)){
            btnNegative.setVisibility(View.GONE);
        } else {
            btnNegative.setText(btnNegativeText);
            btnNegative.setOnClickListener(negativeListener);
        }
    }

    private void setPositive(CharSequence btnPositiveText, final View.OnClickListener positiveListener) {
        if (TextUtils.isEmpty(btnPositiveText)){
            btnPositive.setVisibility(View.GONE);
        } else {
            btnPositive.setText(btnPositiveText);
            btnPositive.setOnClickListener(positiveListener);
        }
    }

    private void setCheckBox(CharSequence checkBoxText, final OnCheckListener onCheckListener) {
        if (TextUtils.isEmpty(checkBoxText)){
            cb.setVisibility(View.GONE);
        } else {
            cb.setText(checkBoxText);
            if (onCheckListener != null){
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        onCheckListener.onCheck(isChecked);
                    }
                });
            }
        }
    }

    private void setEditHint(CharSequence edtHint) {
        if (TextUtils.isEmpty(edtHint)){
            edt.setVisibility(View.GONE);
        } else {
            edt.setHint(edtHint);
        }
    }

    private void setMessage(CharSequence message) {
        if (TextUtils.isEmpty(message)){
            tvMessage.setVisibility(View.GONE);
        } else {
            tvMessage.setText(message);
        }
    }

    private void setTitle(CharSequence title) {
        if (TextUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
    }


}
