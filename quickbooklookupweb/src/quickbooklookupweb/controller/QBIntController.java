package quickbooklookupweb.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;

@Controller
public class QBIntController {

	String clientid = "ABOBnWeI0IbIhTLp75Cv19bjf8BYJmNqMGvUZgA8E3DTlkNfc8";
	String clientSecret = "uKdTH3l9DyAFZ9Xtfhka6Esh9qzIp5Zm2rT601qz";
	String redirectURL = "http://localhost:8080/oauth2redirect";
	String IntuitAccountingAPIHost = "https://sandbox-quickbooks.api.intuit.com";
	
		
  @RequestMapping("/connectquickbook")
  protected RedirectView QuickBookConnect() throws Exception {
		
	  try {
		  System.out.println("THIS IS WHERE THE QUICKBOOK CONNECTION WILL BE CALLED");
		  OAuth2Config oauth2config = new OAuth2Config.OAuth2ConfigBuilder
				  (clientid, clientSecret).callDiscoveryAPI(Environment.SANDBOX).buildConfig();
		  
		  System.out.println("CONNECTED TO QUICKBOOK");
		  // GENERATING CSRF TOKEN
		  String csrf = oauth2config.generateCSRFToken();
		  // SCOPES
		  ArrayList<Scope> scopes = new ArrayList<Scope>();
		  scopes.add(Scope.Accounting);
		  
		  
		  return new RedirectView(oauth2config.prepareUrl(scopes, redirectURL,csrf), true, true, false);
				  
	  }catch(Exception exi){
		  exi.printStackTrace();
	  }
	return null;
	 /*
		 * View view = this.ConnectToQuickBook(); return new
		 * ModelAndView("qblanding","qblanding",payload);
	 */  
}
	  	  
  private View ConnectToQuickBook() {
	  try {
		  System.out.println("THIS IS WHERE THE QUICKBOOK CONNECTION WILL BE CALLED");
		  OAuth2Config oauth2config = new OAuth2Config.OAuth2ConfigBuilder(clientid, clientSecret)
				  	.callDiscoveryAPI(Environment.SANDBOX).buildConfig();
		  // GENERATING CSRF TOKEN
		  String csrf = oauth2config.generateCSRFToken();
		  // SCOPES
		  ArrayList<Scope> scopes = new ArrayList<Scope>();
		  scopes.add(Scope.Accounting);
		  return new RedirectView(oauth2config.prepareUrl(scopes, redirectURL,csrf), true, true, false);
				  
	  }catch(Exception exi){
		  exi.printStackTrace();
	  }
	  return null;
  }

}
