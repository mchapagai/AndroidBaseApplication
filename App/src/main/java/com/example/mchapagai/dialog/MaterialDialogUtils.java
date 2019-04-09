package com.example.mchapagai.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.mchapagai.R;

import javax.annotation.Nonnull;

public class MaterialDialogUtils {

    public static MaterialDialogFragment showDialogWithSingleButton(Context context, int title, int message,
            int buttonMessage) {
        return showDialog(context, title, message, buttonMessage);
    }

    public static MaterialDialogFragment showDialog(@Nonnull Context context, int title, int message,
            int buttonMessage) {
        MaterialDialogBuilder builder = new MaterialDialogBuilder()
                .setTitle(context.getResources().getString(title))
                .setMessage(context.getResources().getString(message))
                .setCancelable(true)
                .setPositiveButtonText(context.getResources().getString(buttonMessage))
                .setLayoutResId(R.layout.custom_confirmation_dialog)
                .setCustomButton(true);

        return MaterialDialogFragment.showDialog(builder, (AppCompatActivity) context);
    }

    public static MaterialDialogFragment showDialog(@Nonnull Context context, String title, String message,
            String buttonMessage) {
        MaterialDialogBuilder builder = new MaterialDialogBuilder()
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButtonText(buttonMessage)
                .setLayoutResId(R.layout.custom_confirmation_dialog)
                .setCustomButton(true);

        return MaterialDialogFragment.showDialog(builder, (AppCompatActivity) context);
    }
}