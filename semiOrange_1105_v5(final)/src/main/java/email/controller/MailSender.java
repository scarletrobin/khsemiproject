package email.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	

	public String mailAuthCodeSend(String email) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int flag = r.nextInt(3);
			if(flag == 0) {
				int randomNum = r.nextInt(10);
				sb.append(randomNum);
			}else if(flag==1) {
				char randomChar = (char)(r.nextInt(26)+65);
				sb.append(randomChar);
			}else if(flag==2) {
				char randomChar = (char)(r.nextInt(26)+97);
				sb.append(randomChar);				
			}
		}
		System.out.println("테스트용 인증코드 : "+sb.toString());
		
		/*
		
		///////////
		//자바 8버전//
		///////////


		Properties prop = System.getProperties();
		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		

		*/
		
		////////////////
		//자바 버전 11이상//
		////////////////
		
		
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587); //추가
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		

		
		
		
		//인증정보 설정(gmail 로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication("khprjteam2@gmail.com", "tpalvmfhwprxm");
				return pa;
			}
		});
		
		//이메일을 작성해서 전송하는 객체를 생성함
		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.setSentDate(new Date());		//메일 전송날짜 설정
			//보내는사람 정보
			msg.setFrom(new InternetAddress("khprjteam2@gmail.com","오렌지마켓"));
			//받는사람정보
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			//이메일 제목설정
			msg.setSubject("인증번호 입니다","UTF-8");
			//이메일 내용설정
			msg.setContent("<h1>안녕하세요 인증번호 입니다.</h1>"+
							"<p>당신의 인증 번호는 "+sb.toString()+" 입니다.</p>"+
							"<p>5분 지나면 인증번호 무효</p>","text/html;charset=UTF-8");
			//이메일 전송
			Transport.send(msg);
			result = true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result) {
			return sb.toString(); 
		}else {
			return null;
		}
	}

	public void findPwMailAuthCodeSend(String email, String memberId, String memberPw) {
		// TODO Auto-generated method stub
		Properties prop = System.getProperties();

		/*


		///////////
		//자바 8버전//
		///////////

		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		
		*/
		
		
		////////////////
		//자바 11버전 이상//
		////////////////
		
		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587); //추가
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		//인증정보 설정(테스트용 지메일계정 로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication("khprjteam2@gmail.com", "tpalvmfhwprxm");
				return pa;
			}
		});
		
		//이메일을 작성해서 전송하는 객체를 생성함
		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.setSentDate(new Date());		//메일 전송날짜 설정
			//보내는사람 정보
			msg.setFrom(new InternetAddress(email, "오렌지마켓"));
			//받는사람정보
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			//이메일 제목설정
			msg.setSubject(memberId+"님, 비밀번호 찾기 결과 입니다.","UTF-8");
			//이메일 내용설정
			msg.setContent("<h1>안녕하세요, "+memberId+"님!</h1>"+"<p>당신의 비밀번호는 "+memberPw+"입니다!","text/html;charset=UTF-8");
			//이메일 전송
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findIdMailAuthCodeSend(String email, String memberName, String memberId) {
		// TODO Auto-generated method stub
		Properties prop = System.getProperties();
		
		/*


		///////////
		//자바 8버전//
		///////////
		
		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		
		*/
		
		////////////////
		//자바 11버전 이상//
		////////////////
		
		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		
		//인증정보 설정(테스트용 지메일계정 로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication("khprjteam2@gmail.com", "tpalvmfhwprxm");
				return pa;
			}
		});
		
		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.setSentDate(new Date());
			//보내는사람 정보
			msg.setFrom(new InternetAddress(email,"오렌지마켓"));
			//받는사람정보
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			//이메일 제목설정
			msg.setSubject(memberName+"님, 아이디 찾기 결과 입니다.","UTF-8");
			//이메일 내용설정
			msg.setContent("<h1>안녕하세요, "+memberName+"님!</h1>"+"<p>당신의 아이디는 "+memberId+"입니다!","text/html;charset=UTF-8");
			//이메일 전송
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
