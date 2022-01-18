package com.example.ksiazka.datebase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "przepis")
public class PrzepisEntity {

    @PrimaryKey
    Long id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "wykonanie")
    String wykonanie;

    @ColumnInfo(name = "skladnik0")
    String skladnik0;

    @ColumnInfo(name = "skladnik1")
    String skladnik1;

    @ColumnInfo(name = "skladnik2")
    String skladnik2;

    @ColumnInfo(name = "skladnik3")
    String skladnik3;

    @ColumnInfo(name = "skladnik4")
    String skladnik4;

    @ColumnInfo(name = "skladnik5")
    String skladnik5;

    @ColumnInfo(name = "skladnik6")
    String skladnik6;

    @ColumnInfo(name = "poziom")
    float poziom;

    public float getPoziom() {
        return poziom;
    }

    public void setPoziom(float poziom) {
        this.poziom = poziom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWykonanie() {
        return wykonanie;
    }

    public void setWykonanie(String wykonanie) {
        this.wykonanie = wykonanie;
    }

    public String getSkladnik0() {
        return skladnik0;
    }

    public void setSkladnik0(String skladnik0) {
        this.skladnik0 = skladnik0;
    }

    public String getSkladnik1() {
        return skladnik1;
    }

    public void setSkladnik1(String skladnik1) {
        this.skladnik1 = skladnik1;
    }

    public String getSkladnik2() {
        return skladnik2;
    }

    public void setSkladnik2(String skladnik2) {
        this.skladnik2 = skladnik2;
    }

    public String getSkladnik3() {
        return skladnik3;
    }

    public void setSkladnik3(String skladnik3) {
        this.skladnik3 = skladnik3;
    }

    public String getSkladnik4() {
        return skladnik4;
    }

    public void setSkladnik4(String skladnik4) {
        this.skladnik4 = skladnik4;
    }

    public String getSkladnik5() {
        return skladnik5;
    }

    public void setSkladnik5(String skladnik5) {
        this.skladnik5 = skladnik5;
    }

    public String getSkladnik6() {
        return skladnik6;
    }

    public void setSkladnik6(String skladnik6) {
        this.skladnik6 = skladnik6;
    }

}
