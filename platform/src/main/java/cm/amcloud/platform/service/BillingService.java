package cm.amcloud.platform.service;

import cm.amcloud.platform.model.Subscription;
import cm.amcloud.platform.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BillingService {

    private final SubscriptionRepository subscriptionRepository;

    public BillingService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription subscribeAgency(String agencyName, String planType) {
        Subscription subscription = new Subscription();
        subscription.setAgencyName(agencyName);
        subscription.setPlanType(planType);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusMonths(planType.equalsIgnoreCase("Free") ? 1 : 12));
        subscription.setActive(true);
        return subscriptionRepository.save(subscription);
    }

    public boolean isAgencySubscriptionValid(String agencyName) {
        return subscriptionRepository.findByAgencyName(agencyName)
                .filter(Subscription::isActive)
                .filter(sub -> sub.getEndDate().isAfter(LocalDateTime.now()))
                .isPresent();
    }

     
    public Subscription createSubscription(String agencyName, String planType, int duration) {
            // Implement the logic to create a subscription
            Subscription subscription = new Subscription();
            subscription.setAgencyName(agencyName);
            subscription.setPlanType(planType);
            subscription.setStartDate(LocalDateTime.now());
            subscription.setEndDate(LocalDateTime.now().plusDays(duration));
            subscription.setActive(true);
            return subscriptionRepository.save(subscription);
        }

        //create method to validate subscription
        public boolean checkSubscriptionValid(String agencyName) {
            // Implement the logic to check if the subscription is valid
            return subscriptionRepository.findByAgencyName(agencyName)
                    .map(subscription -> subscription.getEndDate().isAfter(LocalDateTime.now()))
                    .orElse(false);
        }
    
}
