package com.goodcub.common.email;

	import java.io.File;
    import java.io.UnsupportedEncodingException;
	import java.security.GeneralSecurityException;
	import java.util.Date;
	import java.util.Properties;
	import javax.activation.DataHandler;
	import javax.activation.FileDataSource;
	import javax.mail.Authenticator;
	import javax.mail.Message;
    import javax.mail.MessagingException;
	import javax.mail.Multipart;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
    import javax.mail.internet.AddressException;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeBodyPart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMultipart;
	import javax.mail.internet.MimeUtility;
	import com.sun.mail.util.MailSSLSocketFactory;

	public class EmailSender {
	    
		private static final String charset = "UTF-8";
	    
		private static final String defaultMimetype = "text/plain";
		
	    private static final String smtpServer = "smtp.exmail.qq.com";   //SMTP服务器名
	    
	    private static String name;   // 邮箱登录名
	    
	    private static String passWord;// 邮箱密码
	    
	    private static String mailSender;// 发件人邮箱地址
	    
	    public static void main(String[] args) throws Exception {
		   EmailSender esender = new EmailSender("registration@vcintegration.com", "vci2ME*", "registration@vcintegration.com");
	       esender.send(new String[]{"393712635@qq.com"}, "有新发现了哦", "亲赶快心动啊，不然晚了!", null , "text/html");
	    }
	   
	   public EmailSender(String name,String passWord,String mailSender){
		   this.name = name;
		   this.passWord = passWord;
	       this.mailSender = mailSender;
	   }
	   
	    /**
	     * 发送邮件
	     * @param receiver 收件人
	     * @param subject 标题
	     * @param mailContent 邮件内容
	     * @param mimetype 内容类型 默认为text/plain,如果要发送HTML内容,应设置为text/html
	     */
	    public static boolean send(String receiver, String subject, String mailContent, String mimetype) {
	      return send(new String[]{receiver}, subject, mailContent, mimetype);
	    }
	    
	    /**
	     * 发送邮件
	     * @param receivers 收件人
	     * @param subject 标题
	     * @param mailContent 邮件内容
	     * @param mimetype 内容类型 默认为text/plain,如果要发送HTML内容,应设置为text/html
	     */
	    public static boolean send(String[] receivers, String subject, String mailContent, String mimetype) {
	    	return send(receivers, subject, mailContent, null, mimetype);
	    }
	    /**
	     * 发送邮件
	     * @param receivers 收件人
	     * @param subject 标题
	     * @param mailContent 邮件内容
	     * @param attachements 附件
	     * @param mimetype 内容类型 默认为text/plain,如果要发送HTML内容,应设置为text/html
	     */
	    public static boolean send(String[] receivers, String subject, String mailContent, File[] attachements, String mimetype) {
	        
	    	boolean sendTag = true;
	    	
	    	try {
				Properties props = new Properties();
				
				String SSL_FACTORY="javax.net.ssl.SSLSocketFactory"; 
				props.setProperty("mail.transport.protocol", "smtp");
			    props.setProperty("mail.smtp.host",smtpServer) ;
			    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			    props.setProperty("mail.smtp.socketFactory.fallback", "false") ;
			    props.setProperty("mail.smtp.port","465") ;
			    props.setProperty("mail.smtp.socketFactory.port","465") ;
			    props.setProperty("mail.smtp.auth","true") ;
			    props.setProperty("mail.smtp.auth.ntlm.domain","vcintegration.com") ;
			    props.setProperty("mail.smtp.starttls.enable","true"); 
			    
			    MailSSLSocketFactory sf = null;
				
			    try {
					sf = new MailSSLSocketFactory();
					sf.setTrustAllHosts(true); 
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}  
			    props.put("mail.smtp.ssl.socketFactory", sf);
			    
				Session session = Session.getDefaultInstance(props, new Authenticator() {
				   
					protected PasswordAuthentication getPasswordAuthentication() {
				        
						return new PasswordAuthentication(name,passWord);//登录用户名/密码
				    }
				});
      
				session.setDebug(true);
				
				MimeMessage mimeMessage = new MimeMessage(session);
				   
				mimeMessage.setFrom(new InternetAddress(mailSender));//发件人邮件

			    InternetAddress[] toAddress = new InternetAddress[receivers.length];
			    
			    for (int i=0; i<receivers.length; i++) {
			       
			    	toAddress[i] = new InternetAddress(receivers[i]);
			    }
			   
			    mimeMessage.setRecipients(Message.RecipientType.TO, toAddress);//收件人邮件
			    
			    mimeMessage.setSubject(subject, charset);
			    
			    Multipart multipart = new MimeMultipart();
			   
			    //正文
			    MimeBodyPart body = new MimeBodyPart();
			    
			    //body.setText(message, charset);不支持html
			    body.setContent(mailContent, (mimetype!=null && !"".equals(mimetype) ? mimetype : defaultMimetype)+ ";charset="+ charset);
			   
			    multipart.addBodyPart(body);//发件内容
			    
			    //附件
			    if(attachements!=null){
			       
			    	for (File attachement : attachements) {
			           
			    		MimeBodyPart attache = new MimeBodyPart();
			          
			    		//ByteArrayDataSource bads = new ByteArrayDataSource(byte[],"application/x-any");
			            
			    		attache.setDataHandler(new DataHandler(new FileDataSource(attachement)));
			           
			    		String fileName = getLastName(attachement.getName());
			            
			    		attache.setFileName(MimeUtility.encodeText(fileName, charset, null));
			            
			    		multipart.addBodyPart(attache);
			        }
			    }
			    mimeMessage.setContent(multipart);

			    mimeMessage.setSentDate(new Date()); //formcat.parse("2010-5-23")
			    
			    Transport.send(mimeMessage);
			    
			} catch (AddressException e) {
				
				sendTag = false;	
				e.printStackTrace();
			
			} catch (UnsupportedEncodingException e) {
				
				sendTag = false;
				e.printStackTrace();
			
			} catch (MessagingException e) {
				
				sendTag = false;
				e.printStackTrace();
				
			} catch (Exception e) {
				
				sendTag = false;
				e.printStackTrace();
				
			}       
	        
	      return sendTag;
	      
	    }

	    private static String getLastName(String fileName) {
	       
	    	int pos = fileName.lastIndexOf("\\");
	        
	        if (pos > -1) {
	            fileName = fileName.substring(pos + 1);
	        }
	        
	        pos = fileName.lastIndexOf("/");
	        
	        if (pos > -1) {
	            fileName = fileName.substring(pos + 1);
	        }
	        return fileName;
	    }
	    
	}