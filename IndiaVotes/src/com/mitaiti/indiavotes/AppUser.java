package com.mitaiti.indiavotes;

enum Gender{MALE,FEMALE};
public class AppUser {
	String name;
	Gender gender;
	int age;
	int topic,question;
	
	protected void setAppUser(String name, int age, Gender g){
		this.name=name;
		gender = g;
		this.age=age;
	}
	
	protected Gender getGender(){
		return this.gender;
	}

	protected int getAge(){
		return this.age;
	}
	
	protected String getName(){
		return this.name;
	}
	
	protected void setTopic(int topic){
		this.topic=topic;
	}
	
	protected void setQuestion(int q){
		this.question=q;
	}
}
