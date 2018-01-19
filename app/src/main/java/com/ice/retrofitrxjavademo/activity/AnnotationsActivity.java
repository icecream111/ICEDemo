package com.ice.retrofitrxjavademo.activity;

import android.support.annotation.IntDef;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ice.retrofitrxjavademo.R;
import com.ice.retrofitrxjavademo.base.BaseActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ICE on 2017/12/20.
 * 注解的使用
 */
@Route(path = "/ice/activity/AnnotationsActivity")
public class AnnotationsActivity extends BaseActivity {
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    @IntDef({MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY})

    @Retention(RetentionPolicy.SOURCE)
    public @interface WeekDays {
    }

    @WeekDays
    int currentDay = WEDNESDAY;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_annotations);
    }

    @Override
    protected void initializeView() {
        setCurrentDay(WEDNESDAY);

        @WeekDays int today = getCurrentDay();
        switch (today) {
            case MONDAY:
                break;
            case TUESDAY:
                break;
            case WEDNESDAY:
                break;
            case THURSDAY:
                break;
            case FRIDAY:
                break;
            case SATURDAY:
                break;
            case SUNDAY:
                break;
            default:
                break;
        }
    }

    @WeekDays
    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * 参数只能传入在声明范围内的整形，不然编译不通过
     *
     * @param currentDay
     */
    public void setCurrentDay(@WeekDays int currentDay) {
        this.currentDay = currentDay;
    }

    @Override
    protected void initializeListener() {

    }

    @Override
    protected void initializeData() {

    }
}
