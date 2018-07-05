package com.example.q.Tab1andTab2_01;

import java.util.Comparator;

public class mComparator implements Comparator<ContentModel> {
    @Override
    public int compare(ContentModel c1, ContentModel c2) {
        int year1 = c1.getYear();
        int year2 = c2.getYear();
        int month1 = c1.getMonth();
        int month2 = c2.getMonth();
        int day1 = c1.getDay();
        int day2 = c2.getDay();
        int hour1 = c1.getHour();
        int hour2 = c2.getHour();
        int min1 = c1.getMinute();
        int min2 = c2.getMinute();

        if(year1 > year2) {
            return 1;
        } else if(year1 < year2) {
            return -1;
        } else {
            if(month1 > month2) {
                return 1;
            } else if(month1 < month2) {
                return -1;
            } else {
                if (day1 > day2) {
                    return 1;
                } else if(day1 < day2) {
                    return -1;
                } else {
                    if(hour1 > hour2) {
                        return 1;
                    } else if (hour1 < hour2) {
                        return -1;
                    } else {
                        if (min1 > min2)
                            return 1;
                        else if (min1 < min2)
                            return -1;
                        else
                            return 0;
                    }
                }
            }
        }
    }
}
