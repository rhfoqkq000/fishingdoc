package com.example.kim.fishingdoc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by kim on 16. 8. 10.
 */
public class CalenderFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    //버튼에 선택한 날짜 나타내기
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Button button = (Button)getActivity().findViewById(R.id.bottoncalendar);
        if(month<10){
            String stringOfDate = "0"+(month+1) +"/" + day + "/" + year ;
            button.setText(stringOfDate);
        }else{
            String stringOfDate = (month+1) +"/" + day + "/" + year ;
            button.setText(stringOfDate);
        }
    }
}
