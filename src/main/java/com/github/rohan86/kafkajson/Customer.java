package com.github.rohan86.kafkajson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.StringTokenizer;

public class Customer {

    String Name;
    int Age;
    int Salary;

    public Customer(){};
    public Customer(String Name,int Age,int Salary){
        this.Name=Name;
        this.Age=Age;
        this.Salary=Salary;
    }

    public void parseString(String csvStr) {
        StringTokenizer st = new StringTokenizer(csvStr, ",");
        Name = st.nextToken();
        Age = Integer.parseInt(st.nextToken());
        Salary = Integer.parseInt(st.nextToken());
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Integer getSalary() {
        return Salary;
    }

    public void setSalary(Integer salary) {
        Salary = salary;
    }


    //public String toString(){
      //  return "Customer--> " + "  Name = " + Name + "   Age = "  + Age + "   Salary = " + Salary;
    //}

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Hi");
        Customer c = new Customer("Rohan",34,1000);
        //System.out.println(c);
        System.out.println(mapper.writeValueAsString(c));
        c.parseString("Shunali,34,100000");
        System.out.println(mapper.writeValueAsString(c));

    }






}
