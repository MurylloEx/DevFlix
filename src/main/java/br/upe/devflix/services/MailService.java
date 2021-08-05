package br.upe.devflix.services;

import org.springframework.stereotype.Service;

import br.com.muryllo.jmailer.Mailer;
import br.com.muryllo.jmailer.MailerResponse;

@Service
public class MailService {

  private Mailer createMailer(){
    return new Mailer()
      .key("api-C982E608F0D711EB8499F23C91C88F4E")
      .from("Devflix UPE", "noreply@upedevflix.herokuapp.com");
  }

  public boolean sendMailConfirmation(String userName, String userEmail, String link){
    Mailer mail = createMailer()
      .subject("[DevFlix] Confirmação de Cadastro")
      .to(userName, userEmail)
      .htmlBody(String.format(
        "Seja bem vindo ao DevFlix, %s! Para concluir seu " + 
        "cadastro, clique <a href=\"%s\">neste link</a>.", userName, link));
    
    MailerResponse result = mail.send();
    return result.success();
  }

  public boolean sendMailPasswordRecovery(String userName, String userEmail, String code){
    Mailer mail = createMailer()
      .subject("[DevFlix] Recuperação de Conta")
      .to(userName, userEmail)
      .htmlBody(String.format(
        "Olá %s, recebemos a triste notícia de que você esqueceu sua senha. " +
        "Mas não se preocupe, você poderá alterá-la utilizando este código: <b>%s</b> ." +
        "Caso este email não tenha sido solicitado por você, basta ignorá-lo.", userName, code));
    
    MailerResponse result = mail.send();
    return result.success();
  }

  public boolean sendMailNotification(String userName, String userEmail, String htmlText){
    Mailer mail = createMailer()
      .subject("[DevFlix] Você possui uma nova notificação.")
      .to(userName, userEmail)
      .htmlBody(htmlText);
    
    MailerResponse result = mail.send();
    return result.success();
  }

}
