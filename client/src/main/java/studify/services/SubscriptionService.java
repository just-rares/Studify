package studify.services;


import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.stomp.StompSession;
import studify.utils.WebSocketStompUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class SubscriptionService {

    WebSocketStompUtils webSocketStompUtils;
    private List<StompSession.Subscription> subscriptions;

    /**
     * Constructor for the service
     */
    public SubscriptionService(WebSocketStompUtils webSocketStompUtils){
        this.subscriptions = new ArrayList<>();
        this.webSocketStompUtils = webSocketStompUtils;
    }

    /**
     * Adds a new subscription to the list
     * @param subscription subscription to be added
     */
    public void addSubscription(StompSession.Subscription subscription){
        this.subscriptions.add(subscription);
    }

    /**
     * Unsubscribes and removes all subscriptions
     */
    public void unsubscribeFromAll(){
        for(StompSession.Subscription subscription : subscriptions){
            subscription.unsubscribe();
        }
        subscriptions.clear();
    }

    /**
     * Getter for the subscriptions field
     * @return list of all subscriptions
     */
    public List<StompSession.Subscription> getSubscriptions(){
        return subscriptions;
    }

    public void registerForUserAddition(String username, Consumer<String> consumer) {
        StompSession.Subscription subscription = webSocketStompUtils.registerForMessages("/topic/addUser/" + username, consumer);
        addSubscription(subscription);
    }
}
