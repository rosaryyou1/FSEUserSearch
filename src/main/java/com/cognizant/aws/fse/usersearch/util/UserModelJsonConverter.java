package com.cognizant.aws.fse.usersearch.util;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.aws.fse.usersearch.json.domain.Skill;
import com.cognizant.aws.fse.usersearch.json.domain.UserJsonModel;
import com.cognizant.aws.fse.usersearch.model.User;

public class UserModelJsonConverter {
	public static UserJsonModel convertUser(User user) {
		UserJsonModel userJsonModel = new UserJsonModel();
		userJsonModel.setUserId(user.getUserId());
		userJsonModel.setAssociateId(user.getAssociateId());
		userJsonModel.setAssociateName(user.getAssociateName());
		userJsonModel.setEmail(user.getEmail());
		userJsonModel.setMobile(user.getMobile());
		List<Skill> lstSkill = new ArrayList<>();
		Skill angularSkill = new Skill();
		Skill aptitudeSkill = new Skill();
		Skill awsSkill = new Skill();
		Skill communicationSkill = new Skill();
		Skill dockerSkill = new Skill();
		Skill gitSkill = new Skill();
		Skill hibernateSkill = new Skill();
		Skill htmlCssSkill = new Skill();
		Skill jenkinsSkill = new Skill();
		Skill reactSkill = new Skill();
		Skill restSkill = new Skill();
		Skill spokenSkill = new Skill();
		Skill springSkill = new Skill();
		
		angularSkill.setSkillLevel(user.getAngularLevel());
		angularSkill.setSkillName(user.getAngular());
		angularSkill.setSkillType("Tech");
		aptitudeSkill.setSkillLevel(user.getAptitudeLevel());
		aptitudeSkill.setSkillName(user.getAptitude());
		aptitudeSkill.setSkillType("Non-Tech");
		awsSkill.setSkillLevel(user.getAwsLevel());
		awsSkill.setSkillName(user.getAws());
		awsSkill.setSkillType("Tech");
		communicationSkill.setSkillLevel(user.getCommunicationLevel());
		communicationSkill.setSkillName(user.getCommunication());
		communicationSkill.setSkillType("Non-Tech");
		dockerSkill.setSkillLevel(user.getDockerLevel());
		dockerSkill.setSkillName(user.getDocker());
		dockerSkill.setSkillType("Tech");
		gitSkill.setSkillLevel(user.getGitLevel());
		gitSkill.setSkillName(user.getGit());
		gitSkill.setSkillType("Tech");
		hibernateSkill.setSkillLevel(user.getHibernateLevel());
		hibernateSkill.setSkillName(user.getHibernate());
		hibernateSkill.setSkillType("Tech");
		htmlCssSkill.setSkillLevel(user.getHtmlCssLevel());
		htmlCssSkill.setSkillName(user.getHtmlCss());
		htmlCssSkill.setSkillType("Tech");
		jenkinsSkill.setSkillLevel(user.getJenkinsLevel());
		jenkinsSkill.setSkillName(user.getJenkins());
		jenkinsSkill.setSkillType("Tech");
		reactSkill.setSkillLevel(user.getReactLevel());
		reactSkill.setSkillName(user.getReact());
		reactSkill.setSkillType("Tech");
		restSkill.setSkillLevel(user.getRestLevel());
		restSkill.setSkillName(user.getRest());
		restSkill.setSkillType("Tech");
		spokenSkill.setSkillLevel(user.getSpokenLevel());
		spokenSkill.setSkillName(user.getSpoken());
		spokenSkill.setSkillType("Non-Tech");
		springSkill.setSkillLevel(user.getSpringLevel());
		springSkill.setSkillName(user.getSpring());
		springSkill.setSkillType("Tech");
		
		lstSkill.add(angularSkill);
		lstSkill.add(aptitudeSkill);
		lstSkill.add(awsSkill);
		lstSkill.add(communicationSkill);
		lstSkill.add(dockerSkill);
		lstSkill.add(gitSkill);
		lstSkill.add(hibernateSkill);
		lstSkill.add(htmlCssSkill);
		lstSkill.add(jenkinsSkill);
		lstSkill.add(reactSkill);
		lstSkill.add(restSkill);
		lstSkill.add(spokenSkill);
		lstSkill.add(springSkill);
		
		userJsonModel.setSkills(lstSkill);

		return userJsonModel;
	}
}
