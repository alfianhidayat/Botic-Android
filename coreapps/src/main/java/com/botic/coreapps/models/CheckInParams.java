package com.botic.coreapps.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by alfianh on 7/10/17.
 */

public class CheckInParams {
    String phone;
    int visitor_number;
    int long_visit;
    List<Visitor> visitors;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVisitor_number() {
        return visitor_number;
    }

    public void setVisitor_number(int visitor_number) {
        this.visitor_number = visitor_number;
    }

    public int getLong_visit() {
        return long_visit;
    }

    public void setLong_visit(int long_visit) {
        this.long_visit = long_visit;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public static class Visitor {
        String name;
        int age;
        String origin;

        public Visitor() {
        }

        public Visitor(String name, int age, String origin) {
            this.name = name;
            this.age = age;
            this.origin = origin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }
    }
}
