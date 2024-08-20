package com.example.affectation;

import java.util.Comparator;

public class Student {
    private double note;
    private String name;
    private Choice choice1;
    private Choice choice2;
    private Choice choice3;
    private Choice choice4;
    private Choice affectation;

    public Student(double note, String text, String text1, String text2, String text3, String text4) {
    }

    public Student() {

    }

    public double getNote() {
        return note;
    }


    public void setNote(double note) {
        this.note = note;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Choice getChoice1() {
        return choice1;
    }


    public void setChoice1(Choice choice1) {
        this.choice1 = choice1;
    }


    public Choice getChoice2() {
        return choice2;
    }


    public void setChoice2(Choice choice2) {
        this.choice2 = choice2;
    }


    public Choice getChoice3() {
        return choice3;
    }


    public void setChoice3(Choice choice3) {
        this.choice3 = choice3;
    }


    public Choice getChoice4() {
        return choice4;
    }


    public void setChoice4(Choice choice4) {
        this.choice4 = choice4;
    }


    public Choice getAffectation() {
        return affectation;
    }


    public void setAffectation(Choice affectation) {
        this.affectation = affectation;
    }


    public Student(double note, String name, Choice choice1, Choice choice2, Choice choice3, Choice choice4) {
        this.note = note;
        this.name = name;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
    }

/*
        //tester et afficher la classe
    public void printdetails() {
        System.out.print("Name: " + this.name + "\t");
        System.out.print("Note: " + this.note + "\t");
        System.out.print("Choix1 : " + this.choice1.getChoice_name() + "\t");
        System.out.print("Choix2 : " + this.choice2.getChoice_name() + "\t");
        System.out.print("Choix3 : " + this.choice3.getChoice_name() + "\t");
        System.out.print("Choix4 : " + this.choice4.getChoice_name() + "\n");

    }
        //tester et afficher l'affectation
    public void printdetailsaffectation() {
        System.out.print("Name: " + this.name + "\t");
        System.out.print("Note: " + this.note + "\t");
        System.out.print("Choix1 : " + this.choice1.getChoice_name() + "\t");
        System.out.print("Choix2 : " + this.choice2.getChoice_name() + "\t");
        System.out.print("Choix3 : " + this.choice3.getChoice_name() + "\t");
        System.out.print("Choix4 : " + this.choice4.getChoice_name() + "\t");
        System.out.println("Affectation : " + this.affectation.getChoice_name());

    }
*/


}

class SortbyNote implements Comparator<Student> {
    //classe SortbyNote sert à trier par ordre croissant les étudiants par rapport à leurs notes
    public int compare(Student a, Student b) {
        return Double.compare(a.getNote(), b.getNote());
    }
}