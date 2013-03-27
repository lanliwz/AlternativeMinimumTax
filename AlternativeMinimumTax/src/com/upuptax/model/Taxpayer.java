package com.upuptax.model;

import java.util.Date;

public class Taxpayer {
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private String socialSecurityNo;
	private String occupation;
	private Date dateOfBirth;
	private int age;
	private boolean legallyBlind=false;
	private Date dateOfDeath;
	private boolean canBeClaimedAsDependent=false;
	private boolean isClaimedAsDependent=false;
	
}
