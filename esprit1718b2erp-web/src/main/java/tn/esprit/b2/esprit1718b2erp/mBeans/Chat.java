package tn.esprit.b2.esprit1718b2erp.mBeans;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import tn.esprit.b2.esprit1718b2erp.entities.User;
import tn.esprit.b2.esprit1718b2erp.msg.Message;
import tn.esprit.b2.esprit1718b2erp.msg.MessageDecoder;
import tn.esprit.b2.esprit1718b2erp.msg.MessageEncoder;



@ServerEndpoint(value="/chat", encoders={MessageEncoder.class}, decoders={MessageDecoder.class})
public class Chat {
	
	
	private User usr ;
    // Set of all the connected sessions
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    
    
    
    
    @OnOpen
    public void onOpen(Session session){
        sessions.add(session);
        
        Message message = new Message(Json.createObjectBuilder()
                .add("type", "text")
                .add("data", "*Notification* :"+this.usr.getName()+" has just Joinded us !!!")
               
                .build());
        
        for(Session s : sessions){
            try {
                s.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(session.getId() + " has connected");
    }
 
    public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

	/**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(Message message, Session session){
        System.out.println(message);
        for(Session s : sessions){
            try {
                s.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(message);
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
                
        Message message = new Message(Json.createObjectBuilder()
                .add("type", "text")
                .add("data", "*Notification !!! * "+ this.usr.getName() +"  Has just Disconnected ")
                .build());
        
        for(Session s : sessions){
            try {
                s.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException ex) {
                ex.printStackTrace();
            }
        }
       
        String msg =this.usr.getName();
        System.out.println("*Notification* :"+msg+" has just disconnected");
    }    
}
