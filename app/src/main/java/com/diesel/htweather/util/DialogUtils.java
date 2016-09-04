package com.diesel.htweather.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.diesel.htweather.R;

/**
 * Comments：
 *
 * @author Diesel
 *         Time: 2016/9/3
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 1.0.0
 */
public class DialogUtils {

    public interface DialogOnClickListener {

        void onClick(DialogInterface dialog, int which, String inputContent);
    }

    public static void showInputDialog(@NonNull Context context, @NonNull String title,
            @NonNull DialogOnClickListener listener) {
        showAlertDialog(context, title, true, false, listener, InputType.TYPE_CLASS_TEXT);
    }

    public static void showTelephoneDialog(@NonNull Context context,
            @NonNull DialogOnClickListener listener) {
        showAlertDialog(context, context.getResources().getString(R.string.modify_telephone), true,
                false, listener, InputType.TYPE_CLASS_PHONE);
    }

    private static void showAlertDialog(final Context context, String title, boolean cancelable,
            boolean cancel, final DialogOnClickListener listener, final int inputType) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancel);
        dialog.show();

        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setContentView(R.layout.dialog_input_layout);
        TextView titleTv = (TextView) window.findViewById(R.id.dialog_title_tv);
        ImageView closeIv = (ImageView) window.findViewById(R.id.dismiss_dialog_btn);
        final EditText contentEt = (EditText) window.findViewById(R.id.dialog_input_et);
        contentEt.setInputType(inputType);
        Button commitBtn = (Button) window.findViewById(R.id.commit_btn);

        titleTv.setText(title);
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = contentEt.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.show(context.getResources().getString(R.string.tips_input_content));
                    return;
                }
                if (inputType == InputType.TYPE_CLASS_PHONE && !StringUtils.mobileVerify(content)) {
                    ToastUtils.show(context.getResources()
                            .getString(R.string.tips_input_correct_telephone));
                    return;
                }
                dialog.dismiss();
                listener.onClick(dialog, DialogInterface.BUTTON_POSITIVE, content);
            }
        });
    }

    public static void showGenderDialog(Context context,
            @NonNull final DialogOnClickListener listener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_gender_layout);
        final RadioGroup radioGroup = (RadioGroup) window.findViewById(R.id.gender_radio_group);
        final RadioButton maleBtn = (RadioButton) window.findViewById(R.id.male_radio_button);
        final RadioButton femaleBtn = (RadioButton) window.findViewById(R.id.female_radio_button);
        ImageView closeIv = (ImageView) window.findViewById(R.id.dismiss_dialog_btn);
        Button commitBtn = (Button) window.findViewById(R.id.commit_btn);
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String gender = "";
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId == maleBtn.getId()) {
                    gender = maleBtn.getText().toString();
                } else if (checkedRadioButtonId == femaleBtn.getId()) {
                    gender = femaleBtn.getText().toString();
                }
                listener.onClick(dialog, DialogInterface.BUTTON_POSITIVE, gender);
            }
        });
    }

    public static void showAddPlantDialog(final Context context,
            @NonNull final DialogOnClickListener listener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setContentView(R.layout.dialog_add_plant_layout);
        final EditText plantNameEt = (EditText) window.findViewById(R.id.dialog_plant_name_et);
        final EditText plantAreaEt = (EditText) window.findViewById(R.id.dialog_plant_area_et);
        ImageView closeIv = (ImageView) window.findViewById(R.id.dismiss_dialog_btn);
        final Button commitBtn = (Button) window.findViewById(R.id.commit_btn);
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plantName = plantNameEt.getText().toString();
                String plantArea = plantAreaEt.getText().toString();
                if (TextUtils.isEmpty(plantName) || TextUtils.isEmpty(plantArea)) {
                    ToastUtils.show(
                            context.getResources().getString(R.string.tips_has_data_is_empty));
                    return;
                }
                dialog.dismiss();
                listener.onClick(dialog, DialogInterface.BUTTON_POSITIVE,
                        plantName + "&" + plantArea);
            }
        });
    }

}
