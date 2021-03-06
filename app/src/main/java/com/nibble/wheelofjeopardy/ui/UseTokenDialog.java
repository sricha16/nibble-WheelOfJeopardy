package com.nibble.wheelofjeopardy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.nibble.wheelofjeopardy.R;
import com.nibble.wheelofjeopardy.game.GameManager;

public class UseTokenDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("You have ");
        messageBuilder.append(GameManager.getGame().getCurrentPlayer().getFreeSpins());
        messageBuilder.append(" tokens, would you like to use one and take another turn?");
        dialogBuilder.setMessage(messageBuilder.toString());
        dialogBuilder.setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GameManager.getGame().getCurrentPlayer().useFreeSpin();
                if (getActivity() instanceof WheelActivity) {
                    ((WheelActivity) getActivity()).endTurn(false);
                } else if (getActivity() instanceof AnswerActivity) {
                    ((AnswerActivity) getActivity()).useFreeSpin(true);
                } else if (getActivity() instanceof QuestionActivity) {
                    ((QuestionActivity) getActivity()).useFreeSpin(true);
                }
            }
        });
        dialogBuilder.setNegativeButton(R.string.no_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (getActivity() instanceof WheelActivity) {
                    ((WheelActivity) getActivity()).endTurn(true);
                } else if (getActivity() instanceof AnswerActivity) {
                    ((AnswerActivity) getActivity()).useFreeSpin(false);
                } else if (getActivity() instanceof QuestionActivity) {
                    ((QuestionActivity) getActivity()).useFreeSpin(false);
                }
            }
        });
        return dialogBuilder.create();
    }
}
