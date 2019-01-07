package com.example.baanu.culte_xchange;

public class User {
    private String FirstName;
    private String Name;
    private String Country;
    private String Email;
    private String Password;
    private String Birthday;

    public User() {
    }
    public User (String FirstName,String Name , String Country ,String Email ,String Password,String Birthday){
        this.FirstName= FirstName;
        this.Birthday=Birthday;
        this.Country=Country;
        this.Email=Email;
        this.Password=Password;
        this.Name=Name;
    }

    public String getBirthday() {
        return Birthday;
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

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }
}
