package de.debtcheck.output;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.jboss.logging.Logger;


@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/DebtAddQueue"), 
				@ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(
				propertyName = "messageSelector",
				propertyValue = "DocType LIKE 'Letter'")
		})

public class OutputRequesterProcessor implements MessageListener {

	private static final Logger logger = Logger.getLogger(OutputRequesterProcessor.class);
	
	@Override
	public void onMessage(Message message) {
       try {
    	  TextMessage msg = (TextMessage) message;
          logger.info("Received message from jms/queue/DebtAddQueue: " + msg.getText());
       }
       catch (JMSException e) {
            throw new EJBException(e);
       }
    }
}
