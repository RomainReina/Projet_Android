package com.example.projet_android;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {


    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private String username;
    @ColumnInfo
    private String password;
    @ColumnInfo(name = "steps")
    private String steps;
    @ColumnInfo(name = "imc")
    private float imc;
    @ColumnInfo
    private double height;
    @ColumnInfo
    private int weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public int ComputeIMC()
    {
        setImc(Math.round(getWeight() / Math.pow(getHeight(),2)));
        return ((int)getImc());
    }


}