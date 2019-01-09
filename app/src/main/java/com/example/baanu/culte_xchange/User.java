package com.example.baanu.culte_xchange;

public class User {
    private String FirstName;
    private String LastName;
    private String Country;
    private String Email;
    private String Password;
    private String Birthdate;

    public User() {
    }
    public User (String FirstName,String LastName , String Country ,String Email ,String Password,String Birthday){

    }

    public String getBirthdate() {
        return Birthdate;
    }

    public String getCountry() {
        return Country;
    }

    public String getEmail() {
        return Email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setName(String name) {
        this.LastName = name;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

   public void setBirthdate(String birthday) {
        this.Birthdate = birthday;
    }
}
