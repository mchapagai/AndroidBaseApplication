package com.example.mchapagai.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mchapagai.R;
import com.google.android.material.button.MaterialButton;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class MaterialDialogFragment extends DialogFragment {

    public static final String DATA_MODEL = "dateModel";
    private OnDialogClickListener onDialogClickListener;

    public MaterialDialogFragment() {
    }

    private static MaterialDialogFragment buildAlertDialog(MaterialDialogBuilder materialDialogBuilder) {
        MaterialDialogFragment fragment = new MaterialDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(DATA_MODEL, materialDialogBuilder);
        fragment.setArguments(args);
        return fragment;
    }

    public static MaterialDialogFragment showDialog(MaterialDialogBuilder materialDialogBuilder, AppCompatActivity activity) {
        FragmentManager manager = activity.getSupportFragmentManager();
        String tag = materialDialogBuilder.getTag();
        MaterialDialogFragment fragment = MaterialDialogFragment.buildAlertDialog(materialDialogBuilder);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragment, tag);
        transaction.commitAllowingStateLoss();
        return fragment;
    }

    public static MaterialDialogFragment showDialog(MaterialDialogBuilder builder, Fragment fragment) {
        FragmentManager manager = fragment.getFragmentManager();
        String tag = builder.getTag();
        MaterialDialogFragment dialogFragment = MaterialDialogFragment.buildAlertDialog(builder);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(dialogFragment, tag);
        transaction.commitAllowingStateLoss();
        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialDialogBuilder builder = getArguments().getParcelable(DATA_MODEL);
        return  apply(builder);
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        String tag = getTag();
        if (onDialogClickListener != null) {
            onDialogClickListener.onCancelEvent(tag);
        }
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    private Dialog apply(final MaterialDialogBuilder builder) {
        View view = null;
        Dialog dialog;

        if (builder.isProgressDialog()) {
            dialog = new ProgressDialog(getActivity());
            ((ProgressDialog) dialog).setIndeterminate(true);
            ((ProgressDialog) dialog).setMessage(builder.getMessage());
            setCancelable(builder.isCancelable());
        } else if (builder.isCustomWindow()) {
            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            view = LayoutInflater.from(getActivity()).inflate(builder.getLayoutResId(), null);
            dialog.setContentView(view);

            Window oobPreviewWindow = dialog.getWindow();
            oobPreviewWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            oobPreviewWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            oobPreviewWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            setCustomDialog(builder, dialog, view, false);
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            if (builder.getLayoutResId() != 0) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                view = inflater.inflate(builder.getLayoutResId(), null);
                alertDialog.setView(view);
                if (!builder.isCustomButton()) {
                    setButtons(builder, alertDialog);
                }
                if (view.findViewById(R.id.dialog_title) != null) {
                    TextView dialogTitle = view.findViewById(R.id.dialog_title);

                    if (StringUtils.isNotEmpty(builder.getTitle())) {
                        dialogTitle.setText(builder.getTitle());
                        dialogTitle.setVisibility(View.VISIBLE);
                    } else {
                        dialogTitle.setVisibility(View.GONE);
                    }
                }
            } else {
                if (builder.getMessage() != null) {
                    alertDialog.setMessage(builder.getMessage());
                }
                setButtons(builder, alertDialog);
                if (builder.getTitle() != null) {
                    alertDialog.setTitle(builder.getTitle());
                }
            }

            if (builder.getDrawableResId() != 0) {
                alertDialog.setIcon(builder.getDrawableResId());
            }

            setCancelable(builder.isCancelable());
            dialog = alertDialog.create();
            setCustomDialog(builder, dialog, view, true);
        }
        return dialog;
    }

    private void setButtons(final MaterialDialogBuilder builder, final AlertDialog.Builder dialogBuilder) {
        if (builder.getPositiveButtonText() != null) {
            dialogBuilder.setPositiveButton(builder.getPositiveButtonText(),
                    ((dialog, which) -> {
                        if (onDialogClickListener != null) {
                            onDialogClickListener.onPositiveButtonClicked(builder.getPositiveButtonData(), builder.getTag());
                        }
                    }));
            dialogBuilder.setNegativeButton(builder.getNegativeButtonText(),
                    ((dialog, which) -> {
                        if (onDialogClickListener != null) {
                            onDialogClickListener.onNegativeButtonClicked(builder.getTag());
                        }
                    }));

            dialogBuilder.setNeutralButton(builder.getNegativeButtonText(),
                    ((dialog, which) -> {
                        if (onDialogClickListener != null) {
                            onDialogClickListener.onNeutralButtonClicked(builder.getTag());
                        }
                    }));
        }
    }

    private void setCustomDialog(final MaterialDialogBuilder builder, final Dialog dialog, View containerView, boolean isUpperCase) {
        if (containerView == null) return;

        if (builder.getMessage() != null) {
            TextView message = containerView.findViewById(R.id.dialog_message);
            message.setVisibility(View.VISIBLE);
            message.setText(builder.getMessage());
        }

        if (builder.isCustomButton()) {
            if (builder.getPositiveButtonText() != null) {
                Button button = containerView.findViewById(R.id.positive_button);
                if (isUpperCase) {
                    button.setText(builder.getPositiveButtonText().toUpperCase());
                }
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(view -> {
                    if (onDialogClickListener != null) {
                        onDialogClickListener.onPositiveButtonClicked(builder.getPositiveButtonData(), builder.getTag());
                    }
                    dialog.dismiss();
                });
            }

            if (builder.getNegativeButtonText() != null) {
                MaterialButton button = containerView.findViewById(R.id.negative_button);
                if (isUpperCase) {
                    button.setText(builder.getNegativeButtonText().toUpperCase());
                }
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(v -> {
                    if (onDialogClickListener != null) {
                        onDialogClickListener.onNegativeButtonClicked(builder.getTag());
                    }
                    dialog.dismiss();
                });
            }

            MaterialButton neutralButton = containerView.findViewById(R.id.neutral_button);
            if (builder.getNegativeButtonText() != null && neutralButton != null) {
                if (isUpperCase) {
                    neutralButton.setText(builder.getNeutralTextButton().toUpperCase());
                }
                neutralButton.setVisibility(View.VISIBLE);
                neutralButton.setOnClickListener(v -> {
                    if (onDialogClickListener != null) {
                        onDialogClickListener.onNeutralButtonClicked(builder.getTag());
                    }
                    dialog.dismiss();
                });
            }
        }
    }

    public interface OnDialogClickListener {
        void onPositiveButtonClicked(Serializable data, String tag);
        void onNegativeButtonClicked(String tag);
        void onNeutralButtonClicked(String tag);
        void onCancelEvent(String tag);
    }
}