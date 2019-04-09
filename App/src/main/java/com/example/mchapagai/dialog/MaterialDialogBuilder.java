package com.example.mchapagai.dialog;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;

import java.io.Serializable;

public class MaterialDialogBuilder implements Parcelable {

    private String title;
    private String message;
    private String positiveButtonText;
    private String tag;
    private Intent positiveButtonIntent;
    private String negativeButtonText;
    private String neutralTextButton;
    private boolean isCancelable;
    private boolean isCustomButton;
    private boolean isCustomWindow;
    private boolean isProgressDialog;
    private Serializable negativeButtonData;
    private Serializable neutralButtonData;
    private Serializable positiveButtonData;
    private
    @LayoutRes
    int layoutResId;
    @DrawableRes
    int drawableResId;

    public MaterialDialogBuilder() {
    }

    public MaterialDialogBuilder(Parcel in) {
        title = in.readString();
        message = in.readString();
        positiveButtonText = in.readString();
        tag = in.readString();
        positiveButtonIntent = in.readParcelable(Intent.class.getClassLoader());
        negativeButtonText = in.readString();
        neutralTextButton = in.readString();
        isCancelable = in.readByte() != 0;
        isCustomButton = in.readByte() != 0;
        isCustomWindow = in.readByte() != 0;
        isProgressDialog = in.readByte() != 0;
        layoutResId = in.readInt();
        drawableResId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(message);
        dest.writeString(positiveButtonText);
        dest.writeString(tag);
        dest.writeParcelable(positiveButtonIntent, flags);
        dest.writeString(negativeButtonText);
        dest.writeString(neutralTextButton);
        dest.writeByte((byte) (isCancelable ? 1 : 0));
        dest.writeByte((byte) (isCustomButton ? 1 : 0));
        dest.writeByte((byte) (isCustomWindow ? 1 : 0));
        dest.writeByte((byte) (isProgressDialog ? 1 : 0));
        dest.writeInt(layoutResId);
        dest.writeInt(drawableResId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MaterialDialogBuilder> CREATOR =
            new Creator<MaterialDialogBuilder>() {
                @Override
                public MaterialDialogBuilder createFromParcel(Parcel in) {
                    return new MaterialDialogBuilder(in);
                }

                @Override
                public MaterialDialogBuilder[] newArray(int size) {
                    return new MaterialDialogBuilder[size];
                }
            };

    public String getTitle() {
        return title;
    }

    public MaterialDialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MaterialDialogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public MaterialDialogBuilder setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Intent getPositiveButtonIntent() {
        return positiveButtonIntent;
    }

    public void setPositiveButtonIntent(Intent positiveButtonIntent) {
        this.positiveButtonIntent = positiveButtonIntent;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public String getNeutralTextButton() {
        return neutralTextButton;
    }

    public void setNeutralTextButton(String neutralTextButton) {
        this.neutralTextButton = neutralTextButton;
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public MaterialDialogBuilder setCancelable(boolean cancelable) {
        isCancelable = cancelable;
        return this;
    }

    public boolean isCustomButton() {
        return isCustomButton;
    }

    public MaterialDialogBuilder setCustomButton(boolean isCustomButton) {
        this.isCustomButton = isCustomButton;
        return this;
    }

    public boolean isCustomWindow() {
        return isCustomWindow;
    }

    public void setCustomWindow(boolean customWindow) {
        isCustomWindow = customWindow;
    }

    public boolean isProgressDialog() {
        return isProgressDialog;
    }

    public void setProgressDialog(boolean progressDialog) {
        isProgressDialog = progressDialog;
    }

    public Serializable getNegativeButtonData() {
        return negativeButtonData;
    }

    public void setNegativeButtonData(Serializable negativeButtonData) {
        this.negativeButtonData = negativeButtonData;
    }

    public Serializable getNeutralButtonData() {
        return neutralButtonData;
    }

    public void setNeutralButtonData(Serializable neutralButtonData) {
        this.neutralButtonData = neutralButtonData;
    }

    public Serializable getPositiveButtonData() {
        return positiveButtonData;
    }

    public void setPositiveButtonData(Serializable positiveButtonData) {
        this.positiveButtonData = positiveButtonData;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

    public MaterialDialogBuilder setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
        return this;
    }

    public int getDrawableResId() {
        return drawableResId;
    }

    public void setDrawableResId(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public static Creator<MaterialDialogBuilder> getCREATOR() {
        return CREATOR;
    }

}
