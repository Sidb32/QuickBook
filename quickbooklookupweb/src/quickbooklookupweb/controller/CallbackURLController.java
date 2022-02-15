package quickbooklookupweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;

@Controller
public class CallbackURLController {

	String clientid = "ABOBnWeI0IbIhTLp75Cv19bjf8BYJmNqMGvUZgA8E3DTlkNfc8";
	String clientSecret = "uKdTH3l9DyAFZ9Xtfhka6Esh9qzIp5Zm2rT601qz";
	
	/***
	 * 
	 * @param authCode
	 * @param state
	 * @param realmId
	 * @param session
	 * @return
	 */
	@RequestMapping("/oauth2redirect")
	public String callBackFromOAuth(@RequestParam("code") String authCode, 
			@RequestParam("state") String state, @RequestParam(value = "realmId", required = false) String realmId, HttpSession session) {
		
		 try {
		        String csrfToken = (String) session.getAttribute("csrfToken");
		        if (csrfToken.equals(state)) {
		            session.setAttribute("realmId", realmId);
		            session.setAttribute("auth_code", authCode);
		            
		            OAuth2Config oauth2config = new OAuth2Config.OAuth2ConfigBuilder(clientid, clientSecret)
						  	.callDiscoveryAPI(Environment.SANDBOX).buildConfig();
		            OAuth2PlatformClient client = new OAuth2PlatformClient(oauth2config);
		            		
		           
		            // String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri");
		            
		            String redirectUri =  "http://localhost:8080/oauth2redirect";
		            
		            System.out.println("inside oauth2redirect of sample -- redirectUri " + redirectUri  );
		            
		            BearerTokenResponse bearerTokenResponse = client.retrieveBearerTokens(authCode, redirectUri);
					 
		            session.setAttribute("access_token", bearerTokenResponse.getAccessToken());
		            session.setAttribute("refresh_token", bearerTokenResponse.getRefreshToken());
		            
		            // Update your Data store here with user's AccessToken and RefreshToken along with the realmId

		            return "connected";
		        }
		        System.out.println("csrf token mismatch " );
	        } catch (OAuthException e) {
	        	 e.printStackTrace();;
			} 
	        return null;
		
	}

}
