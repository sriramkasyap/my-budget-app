package org.sriramkasyap.mybudgetapp;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiManager;
import org.sriramkasyap.mybudgetapp.NetworkUtils.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Sriram Kasyap on 12-10-2017.
 */

public class BudgetUtils {
    private static float MonthlyBudget = Float.valueOf(0);
    private static float TotalExpenditure = Float.valueOf(0);
    private static float AddedExpenditure = Float.valueOf(0);
    private static float PlannedExpenditure = Float.valueOf(0);
    private static float BudgetLeftForMonth = Float.valueOf(0);
    private static float BudgetForToday = Float.valueOf(0);
    private static float TodayExpenditure = Float.valueOf(0);
    private static int NoDaysInMonth = 0;
    private static int NoDaysLeft = 0;
    private static int NoOfDay = 0;


    public static void Init(AppCompatActivity requestingActivity) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requestingActivity);
        setMonthlyBudget(Float.parseFloat(sharedPreferences.getString("monthly_budget", "0")));
        setPlannedExpenditure(Float.parseFloat(sharedPreferences.getString("planned_expenditure", "0")));
        setTotalExpenditure(Float.parseFloat(sharedPreferences.getString("previous_expenditure", "0")));
        setBudgetLeftForMonth(MonthlyBudget - TotalExpenditure);

    }

    public static float getMonthlyBudget() {
        return MonthlyBudget;
    }

    public static void setMonthlyBudget(float monthlyBudget) {
        MonthlyBudget = monthlyBudget;
    }

    public static float getTotalExpenditure() {
        return TotalExpenditure;
    }

    public static void setTotalExpenditure(float totalPreviousExpenditure) {
        TotalExpenditure = totalPreviousExpenditure + AddedExpenditure;
    }

    public static float getPlannedExpenditure() {
        return PlannedExpenditure;
    }

    public static void setAddedExpenditure(float FromAddedExpenditure) {
        AddedExpenditure = FromAddedExpenditure;
    }

    public static void setPlannedExpenditure(float plannedExpenditure) {
        PlannedExpenditure = plannedExpenditure;
    }

    public static float getBudgetLeftForMonth() {
        return BudgetLeftForMonth;
    }

    public static void setBudgetLeftForMonth(float budgetLeftForMonth) {
        BudgetLeftForMonth = budgetLeftForMonth;
    }

    public static float getBudgetForToday() {
        return BudgetForToday;
    }

    public static void setBudgetForToday(float budgetForToday) {
        BudgetForToday = budgetForToday;
    }

    public static float getTodayExpenditure() {
        return TodayExpenditure;
    }

    public static void setTodayExpenditure(float todayExpenditure) {
        TodayExpenditure = todayExpenditure;
    }

    public static float getNoDaysInMonth() {
        return NoDaysInMonth;
    }

    public static void setNoDaysInMonth(int noDaysInMonth) {
        NoDaysInMonth = noDaysInMonth;
    }

    public static float getNoDaysLeft() {
        return NoDaysLeft;
    }

    public static void setNoDaysLeft(int noDaysLeft) {
        NoDaysLeft = noDaysLeft;
    }

    public static float getNoOfDay() {
        return NoOfDay;
    }

    public static void setNoOfDay(int noOfDay) {
        NoOfDay = noOfDay;
    }
}
