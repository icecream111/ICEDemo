package com.ice.retrofitrxjavademo.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ICE on 2017/12/18.
 * 生日选择
 */
@Route(path = "/ice/activity/BirthDayActivity")
public class BirthDayActivity extends BaseActivity {
    @BindView(R.id.tv_birthdat)
    TextView mTvBirthdat;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_birthday);
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {

    }

    /**
     * 生日选择
     */
    private void showBirthDay() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(BirthDayActivity.this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        int mYear = year;
                        int mMonth = month;
                        int mDay = day;
                        //更新EditText控件日期 小于10加0
                        mTvBirthdat.setText(new StringBuilder()
                                .append(mYear)
                                .append("-")
                                .append((mMonth + 1) < 10 ? 0 + (mMonth + 1) : (mMonth + 1))
                                .append("-")
                                .append((mDay < 10) ? 0 + mDay : mDay));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));


        //设置时间范围
        datePickerDialog.getDatePicker().setMinDate(999999999);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }


    @OnClick(R.id.tv_select)
    public void onViewClicked() {
        showBirthDay();
    }
}
