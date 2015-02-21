package com.nando.mvc;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.controller.MandrillMessagesApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import com.microtripit.mandrillapp.lutung.view.MandrillUserInfo;
import com.nando.dto.StatusResponse;
import com.nando.dto.TestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Controller
public class TestController {
	
	private static final Logger logger = Logger.getLogger(TestController.class.getName());
	private static final String MANDRILL_KEY = "Ot77cpmzRCwbiZaLg8oyGQ";
 	private static final MandrillMessagesApi messagesApi = new MandrillMessagesApi(MANDRILL_KEY);

	
	public TestController(){
	}

	@RequestMapping(value = "/test/airport",
             method = RequestMethod.GET,
             consumes = "application/json",
             produces = "application/json",
             headers = {"content-type=application/json"})
	@ResponseBody
	public StatusResponse testResetPasswordEmail()  {
		logger.info("called");
		StatusResponse sr = new StatusResponse();

		try
		{

			MandrillMessageStatus[] status = sendSingleAirport("will.edwards@me.com","27.98","<p><b>Have a good trip</b/></p>");
			StringBuilder sb = new StringBuilder();
			logger.info("statuses from mandrill = " + status.length);
			for (int i = 0; i < status.length; i++)
			{
				sb.append("EMAIL = " + status[i].getEmail());
				sb.append("\nID = " + status[i].getId());
				sb.append("\nREJECT REASON= " + status[i].getRejectReason());
				sb.append("\nSTATUS = " + status[i].getStatus());
			}
			sr.setData("sent to will.edwards@me.com\n" + sb.toString());

		}
		catch (MandrillApiError | IOException e)
		{
			logger.severe(e.getMessage());
			sr.setMessage(e.getMessage());
			sr.setStatusCode(99);
		}
		return sr;
    }

	private  MandrillMessageStatus[] sendSingleAirport(String  emailAddress, String price, String html) throws IOException, MandrillApiError
	{
		 MandrillMessage message = new MandrillMessage();

		 ArrayList<MandrillMessage.Recipient> recipients = new ArrayList<>();
		 MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
		 recipient.setEmail(emailAddress);
		 recipients.add(recipient);

		 message.setTo(recipients);
		 message.setPreserveRecipients(true);

		 List<MandrillMessage.MergeVar> list = new ArrayList();
		 list.add(new MandrillMessage.MergeVar("price",price));

		 message.setGlobalMergeVars(list);

		Map<String,String> template_content = new HashMap();
		template_content.put("JOURNEY_DETAILS",html);


		 MandrillMessageStatus[] status = messagesApi.sendTemplate("singleairport", template_content, message, false);

		 return status;

	 }




	@RequestMapping("/test")
	@ResponseBody 
	public MandrillUserInfo test(TestDTO input) throws MandrillApiError, IOException{
		logger.info("Input: "+input);	
	
		MandrillApi mandrillApi = new MandrillApi(MANDRILL_KEY);

		
		MandrillUserInfo userInfo = mandrillApi.users().info();
		
		logger.info("userInfo: "+userInfo.getUsername());	

		return userInfo;
	}
	

}