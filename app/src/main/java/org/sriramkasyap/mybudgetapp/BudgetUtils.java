package org.sriramkasyap.mybudgetapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.sriramkasyap.mybudgetapp.NetworkUtils.TransactionItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    private static float BudgetLeftForToday = Float.valueOf(0);
    private static float TodayExpenditure = Float.valueOf(0);
    private static int NoDaysInMonth = 0;
    private static int NoDaysLeft = 0;
    private static int NoOfDay = 0;


    public static void Init(AppCompatActivity requestingActivity) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requestingActivity);
        setMonthlyBudget(Float.parseFloat(sharedPreferences.getString("monthly_budget", "0")));
        setPlannedExpenditure(Float.parseFloat(sharedPreferences.getString("planned_expenditure", "0")));
        setTotalExpenditure(Float.parseFloat(sharedPreferences.getString("previous_expenditure", "0")));
        setBudgetLeftForMonth(MonthlyBudget - TotalExpenditure - PlannedExpenditure);

        Calendar calendar = Calendar.getInstance();
        int NoOfDays = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        int NoOfToDay = calendar.get(Calendar.DAY_OF_MONTH);
        setNoDaysInMonth(NoOfDays);
        setNoOfDay(NoOfToDay);
        setNoDaysLeft(NoOfDays - NoOfToDay + 1);
        setBudgetForToday(getBudgetLeftForMonth()/getNoDaysLeft());
        setBudgetLeftForToday(getBudgetForToday() -  getTodayExpenditure());

    }


    public static float getBudgetLeftForToday() {
        return BudgetLeftForToday;
    }

    public static void setBudgetLeftForToday(float budgetLeftForToday) {
        BudgetLeftForToday = budgetLeftForToday;
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

    public static void setTodayExpenditure(ArrayList<TransactionItem> transactionList) {
        int pos = 0;
        float sum = 0;
        Calendar calendar = Calendar.getInstance();
        Calendar transacionCal = Calendar.getInstance();

//        int today = calendar.get(Calendar.DATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = new Date();
        calendar.setTime(today);
        Log.d("today", String.valueOf(today));

        while(pos < transactionList.size()) {
            TransactionItem currentItem = transactionList.get(pos);
            try {
                Date transactionDate = format.parse(currentItem.getTransactionTimeCreated());
                transacionCal.setTime(transactionDate);
                Log.d("transactionDate", String.valueOf(transactionDate));
                if(calendar.get(Calendar.YEAR) == transacionCal.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == transacionCal.get(Calendar.DAY_OF_YEAR)) {
                    sum += currentItem.getTransactionValue();
                }


            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d(currentItem.getTransactionTitle(), String.valueOf(currentItem.getTransactionValue()));
            Log.d("Sum", String.valueOf(sum));
            pos++;
        }
        TodayExpenditure = sum;
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

    public static void setExpenditures(ArrayList<TransactionItem> expenditures) {
//        BudgetUtils.expenditures = expenditures;
        setTodayExpenditure(expenditures);
        setAddedExpenditure(expenditures);
    }
}
