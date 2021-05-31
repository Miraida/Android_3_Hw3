package com.geek.android3_3.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.geek.android3_3.R;

public class CustomAlertDialog {
    public static void showDialog(Context context,Listener listener){
         new AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_delete)
                .setTitle("Внимание!")
                .setMessage("Вы уверены что хотите удалить запись?")
                .setPositiveButton("Да", (dialog, which) -> listener.onPositiveButtonClick())
                .setNegativeButton("Отменить",null)
                .show();

    }
    public interface Listener{
        void onPositiveButtonClick();
    }
}
